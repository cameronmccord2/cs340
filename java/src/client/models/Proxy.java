package client.models;

import client.data.PlayerInfo;

/**
 * The Proxy class acts as a proxy for the real server and has similar methods that are found on the server. 
 * @author scottdaly
 *
 */
public class Proxy {

	public Proxy() {
		
	}
	
	/**
	 * calls the user login method on the server 
	 * @param the User object that is being logged in 
	 */
	public void postUserLogin(User user){
		
	}
	
	/**
	 * this calls the method on the server to register a new user
	 * @param the User object that will be registered
	 */
	public void postUserRegister(User user){
		
	}
	
	/**
	 * This will retrieve all current games on the server
	 * @return an array of Game objects
	 */
	public Game[] getGamesList(){
		return null;
	}
	
	/**
	 * Posts a new game that has just been created
	 * @param the Game object that was just created to start a new game
	 */
	public void postGamesCreate(Game game){
		
	}
	
	/**
	 * Joins the game on the server
	 * @param The Game that this client wants to join
	 */
	public void postGamesJoin(Game game){
		
	}
	
	/**
	 * Saves the game state
	 * @param the game state to be saved
	 */
	public void postGamesSave(Game game){
		
	}
	
	/**
	 * Loads the game that the client wants to start and sends it to the server
	 * @param the game that needs to be loaded
	 */
	public void postGamesLoad(Game game){
		
	}
	
	/**
	 * Retrieves the latest game state
	 * @return the Game object that holds all the updated game state
	 */
	public Game getGameModel(){
		return null;
	}
	
	/**
	 * Sends a game to reset it on the server
	 * @param the game that needs to be reset
	 */
	public void postGameReset(Game game){
		
	}
	
	/**
	 * Not sure what this one does...
	 * @param game
	 */
	public void postGameCommands(Game game){
		
	}
	
	/**
	 * Not sure what this one does...
	 */
	public void getGameCommands(){
		
	}
	
	/**
	 * This will post an AI participant
	 * @param the participant AI that needs to be posted
	 */
	public void postAddAI(Participant participant){
		
	}
	
	/**
	 * returns a list of AI participants
	 * @return the list of AI participants
	 */
	public Participant[] getListAI(){
		return null;
	}
	
	/**
	 * posts a chat message to the server
	 * @param the Chat object that needs to be posted
	 */
	public void movesSendChat(Chat chat){
		
	}
	
	/**
	 * Rolls the dice for you and returns the number rolled
	 * @return the integer rolled
	 */
	public int movesRollNumber(){
		return 0;
	}
	
	/**
	 * Sends the request to rob a certain player by giving the Thief and the Player objects
	 * @param the current thief object
	 * @param the player object being robbed
	 */
	public void moveRobPlayer(Thief thief, PlayerInfo player){
		
	}
	
	/**
	 * Sends the player indicating that player has finsihed their turn
	 * @param the player object who is finishing their turn
	 */
	public void movesFinishTurn(PlayerInfo player){
		
	}
	
	/**
	 * Sends a player to the server that wants to buy a dev card
	 * @param the player object that is buying a dev card
	 * @return returns the DevCard object just bought
	 */
	public DevCard movesBuyDevCard(PlayerInfo player){
		
	}
	
	/**
	 * Plays a Year of Plenty card for the player passed in
	 * @param the player object playing the Year of Plenty card
	 */
	public void movesYear_of_Plenty(PlayerInfo player){
		
	}
	
	/**
	 * Plays a Road Building card for the specified player
	 * @param the player object that is using a Road Building card
	 */
	public void movesRoad_Building(PlayerInfo player){
		
	}
	
	/**
	 * PLays a Soldier card for the specified player
	 * @param the player object that is playing the Soldier card
	 */
	public void movesSoldier(PlayerInfo player){
		
	}
	
	/**
	 * Plays a monopoly card for the specified user
	 * @param the player object that is using the Monopoly card
	 */
	public void movesMonopoly(PlayerInfo player){
		
	}
	
	/**
	 * Plays a Monument card for the specified user
	 * @param the player object that is using a Monument card
	 */
	public void movesMonument(PlayerInfo player){
		
	}
	
	/**
	 * sends a Road object to the server to build it
	 * @param the road object that is to be built
	 */
	public void movesBuildRoad(Road road){
		
	}
	
	/**
	 * Sends a settlement to be built
	 * @param the settlement object to be built
	 */
	public void movesBuildSettlement(Settlement settlement){
		
	}
	
	/**
	 * Sends a city to built on the server
	 * @param the city object to be built 
	 */
	public void movesBuildCity(City city){
		
	}
	
	/**
	 * Sends a Domestic Trade object from a user that wants to trade
	 * @param the Domestic Trade object that holds the info for the potential trade
	 */
	public void movesOfferTrade(DomesticTrade dTrade){
		
	}
	
	/**
	 * Sends the Domestic Trade object to signify the user accepts the trade
	 * @param the domestic trade object that is being accepted
	 */
	public void movesAcceptTrade(DomesticTrade dTrade){
		
	}
	
	/**
	 * Conducts a Maritime trade by sending the maritime trade object
	 * @param the meritime trade object that holds the info for the trade
	 */
	public void movesMaritimeTrade(MaritimeTrade mTrade){
		
	}
	
	/**
	 * Discards cards because a 7 was rolled
	 * @param the player object that has to discard cards
	 */
	public void movesdiscardCards(PlayerInfo player){
		
	}
	
	/**
	 * Sets the log level of the server
	 * @param the loglevel object
	 */
	public void utilChangeLogLevel(LogLevel loglevel){
		
	}
}
