package client.models;

import java.io.BufferedReader;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import sun.misc.IOUtils;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import client.data.GameInfo;
import client.data.PlayerInfo;
import client.models.exceptions.InvalidTranslatorModelException;
import client.models.translator.ClientModel;
import client.server.CreateGame;
import client.server.GameServer;
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
	
	public Proxy() {
		this.translator = new Translator();
		this.games = new ArrayList<IGame>();
	}
	
	@Override
	public String postUserLogin(User user){
		return doPost("/user/login", gson.toJson(user));
	}
	
	@Override
	public String postUserRegister(User user){
		return doPost("/user/register", gson.toJson(user));
	}
	
	@Override
	public GameServer[] getGamesList(){
		String response = doGet("/games/list");
		GameServer gameServer = gson.fromJson(response, GameServer.class);
		System.out.println();
		return null;
	}
	
	@Override
	public String postGamesCreate(CreateGame game){
		String json = gson.toJson(game);
		System.out.println(json);
		String response = doPost("/games/create", json);
		return null;
	}
	
	@Override
	public String postGamesJoin(String game){
		String response = doPost("/games/join", game);
		return null;
	}
	
	@Override
	public void postGamesSave(Game game){
		
	}
	
	@Override
	public void postGamesLoad(Game game){
		
	}
	
	@Override
	public IGame getGameModel(){
		String response = this.doGet("/game/state");
		Gson gson = new Gson();
		ClientModel cm = gson.fromJson(response, ClientModel.class);
		try {
			cm.isValid();
		} catch (InvalidTranslatorModelException e) {
			throw new RuntimeException(e.getMessage());
		}
		IGame g = this.translator.convertClientModelToGame(cm);
		return g;
	}
	
	/* (non-Javadoc)
	 * @see client.models.IProxy#postGameReset(client.models.Game)
	 */
	@Override
	public void postGameReset(Game game){
		
	}
	
	/* (non-Javadoc)
	 * @see client.models.IProxy#postGameCommands(client.models.Game)
	 */
	@Override
	public void postGameCommands(Game game){
		
	}
	
	/* (non-Javadoc)
	 * @see client.models.IProxy#getGameCommands()
	 */
	@Override
	public void getGameCommands(){
		
	}
	
	/* (non-Javadoc)
	 * @see client.models.IProxy#postAddAI(client.models.Participant)
	 */
	@Override
	public void postAddAI(Participant participant){
		
	}
	
	/* (non-Javadoc)
	 * @see client.models.IProxy#getListAI()
	 */
	@Override
	public Participant[] getListAI(){
		return null;
	}
	
	/**
	 * posts a chat message to the server
	 * @param the Chat object that needs to be posted
	 */
	@Override
	public void movesSendChat(ChatMessage chat){
		
	}
	
	/* (non-Javadoc)
	 * @see client.models.IProxy#movesRollNumber()
	 */
	@Override
	public int movesRollNumber(){
		return 0;
	}
	
	/**
	 * Sends the request to rob a certain player by giving the Thief and the Player objects
	 * @param the current thief object
	 * @param the player object being robbed
	 */
	@Override
	public void moveRobPlayer(PlayerInfo player){
		
	}
	
	/* (non-Javadoc)
	 * @see client.models.IProxy#movesFinishTurn(client.data.PlayerInfo)
	 */
	@Override
	public void movesFinishTurn(PlayerInfo player){
		
	}
	
	/**
	 * Sends a player to the server that wants to buy a dev card
	 * @param the player object that is buying a dev card
	 * @return returns the DevCard object just bought
	 */
	@Override
	public DevelopmentCard movesBuyDevCard(PlayerInfo player){
		return null;
		
	}
	
	/* (non-Javadoc)
	 * @see client.models.IProxy#movesYear_of_Plenty(client.data.PlayerInfo)
	 */
	@Override
	public void movesYear_of_Plenty(PlayerInfo player){
		
	}
	
	/* (non-Javadoc)
	 * @see client.models.IProxy#movesRoad_Building(client.data.PlayerInfo)
	 */
	@Override
	public void movesRoad_Building(PlayerInfo player){
		
	}
	
	/* (non-Javadoc)
	 * @see client.models.IProxy#movesSoldier(client.data.PlayerInfo)
	 */
	@Override
	public void movesSoldier(PlayerInfo player){
		
	}
	
	/* (non-Javadoc)
	 * @see client.models.IProxy#movesMonopoly(client.data.PlayerInfo)
	 */
	@Override
	public void movesMonopoly(PlayerInfo player){
		
	}
	
	/* (non-Javadoc)
	 * @see client.models.IProxy#movesMonument(client.data.PlayerInfo)
	 */
	@Override
	public void movesMonument(PlayerInfo player){
		
	}
	
	/**
	 * sends a Road object to the server to build it
	 * @param the road object that is to be built
	 */
	@Override
	public void movesBuildRoad(Road road){
		
	}
	
	/**
	 * Sends a settlement to be built
	 * @param the settlement object to be built
	 */
	@Override
	public void movesBuildSettlement(Settlement settlement){
		
	}
	
	/**
	 * Sends a city to built on the server
	 * @param the city object to be built 
	 */
	@Override
	public void movesBuildCity(City city){
		
	}
	
	/* (non-Javadoc)
	 * @see client.models.IProxy#movesOfferTrade(client.models.DomesticTrade)
	 */
	@Override
	public void movesOfferTrade(DomesticTrade dTrade){
		
	}
	
	/* (non-Javadoc)
	 * @see client.models.IProxy#movesAcceptTrade(client.models.DomesticTrade)
	 */
	@Override
	public void movesAcceptTrade(DomesticTrade dTrade){
		
	}
	
	/* (non-Javadoc)
	 * @see client.models.IProxy#movesMaritimeTrade(client.models.MaritimeTrade)
	 */
	@Override
	public void movesMaritimeTrade(MaritimeTrade mTrade){
		
	}
	
	/* (non-Javadoc)
	 * @see client.models.IProxy#movesdiscardCards(client.data.PlayerInfo)
	 */
	@Override
	public void movesdiscardCards(PlayerInfo player){
		
	}
	
	/**
	 * Sets the log level of the server
	 * @param the loglevel object
	 */
	@Override
	public void utilChangeLogLevel(LogLevel loglevel){
		
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
}
