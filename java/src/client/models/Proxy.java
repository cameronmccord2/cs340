package client.models;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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
	private String gameId;
	private IFacade facade;
	
	public Proxy() {
		this.translator = new Translator();
		this.games = new ArrayList<IGame>();
		this.facade = new Facade(this);
	}
	
	@Override
	public IFacade getFacade(){
		return this.facade;
	}
	
	private IGame saveGameModel(String model){
		Gson gson = new Gson();
		ClientModel cm = gson.fromJson(model, ClientModel.class);
		try {
			cm.isValid();
		} catch (InvalidTranslatorModelException e) {
			throw new RuntimeException(e.getMessage());
		}
		IGame g = null;
		try {
			g = this.translator.convertClientModelToGame(cm, this.getGameForGameId(Integer.parseInt(gameId)));
		} catch (NumberFormatException | InvalidLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < this.games.size(); i++) {
			if(this.games.get(i).getGameInfo().getId() == g.getGameInfo().getId())
				this.games.set(i, g);
		}
		this.facade.updatedCatanModel();
		return g;
	}
	
	@Override
	public ServerResponse postUserLogin(User user){
		ServerResponse sr =  doPost("/user/login", gson.toJson(user));
		//saveGameModel(sr.getJson());
		if(sr.getResponseCode() == 200){
			Map<String, List<String>> map = connection.getHeaderFields();
			List<String> setCookie = map.get("Set-cookie");
			cookie = setCookie.get(0);
			cookie = cookie.substring(0, cookie.length() - 8);
		}
		return sr;
	}
	
	@Override
	public ServerResponse postUserRegister(User user){
		ServerResponse sr = doPost("/user/register", gson.toJson(user));
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse getGamesList(){
		ServerResponse sr = doGet("/games/list");
		List<GameServer> games = gson.fromJson(sr.getJson(), new TypeToken<List<GameServer>>(){}.getType());
		List<IGame> list = new ArrayList<IGame>();
		for (GameServer g : games) {
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
			Game game = new Game();
			game.setPlayers(players);
			
			GameInfo gi = new GameInfo();
			gi.setId(g.getId());
			gi.setTitle(g.getTitle());
			game.setGameInfo(gi);
			list.add(game);
		}
		this.games = list;
		return sr;
	}
	
	@Override
	public ServerResponse postGamesCreate(CreateGame game){
		ServerResponse sr = doPost("/games/create", gson.toJson(game));
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse postGamesJoin(ServerJoinGame join){
		ServerResponse sr = doJoinPost("/games/join", gson.toJson(join));
		//saveGameModel(sr.getJson());
		Map<String, List<String>> map = connection.getHeaderFields();
		List<String> setCookie = map.get("Set-cookie");
		gameId = setCookie.get(0);
		gameId = gameId.substring(0, gameId.length() - 8);
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
		String requestUrl = "/game/model";
		if(version != 0)
			requestUrl += "?version=" + version;
		ServerResponse sr = this.doGet(requestUrl);
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
		ServerResponse sr = doMasterPost("/game/reset","");
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesSendChat(ServerChat chat){
		ServerResponse sr = doMasterPost("/moves/sendChat", gson.toJson(chat));
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesRollNumber(ServerRoll roll){
		ServerResponse sr = doMasterPost("/moves/rollNumber", gson.toJson(roll));
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse moveRobPlayer(ServerRobPlayer rob){
		ServerResponse sr = doMasterPost("/moves/robPlayer", gson.toJson(rob));
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesFinishTurn(FinishedTurn turn){
		ServerResponse sr = doMasterPost("/moves/finishTurn", gson.toJson(turn));
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesBuyDevCard(BuyDevCard card){
		ServerResponse sr = doMasterPost("/moves/buyDevCard", gson.toJson(card));
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesYear_of_Plenty(ServerYearofPlenty yop){
		ServerResponse sr = doMasterPost("/moves/Year_of_Plenty", gson.toJson(yop));
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesRoad_Building(RoadBuilding rb){
		ServerResponse sr = doMasterPost("/moves/Road_Building", gson.toJson(rb));
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesSoldier(ServerSoldier ss){
		ServerResponse sr = doMasterPost("/moves/Soldier", gson.toJson(ss));
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesMonopoly(ServerMonopoly sm){
		ServerResponse sr = doMasterPost("/moves/Monopoly", gson.toJson(sm));
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesMonument(ServerMonument sm){
		ServerResponse sr = doMasterPost("/moves/Monument", gson.toJson(sm));
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesBuildRoad(ServerBuildRoad br){
		ServerResponse sr = doMasterPost("/moves/buildRoad", gson.toJson(br));
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesBuildSettlement(ServerBuildSettlement bs){
		ServerResponse sr = doMasterPost("/moves/buildSettlement", gson.toJson(bs));
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesBuildCity(ServerBuildCity bc){
		ServerResponse sr = doMasterPost("/moves/buildCity", gson.toJson(bc));
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesOfferTrade(OfferTrade ot){
		ServerResponse sr = doMasterPost("/moves/offerTrade", gson.toJson(ot));
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesAcceptTrade(AcceptTrade at){
		ServerResponse sr = doMasterPost("/moves/acceptTrade", gson.toJson(at));
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesMaritimeTrade(MaritimeTradeOff mTrade){
		ServerResponse sr = doMasterPost("/moves/maritimeTrade", gson.toJson(mTrade));
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	@Override
	public ServerResponse movesdiscardCards(PlayerInfo player){
		return null;
	}
	
	@Override
	public ServerResponse utilChangeLogLevel(ServerLogLevel loglevel){
		ServerResponse sr = doMasterPost("/util/changeLogLevel", gson.toJson(loglevel));
		//saveGameModel(sr.getJson());
		return sr;
	}
	
	private ServerResponse doGet(String urlPath){
		URL url;
		try {
			url = new URL("http://localhost:8081" + urlPath);
			connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			
			int response = connection.getResponseCode();
			System.out.println(response);
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
				 System.out.println("response: " + out.toString());  
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
				 System.out.println(out.toString());  
				 reader.close();
				 responseBody.close();
				 return new ServerResponse(out.toString(), connection.getResponseCode());
			 }
	    }catch(Exception e){
			e.printStackTrace();
	    }
		return null;
	}
	
	private ServerResponse doPost(String urlPath, String postData) {
		try { 
			 URL url = new URL("http://localhost:8081" + urlPath); 
			 connection = (HttpURLConnection)url.openConnection();
			 connection.setRequestMethod("POST");
			 connection.setDoOutput(true);
			 connection.connect();
			 
			 //Write request body to OutputStream
			 OutputStream requestBody = connection.getOutputStream();  
			 requestBody.write(postData.getBytes(Charset.forName("UTF-8")));
			 requestBody.close();
			 
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
	private ServerResponse doJoinPost(String urlPath, String postData) {
		try { 
			 URL url = new URL("http://localhost:8081" + urlPath); 
			 connection = (HttpURLConnection)url.openConnection();
			 connection.setRequestMethod("POST");
			 connection.setRequestProperty("Cookie", cookie);
			 connection.setDoOutput(true);
			 connection.connect();
			 
			 //Write request body to OutputStream
			 OutputStream requestBody = connection.getOutputStream();  
			 requestBody.write(postData.getBytes(Charset.forName("UTF-8")));
			 requestBody.close();
			 
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
	
	private ServerResponse doMasterPost(String urlPath, String postData) {
		try { 
			 URL url = new URL("http://localhost:8081" + urlPath); 
			 connection = (HttpURLConnection)url.openConnection();
			 connection.setRequestMethod("POST");
			 connection.setRequestProperty("Cookie", cookie + "; " + gameId);
			 connection.setDoOutput(true);
			 connection.connect();
			 
			 //Write request body to OutputStream
			 OutputStream requestBody = connection.getOutputStream();  
			 requestBody.write(postData.getBytes(Charset.forName("UTF-8")));
			 requestBody.close();
			 
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

	@Override
	public List<IGame> getGames() {
		return games;
	}

	@Override
	public String getGameId() {
		return gameId;
	}
}
