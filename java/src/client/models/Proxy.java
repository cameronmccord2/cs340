package client.models;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import shared.definitions.CatanColor;
import client.data.GameInfo;
import client.data.PlayerInfo;
import client.exceptions.InvalidGameModelException;
import client.models.exceptions.InvalidTranslatorModelException;
import client.models.translator.ClientModel;
import client.server.AcceptTrade;
import client.server.BuyDevCard;
import client.server.CreateGame;
import client.server.FinishedTurn;
import client.server.GameServer;
import client.server.MaritimeTradeOff;
import client.server.OfferTrade;
import client.server.PlayerServer;
import client.server.RoadBuilding;
import client.server.ServerBuildCity;
import client.server.ServerBuildRoad;
import client.server.ServerBuildSettlement;
import client.server.ServerChat;
import client.server.ServerJoinGame;
import client.server.ServerLogLevel;
import client.server.ServerMonopoly;
import client.server.ServerMonument;
import client.server.ServerRobPlayer;
import client.server.ServerRoll;
import client.server.ServerSoldier;
import client.server.ServerYearofPlenty;
import client.server.User;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * The Proxy class acts as a proxy for the real server and has similar methods that are found on the server.
 * There are three doPost methods. One is for logging in with no cookie headers. Then the second is for joining a game. Then the last is for all subsequent post calls.
 * @author scottdaly
 *
 */
public class Proxy implements IProxy {

	private HttpURLConnection connection;

	private Gson gson = new Gson();
	private Translator translator;
	private List<IGame> games;
	private String cookie;
	private String gameId = null;
	private IFacade facade;
	private ReturnedUser rUser;
	
	
	public Proxy() {
		this.translator = new Translator();
		this.games = new ArrayList<IGame>();
		this.facade = new Facade(this);
	}
	
	@Override
	public IFacade getFacade(){
		return this.facade;
	}
	
	private synchronized IGame saveGameModel(String model){
		if(model.length() < 1000)
			return null;
		
		Integer version = -1;
		try {
			version = this.getVersionForGameId(Integer.parseInt(this.gameId));
		} catch (NumberFormatException e1) {
		} catch (InvalidGameModelException e1) {
		}
		Gson gson = new Gson();
		ClientModel cm = gson.fromJson(model, ClientModel.class);
		try {
			cm.isValid();
		} catch (InvalidTranslatorModelException e) {
			throw new RuntimeException(e.getMessage());
		}
		if(version == null){
			version = -1;
		}
		//System.out.println("version: " + version);
		if(cm.getVersion() == version.intValue()){
			// ignore update
		}else{
			IGame g = null;
			try {
				g = this.translator.convertClientModelToGame(cm, this.getGameForGameId(Integer.parseInt(gameId)));
				for (int i = 0; i < this.games.size(); i++) {
					if(this.games.get(i).getGameInfo().getId() == g.getGameInfo().getId())
						this.games.set(i, g);
				}
			} catch (NumberFormatException | InvalidLocationException e) {
			}
			this.facade.updatedCatanModel();
			return g;
		}
		return null;
	}
	
	@Override
	public ServerResponse postUserLogin(User user){
		ServerResponse sr =  doPost("/user/login", gson.toJson(user), false, false);
		if(sr.getResponseCode() == 200){
			Map<String, List<String>> map = connection.getHeaderFields();
			List<String> setCookie = map.get("Set-cookie");
			cookie = setCookie.get(0);
			cookie = cookie.substring(0, cookie.length() - 8);
			String temp = cookie.substring(11, cookie.length());
			try {
				rUser = gson.fromJson(URLDecoder.decode(temp, "UTF-8"), ReturnedUser.class);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
		}
		this.facade.setCurrentUser(user.getUser());
		return sr;
	}
	

	public ReturnedUser getrUser() {
		return rUser;
	}

	@Override
	public ServerResponse postUserRegister(User user){
		ServerResponse sr = doPost("/user/register", gson.toJson(user), false, false);
		return sr;
	}
	
	@Override
	public List<IGame> getGamesList(){
		ServerResponse sr = doGet("/games/list", false, false);
		List<GameServer> games = gson.fromJson(sr.getJson(), new TypeToken<List<GameServer>>(){}.getType());
		List<IGame> list = new ArrayList<IGame>();
		for (GameServer g : games) {
			Game game = new Game();
			
			if(g.getPlayers()[0].getColor() != null){
				IPlayer[] players = new IPlayer[g.getPlayers().length];
				int playerIndex = 0;
				for (PlayerServer p : g.getPlayers()) {
					PlayerInfo pi = new PlayerInfo();
					pi.setColor(CatanColor.getColorForName(p.getColor()));
					pi.setId(p.getId());
					pi.setName(p.getName());
					pi.setPlayerIndex(playerIndex);
					IPlayer player = new Player(pi);
					players[playerIndex] = player;
					playerIndex++;
				}
				game.setPlayers(players);
			}
			
			GameInfo gi = new GameInfo();
			gi.setId(g.getId());
			gi.setTitle(g.getTitle());
			game.setGameInfo(gi);
			list.add(game);
		}
		this.games = list;
		return this.games;
	}
	
	@Override
	public ServerResponse postGamesCreate(CreateGame game){
		ServerResponse sr = doPost("/games/create", gson.toJson(game), false, false);
		return sr;
	}
	
	@Override
	public ServerResponse postGamesJoin(ServerJoinGame join){
		ServerResponse sr = doPost("/games/join", gson.toJson(join), true, false);// responds with Success
		Map<String, List<String>> map = connection.getHeaderFields();
		List<String> setCookie = map.get("Set-cookie");
		gameId = setCookie.get(0);
//		System.out.println(gameId);// catan.game=0;Path=/;
		gameId = gameId.substring(0, gameId.length() - 8);
//		System.out.println(gameId);//catan.game=0
		gameId = gameId.substring(11);
		//System.out.println(gameId);//0
//		this.getGameModel();// first time it gets gotten
		return sr;
	}
	
	@Override
	public IGame getGameModel(){
		Integer version = 0;
		try {
			version = this.getVersionForGameId(Integer.parseInt(this.gameId));
		} catch (InvalidGameModelException e1) {
			// fail silently
		}
		if(version == null)
			version = 0;
		
		
		String requestUrl = "/game/model";
		if(version != 0)
			requestUrl += "?version=" + version;
		ServerResponse sr = this.doGet(requestUrl, true, true);
		return this.saveGameModel(sr.getJson());
	}
	
	private IGame getGameForGameId(Integer gameId) {
		for (IGame g : this.games) {
			if(g.getGameInfo().getId() == gameId)
				return g;
		}
		return null;
	}

	private Integer getVersionForGameId(Integer gameId) throws InvalidGameModelException {
		for (IGame g : this.games) {
			if(g.getGameInfo().getId() == gameId)
				return g.getModelVersion();
		}
		throw new InvalidGameModelException("Couldnt find the requested game: " + gameId + " in the game list: " + this.games.toString());
	}

	@Override
	public ServerResponse postGameReset(){
		ServerResponse sr = doPost("/game/reset","", true, true);
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesSendChat(ServerChat chat){
		ServerResponse sr = doPost("/moves/sendChat", gson.toJson(chat), true, true);
		saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesRollNumber(ServerRoll roll){
		ServerResponse sr = doPost("/moves/rollNumber", gson.toJson(roll), true, true);
		saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse moveRobPlayer(ServerRobPlayer rob){
		ServerResponse sr = doPost("/moves/robPlayer", gson.toJson(rob), true, true);
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesFinishTurn(FinishedTurn turn){
		ServerResponse sr = doPost("/moves/finishTurn", gson.toJson(turn), true, true);
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesBuyDevCard(BuyDevCard card){
		ServerResponse sr = doPost("/moves/buyDevCard", gson.toJson(card), true, true);
		saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesYear_of_Plenty(ServerYearofPlenty yop){
		ServerResponse sr = doPost("/moves/Year_of_Plenty", gson.toJson(yop), true, true);
		saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesRoad_Building(RoadBuilding rb){
		ServerResponse sr = doPost("/moves/Road_Building", gson.toJson(rb), true, true);
		saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesSoldier(ServerSoldier ss){
		ServerResponse sr = doPost("/moves/Soldier", gson.toJson(ss), true, true);
		saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesMonopoly(ServerMonopoly sm){
		ServerResponse sr = doPost("/moves/Monopoly", gson.toJson(sm), true, true);
		saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesMonument(ServerMonument sm){
		ServerResponse sr = doPost("/moves/Monument", gson.toJson(sm), true, true);
		saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesBuildRoad(ServerBuildRoad br){
		ServerResponse sr = doPost("/moves/buildRoad", gson.toJson(br), true, true);
		saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesBuildSettlement(ServerBuildSettlement bs){
		ServerResponse sr = doPost("/moves/buildSettlement", gson.toJson(bs), true, true);
		saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesBuildCity(ServerBuildCity bc){
		ServerResponse sr = doPost("/moves/buildCity", gson.toJson(bc), true, true);
		saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesOfferTrade(OfferTrade ot){
		ServerResponse sr = doPost("/moves/offerTrade", gson.toJson(ot), true, true);
		saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesAcceptTrade(AcceptTrade at){
		ServerResponse sr = doPost("/moves/acceptTrade", gson.toJson(at), true, true);
		saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesMaritimeTrade(MaritimeTradeOff mTrade){
		ServerResponse sr = doPost("/moves/maritimeTrade", gson.toJson(mTrade), true, true);
		saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesdiscardCards(PlayerInfo player){
		return null;
	}
	
	@Override
	public ServerResponse utilChangeLogLevel(ServerLogLevel loglevel){
		ServerResponse sr = doPost("/util/changeLogLevel", gson.toJson(loglevel), true, true);
		saveGameModel(sr.getJson());
		return sr;
	}
	
	private ServerResponse doGet(String urlPath, boolean withCookie, boolean withGameId){
		return this.doRequest(urlPath, "GET", withCookie, withGameId, null);
	}
	
	private ServerResponse doPost(String urlPath, String postData, boolean withCookie, boolean withGameId){
		return this.doRequest(urlPath, "POST", withCookie, withGameId, postData);
	}
	
	private ServerResponse doRequest(String urlPath, String verb, boolean withCookie, boolean withGameId, String postData){
		URL url;
		try {
			url = new URL("http://localhost:8081" + urlPath);
			connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod(verb);
			String cookieProperty = "";
			if(withCookie){
				cookieProperty = cookie;
				if(withGameId)
					cookieProperty += "; catan.game=" + gameId;
			}
			if(cookieProperty.length() > 0)
				connection.setRequestProperty("Cookie", cookieProperty);
			
			if(verb.equals("POST"))
				connection.setDoOutput(true);
			
			connection.connect();
			
			
			if(verb.equals("POST")){
				//Write request body to OutputStream
				 OutputStream requestBody = connection.getOutputStream();  
				 requestBody.write(postData.getBytes(Charset.forName("UTF-8")));
				 requestBody.close();
			}
			
			int response = connection.getResponseCode();
			if (response == HttpURLConnection.HTTP_OK) { 
				
				 //Read response body from InputStream
				 InputStream responseBody = connection.getInputStream(); 
				 
				 //convert response to string
				 BufferedReader reader = new BufferedReader(new InputStreamReader(responseBody));
				 StringBuilder out = new StringBuilder();
				 String line;
				 while ((line = reader.readLine()) != null) {
				     out.append(line);
				 }
				 reader.close();
				 responseBody.close();
				 return new ServerResponse(out.toString(), connection.getResponseCode());
			 } 
			 else{
				//Read response body from InputStream
				 InputStream responseBody = connection.getErrorStream(); 
				 
				 //convert response to string
				 BufferedReader reader = new BufferedReader(new InputStreamReader(responseBody));
				 StringBuilder out = new StringBuilder();
				 String line;
				 while ((line = reader.readLine()) != null) {
				     out.append(line);
				 }
				 reader.close();
				 responseBody.close();
				 return new ServerResponse(out.toString(), connection.getResponseCode());
			 }
	    }catch(Exception e){
			e.printStackTrace();
	    }
		return null;
	}
	
//	private ServerResponse doPost(String urlPath, String postData) {
//		try { 
//			 URL url = new URL("http://localhost:8081" + urlPath); 
//			 connection = (HttpURLConnection)url.openConnection();
//			 connection.setRequestMethod("POST");
//			 connection.setDoOutput(true);
//			 connection.connect();
//			 
//			 
//			 
//			 int response = connection.getResponseCode();
//	
//			 if (response == HttpURLConnection.HTTP_OK) {
//				 //Read response body from InputStream
//				 InputStream responseBody = connection.getInputStream(); 
//				 
//				 //convert response to string
//				 BufferedReader reader = new BufferedReader(new InputStreamReader(responseBody));
//				 StringBuilder out = new StringBuilder();
//				 String line;
//				 while ((line = reader.readLine()) != null) {
//				     out.append(line);
//				 } 
//				 reader.close();
//				 responseBody.close();
//				 return new ServerResponse(out.toString(), connection.getResponseCode());
//			 } 
//			 else{
//				//Read response body from InputStream
//				 InputStream responseBody = connection.getErrorStream(); 
//				 
//				 //convert response to string
//				 BufferedReader reader = new BufferedReader(new InputStreamReader(responseBody));
//				 StringBuilder out = new StringBuilder();
//				 String line;
//				 while ((line = reader.readLine()) != null) {
//				     out.append(line);
//				 } 
//				 reader.close();
//				 responseBody.close();
//				 return new ServerResponse(out.toString(), connection.getResponseCode());
//			 }
//	    }catch(Exception e){
//			e.printStackTrace();
//	    }
//		return null;
//	}
//	private ServerResponse doJoinPost(String urlPath, String postData) {
//		try { 
//			 URL url = new URL("http://localhost:8081" + urlPath); 
//			 connection = (HttpURLConnection)url.openConnection();
//			 connection.setRequestMethod("POST");
//			 connection.setRequestProperty("Cookie", cookie);
//			 connection.setDoOutput(true);
//			 connection.connect();
//			 
//			 //Write request body to OutputStream
//			 OutputStream requestBody = connection.getOutputStream();  
//			 requestBody.write(postData.getBytes(Charset.forName("UTF-8")));
//			 requestBody.close();
//			 
//			 int response = connection.getResponseCode();
//	
//			 if (response == HttpURLConnection.HTTP_OK) {
//				 //Read response body from InputStream
//				 InputStream responseBody = connection.getInputStream(); 
//				 
//				 //convert response to string
//				 BufferedReader reader = new BufferedReader(new InputStreamReader(responseBody));
//				 StringBuilder out = new StringBuilder();
//				 String line;
//				 while ((line = reader.readLine()) != null) {
//				     out.append(line);
//				 }
//				 reader.close();
//				 responseBody.close();
//				 return new ServerResponse(out.toString(), connection.getResponseCode());
//			 } 
//			 else{
//				//Read response body from InputStream
//				 InputStream responseBody = connection.getErrorStream(); 
//				 
//				 //convert response to string
//				 BufferedReader reader = new BufferedReader(new InputStreamReader(responseBody));
//				 StringBuilder out = new StringBuilder();
//				 String line;
//				 while ((line = reader.readLine()) != null) {
//				     out.append(line);
//				 } 
//				 reader.close();
//				 responseBody.close();
//				 return new ServerResponse(out.toString(), connection.getResponseCode());
//			 }
//	    }catch(Exception e){
//			e.printStackTrace();
//	    }
//		return null;
//	}
//	
//	private ServerResponse doMasterPost(String urlPath, String postData) {
//		try { 
//			 URL url = new URL("http://localhost:8081" + urlPath); 
//			 connection = (HttpURLConnection)url.openConnection();
//			 connection.setRequestMethod("POST");
//			 connection.setRequestProperty("Cookie", cookie + "; " + gameId);
//			 connection.setDoOutput(true);
//			 connection.connect();
//			 
//			 //Write request body to OutputStream
//			 OutputStream requestBody = connection.getOutputStream();  
//			 requestBody.write(postData.getBytes(Charset.forName("UTF-8")));
//			 requestBody.close();
//			 
//			 int response = connection.getResponseCode();
//	
//			 if (response == HttpURLConnection.HTTP_OK) {
//				 //Read response body from InputStream
//				 InputStream responseBody = connection.getInputStream(); 
//				 
//				 //convert response to string
//				 BufferedReader reader = new BufferedReader(new InputStreamReader(responseBody));
//				 StringBuilder out = new StringBuilder();
//				 String line;
//				 while ((line = reader.readLine()) != null) {
//				     out.append(line);
//				 } 
//				 reader.close();
//				 responseBody.close();
//				 return new ServerResponse(out.toString(), connection.getResponseCode());
//			 } 
//			 else{
//				//Read response body from InputStream
//				 InputStream responseBody = connection.getErrorStream(); 
//				 
//				 //convert response to string
//				 BufferedReader reader = new BufferedReader(new InputStreamReader(responseBody));
//				 StringBuilder out = new StringBuilder();
//				 String line;
//				 while ((line = reader.readLine()) != null) {
//				     out.append(line);
//				 }
//				 reader.close();
//				 responseBody.close();
//				 return new ServerResponse(out.toString(), connection.getResponseCode());
//			 }
//	    }catch(Exception e){
//			e.printStackTrace();
//	    }
//		return null;
//	}

	@Override
	public List<IGame> getGames() {
		return games;
	}

	@Override
	public String getGameId() {
		return gameId;
	}
}
