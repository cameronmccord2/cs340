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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import client.data.PlayerInfo;
import client.exceptions.InvalidGameModelException;
import client.models.exceptions.InvalidTranslatorModelException;
import client.models.translator.ClientModel;
import client.server.AcceptTrade;
import client.server.BuyDevCard;
import client.server.CreateGame;
import client.server.FinishedTurn;
import client.server.GameLoad;
import client.server.GameServer;
import client.server.MaritimeTradeOff;
import client.server.OfferTrade;
import client.server.RoadBuilding;
import client.server.SaveGame;
import client.server.ServerAI;
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

/**
 * The Proxy class acts as a proxy for the real server and has similar methods that are found on the server. 
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
	
	public Proxy() {
		this.translator = new Translator();
		this.games = new ArrayList<IGame>();
	}
	
	@Override
	public String postUserLogin(User user){
		String response =  doPost("/user/login", gson.toJson(user));
		Map<String, List<String>> map = connection.getHeaderFields();
		List<String> setCookie = map.get("Set-cookie");
		cookie = setCookie.get(0);
//		cookie = cookie.substring(11);
		cookie = cookie.substring(0, cookie.length() - 8);
		return response;
	}
	
	@Override
	public String postUserRegister(User user){
		return doPost("/user/register", gson.toJson(user));
	}
	
	@Override
	public GameServer[] getGamesList(){
		String response = doGet("/games/list");
		List<GameServer> games = gson.fromJson(response, new TypeToken<List<GameServer>>(){}.getType());
		GameServer[] list = new GameServer[games.size()];
		int index = 0;
		for (GameServer g : games) {
			list[index] = g;
			index++;
		}
		return list;
	}
	
	@Override
	public String postGamesCreate(CreateGame game){
		String json = gson.toJson(game);
		String response = doPost("/games/create", json);
		return null;
	}
	
	@Override
	public String postGamesJoin(ServerJoinGame join){
		String response = doJoinPost("/games/join", gson.toJson(join));
		Map<String, List<String>> map = connection.getHeaderFields();
		List<String> setCookie = map.get("Set-cookie");
		gameId = setCookie.get(0);
		//gameId = gameId.substring(11);
		gameId = gameId.substring(0, gameId.length() - 8);
		return response;
	}
	
	@Override
	public String postGamesSave(SaveGame game){
		String response =  doMasterPost("/games/save", gson.toJson(game));
		System.out.println(response);
		return response;
	}
	
	@Override
	public String postGamesLoad(GameLoad game){
		return "";
	}
	
	@Override
	public IGame getGameModel(Integer gameId){
		Integer version = 0;
		try {
			version = this.getVersionForGameId(gameId);
		} catch (InvalidGameModelException e1) {
			// fail silently
		}
		String requestUrl = "/game/model";
		if(version != 0)
			requestUrl += "?version=" + version;
		String response = this.doGet(requestUrl);
		Gson gson = new Gson();
		ClientModel cm = gson.fromJson(response, ClientModel.class);
		try {
			cm.isValid();
		} catch (InvalidTranslatorModelException e) {
			throw new RuntimeException(e.getMessage());
		}
		IGame g = this.translator.convertClientModelToGame(cm, this.getGameForGameId(gameId));
		return g;
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
	public String postGameReset(){
		return doMasterPost("/game/reset","");
	}
	
	@Override
	public void postGameCommands(){
		
	}
	
	@Override
	public String getGameCommands(){
		return doGet("/game/commands");
	}
	
	@Override
	public String postAddAI(ServerAI ai){
		return doPost("/game/addAI", gson.toJson(ai));
	}
	
	@Override
	public String getListAI(){
		return doGet("/game/listAI");
	}
	
	/**
	 * posts a chat message to the server
	 * @param the Chat object that needs to be posted
	 */
	@Override
	public String movesSendChat(ServerChat chat){
		return doPost("/moves/sendChat", gson.toJson(chat));
	}
	
	@Override
	public String movesRollNumber(ServerRoll roll){
		return doPost("/moves/rollNumber", gson.toJson(roll));
	}
	
	@Override
	public String moveRobPlayer(ServerRobPlayer rob){
		return doPost("/moves/robPlayer", gson.toJson(rob));
	}
	
	@Override
	public String movesFinishTurn(FinishedTurn turn){
		return doPost("/moves/finishTurn", gson.toJson(turn));
	}
	
	@Override
	public String movesBuyDevCard(BuyDevCard card){
		return doPost("/moves/buyDevCard", gson.toJson(card));
	}
	
	@Override
	public String movesYear_of_Plenty(ServerYearofPlenty yop){
		return doPost("/moves/Year_of_Plenty", gson.toJson(yop));
	}
	
	@Override
	public String movesRoad_Building(RoadBuilding rb){
		return doPost("/moves/Raod_Building", gson.toJson(rb));
	}
	
	@Override
	public String movesSoldier(ServerSoldier ss){
		return doPost("/moves/Soldier", gson.toJson(ss));
	}
	
	@Override
	public String movesMonopoly(ServerMonopoly sm){
		return doPost("/moves/Monopoly", gson.toJson(sm));
	}
	
	@Override
	public String movesMonument(ServerMonument sm){
		return doPost("/moves/Monument", gson.toJson(sm));
	}
	
	@Override
	public String movesBuildRoad(ServerBuildRoad br){
		return doPost("/moves/buildRoad", gson.toJson(br));
	}
	
	@Override
	public String movesBuildSettlement(ServerBuildSettlement bs){
		return doPost("/moves/buildSettlement", gson.toJson(bs));
	}
	
	@Override
	public String movesBuildCity(ServerBuildCity bc){
		return doPost("/moves/buildCity", gson.toJson(bc));
	}
	
	@Override
	public String movesOfferTrade(OfferTrade ot){
		return doPost("/moves/offerTrade", gson.toJson(ot));
	}
	
	@Override
	public String movesAcceptTrade(AcceptTrade at){
		return doPost("/moves/acceptTrade", gson.toJson(at));
	}
	
	@Override
	public String movesMaritimeTrade(MaritimeTradeOff mTrade){
		return doPost("/moves/maritimeTrade", gson.toJson(mTrade));
	}
	
	@Override
	public void movesdiscardCards(PlayerInfo player){
		
	}
	
	@Override
	public String utilChangeLogLevel(ServerLogLevel loglevel){
		return doPost("/util/changeLogLevel", gson.toJson(loglevel));
	}
	
	private String doGet(String urlPath){
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
				 System.out.println(out.toString());  
				 reader.close();
				 responseBody.close();
				 return out.toString();
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
				 return out.toString();
			 }
	    }catch(Exception e){
			e.printStackTrace();
	    }
		return "";
	}
	
	private String doPost(String urlPath, String postData) {
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
				 return out.toString();
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
				 return out.toString();
			 }
	    }catch(Exception e){
			e.printStackTrace();
	    }
		return "";
	}
	private String doJoinPost(String urlPath, String postData) {
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
				 return out.toString();
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
				 return out.toString();
			 }
	    }catch(Exception e){
			e.printStackTrace();
	    }
		return "";
	}
	
	private String doMasterPost(String urlPath, String postData) {
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
				 return out.toString();
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
				 return out.toString();
			 }
	    }catch(Exception e){
			e.printStackTrace();
	    }
		return "";
	}
}
