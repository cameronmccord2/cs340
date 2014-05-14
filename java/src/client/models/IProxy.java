package client.models;

import client.data.PlayerInfo;
import client.server.CreateGame;
import client.server.GameServer;
import client.server.SaveGame;
import client.server.ServerJoinGame;
import client.server.User;

public interface IProxy {

	/**
	 * calls the user login method on the server 
	 * @param the User object that is being logged in. Holds the username and password
	 */
	public String postUserLogin(User user);

	/**
	 * this calls the method on the server to register a new user
	 * @param the User object that will be registered
	 */
	public String postUserRegister(User user);

	/**
	 * This will retrieve all current games on the server
	 * @return an array of Game objects
	 */
	public GameServer[] getGamesList();

	/**
	 * Posts a new game that has just been created
	 * @param the Game object that was just created to start a new game
	 */
	public String postGamesCreate(CreateGame game);

	/**
	 * Joins the game on the server
	 * @param The Game that this client wants to join
	 */
	public String postGamesJoin(ServerJoinGame join);

	/**
	 * Saves the game state
	 * @param the game state to be saved
	 */
	public String postGamesSave(SaveGame game);

	/**
	 * Loads the game that the client wants to start and sends it to the server
	 * @param the game that needs to be loaded
	 */
	public abstract void postGamesLoad(Game game);

	/**
	 * Retrieves the latest game state
	 * @return the Game object that holds all the updated game state
	 */
	public abstract IGame getGameModel();

	/**
	 * Sends a game to reset it on the server
	 * @param the game that needs to be reset
	 */
	public abstract void postGameReset(Game game);

	/**
	 * Not sure what this one does...
	 * @param game
	 */
	public abstract void postGameCommands(Game game);

	/**
	 * Not sure what this one does...
	 */
	public abstract void getGameCommands();

	/**
	 * This will post an AI participant
	 * @param the participant AI that needs to be posted
	 */
	public abstract void postAddAI(Participant participant);

	/**
	 * returns a list of AI participants
	 * @return the list of AI participants
	 */
	public abstract Participant[] getListAI();

	/**
	 * posts a chat message to the server
	 * @param the Chat object that needs to be posted
	 */
	public abstract void movesSendChat(ChatMessage chat);

	/**
	 * Rolls the dice for you and returns the number rolled
	 * @return the integer rolled
	 */
	public abstract int movesRollNumber();

	/**
	 * Sends the request to rob a certain player by giving the Thief and the Player objects
	 * @param the current thief object
	 * @param the player object being robbed
	 */
	public abstract void moveRobPlayer(PlayerInfo player);

	/**
	 * Sends the player indicating that player has finsihed their turn
	 * @param the player object who is finishing their turn
	 */
	public abstract void movesFinishTurn(PlayerInfo player);

	/**
	 * Sends a player to the server that wants to buy a dev card
	 * @param the player object that is buying a dev card
	 * @return returns the DevCard object just bought
	 */
	public abstract DevelopmentCard movesBuyDevCard(PlayerInfo player);

	/**
	 * Plays a Year of Plenty card for the player passed in
	 * @param the player object playing the Year of Plenty card
	 */
	public abstract void movesYear_of_Plenty(PlayerInfo player);

	/**
	 * Plays a Road Building card for the specified player
	 * @param the player object that is using a Road Building card
	 */
	public abstract void movesRoad_Building(PlayerInfo player);

	/**
	 * PLays a Soldier card for the specified player
	 * @param the player object that is playing the Soldier card
	 */
	public abstract void movesSoldier(PlayerInfo player);

	/**
	 * Plays a monopoly card for the specified user
	 * @param the player object that is using the Monopoly card
	 */
	public abstract void movesMonopoly(PlayerInfo player);

	/**
	 * Plays a Monument card for the specified user
	 * @param the player object that is using a Monument card
	 */
	public abstract void movesMonument(PlayerInfo player);

	/**
	 * sends a Road object to the server to build it
	 * @param the road object that is to be built
	 */
	public abstract void movesBuildRoad(Road road);

	/**
	 * Sends a settlement to be built
	 * @param the settlement object to be built
	 */
	public abstract void movesBuildSettlement(Settlement settlement);

	/**
	 * Sends a city to built on the server
	 * @param the city object to be built 
	 */
	public abstract void movesBuildCity(City city);

	/**
	 * Sends a Domestic Trade object from a user that wants to trade
	 * @param the Domestic Trade object that holds the info for the potential trade
	 */
	public abstract void movesOfferTrade(DomesticTrade dTrade);

	/**
	 * Sends the Domestic Trade object to signify the user accepts the trade
	 * @param the domestic trade object that is being accepted
	 */
	public abstract void movesAcceptTrade(DomesticTrade dTrade);

	/**
	 * Conducts a Maritime trade by sending the maritime trade object
	 * @param the meritime trade object that holds the info for the trade
	 */
	public abstract void movesMaritimeTrade(MaritimeTrade mTrade);

	/**
	 * Discards cards because a 7 was rolled
	 * @param the player object that has to discard cards
	 */
	public abstract void movesdiscardCards(PlayerInfo player);

	/**
	 * Sets the log level of the server
	 * @param the loglevel object
	 */
	public abstract void utilChangeLogLevel(LogLevel loglevel);

}