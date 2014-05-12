package client.models;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import client.data.PlayerInfo;

/**
 * The Proxy class acts as a proxy for the real server and has similar methods that are found on the server. 
 * @author scottdaly
 *
 */
public class Proxy implements IProxy {

	public Proxy() {
		System.out.println("PROXY STARTED");
	}
	
	/**
	 * calls the user login method on the server 
	 * @param the User object that is being logged in 
	 */
	@Override
	public String postUserLogin(PlayerInfo player){
		Object obj = doPost("/user/login", player);
		return null;
	}
	
	/**
	 * this calls the method on the server to register a new user
	 * @param the User object that will be registered
	 */
	@Override
	public void postUserRegister(PlayerInfo player){
		
	}
	
	/* (non-Javadoc)
	 * @see client.models.IProxy#getGamesList()
	 */
	@Override
	public Game[] getGamesList(){
		return null;
	}
	
	/* (non-Javadoc)
	 * @see client.models.IProxy#postGamesCreate(client.models.Game)
	 */
	@Override
	public void postGamesCreate(Game game){
		
	}
	
	/* (non-Javadoc)
	 * @see client.models.IProxy#postGamesJoin(client.models.Game)
	 */
	@Override
	public void postGamesJoin(Game game){
		
	}
	
	/* (non-Javadoc)
	 * @see client.models.IProxy#postGamesSave(client.models.Game)
	 */
	@Override
	public void postGamesSave(Game game){
		
	}
	
	/* (non-Javadoc)
	 * @see client.models.IProxy#postGamesLoad(client.models.Game)
	 */
	@Override
	public void postGamesLoad(Game game){
		
	}
	
	/* (non-Javadoc)
	 * @see client.models.IProxy#getGameModel()
	 */
	@Override
	public Game getGameModel(){
		return null;
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
	public void moveRobPlayer(Thief thief, PlayerInfo player){
		
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
	
	private Object doPost(String urlPath, Object postData) {
		try { 
			System.out.println("trying to connect");
			 URL url = new URL("http://localhost:8081" + urlPath); 
			 HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			 System.out.println("1");
			 connection.setRequestMethod("POST");
			 System.out.println("2");
			 connection.setDoOutput(true);
			 connection.connect();
			 System.out.println("3");
			 
			 XStream xstream = new XStream(new DomDriver());
			 
			 //Write request body to OutputStream
			 OutputStream requestBody = connection.getOutputStream();  
			 xstream.toXML(postData,requestBody);
			 requestBody.close();
			 System.out.println("4");
			 
			 int response = connection.getResponseCode();
			 System.out.println("5");
			 if (response == HttpURLConnection.HTTP_OK) { 
				 System.out.println("ok");
				 //Read response body from InputStream
				 InputStream responseBody = connection.getInputStream(); 
				 Object obj = xstream.fromXML(responseBody);
				 responseBody.close();
				 return obj;
			 } 
			 else if(response == -1){
				 System.out.println("nope");
				 return null;
			 } 
		}catch(Exception e){
			//e.printStackTrace();
			return null;
		}
		return null;
	}
}
