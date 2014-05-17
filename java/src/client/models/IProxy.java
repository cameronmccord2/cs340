package client.models;

import client.data.PlayerInfo;
import client.server.AcceptTrade;
import client.server.BuyDevCard;
import client.server.CreateGame;
import client.server.FinishedTurn;
import client.server.MaritimeTradeOff;
import client.server.OfferTrade;
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

public interface IProxy {

	/**
	 * calls the user login method on the server 
	 * @param the User object that is being logged in. Holds the username and password
	 */
	public ServerResponse postUserLogin(User user);

	/**
	 * this calls the method on the server to register a new user
	 * @param the User object that will be registered
	 */
	public ServerResponse postUserRegister(User user);

	/**
	 * This will retrieve all current games on the server
	 * @return an array of Game objects
	 */
	public ServerResponse getGamesList();

	/**
	 * Posts a new game that has just been created
	 * @param the Game object that was just created to start a new game
	 */
	public ServerResponse postGamesCreate(CreateGame game);

	/**
	 * Joins the game on the server
	 * @param The Game that this client wants to join
	 */
	public ServerResponse postGamesJoin(ServerJoinGame join);

	/**
	 * Sends a game to reset it on the server
	 * @param the game that needs to be reset
	 */
	public ServerResponse postGameReset();

	/**
	 * posts a chat message to the server
	 * @param the Chat object that needs to be posted
	 */
	public ServerResponse movesSendChat(ServerChat chat);

	/**
	 * Rolls the dice for you and returns the number rolled
	 * @return the integer rolled
	 */
	public ServerResponse movesRollNumber(ServerRoll roll);

	/**
	 * Sends the request to rob a certain player by giving the Thief and the Player objects
	 * @param the current thief object
	 * @param the player object being robbed
	 */
	public ServerResponse moveRobPlayer(ServerRobPlayer rob);

	/**
	 * Sends the player indicating that player has finished their turn
	 * @param the player object who is finishing their turn
	 */
	public ServerResponse movesFinishTurn(FinishedTurn turn);

	/**
	 * Sends a player to the server that wants to buy a dev card
	 * @param the player object that is buying a dev card
	 * @return returns the DevCard object just bought
	 */
	public ServerResponse movesBuyDevCard(BuyDevCard card);

	/**
	 * Plays a Year of Plenty card for the player passed in
	 * @param the player object playing the Year of Plenty card
	 */
	public ServerResponse movesYear_of_Plenty(ServerYearofPlenty yop);

	/**
	 * Plays a Road Building card for the specified player
	 * @param the player object that is using a Road Building card
	 */
	public ServerResponse movesRoad_Building(RoadBuilding rb);

	/**
	 * Plays a Soldier card for the specified player
	 * @param the player object that is playing the Soldier card
	 */
	public ServerResponse movesSoldier(ServerSoldier ss);

	/**
	 * Plays a monopoly card for the specified user
	 * @param the player object that is using the Monopoly card
	 */
	public ServerResponse movesMonopoly(ServerMonopoly sm);

	/**
	 * Plays a Monument card for the specified user
	 * @param the player object that is using a Monument card
	 */
	public ServerResponse movesMonument(ServerMonument sm);

	/**
	 * sends a Road object to the server to build it
	 * @param the road object that is to be built
	 */
	public ServerResponse movesBuildRoad(ServerBuildRoad br);

	/**
	 * Sends a settlement to be built
	 * @param the settlement object to be built
	 */
	public ServerResponse movesBuildSettlement(ServerBuildSettlement bs);

	/**
	 * Sends a city to built on the server
	 * @param the city object to be built 
	 */
	public ServerResponse movesBuildCity(ServerBuildCity bc);

	/**
	 * Sends a Domestic Trade object from a user that wants to trade
	 * @param the Domestic Trade object that holds the info for the potential trade
	 */
	public ServerResponse movesOfferTrade(OfferTrade ot);

	/**
	 * Sends the Domestic Trade object to signify the user accepts the trade
	 * @param the domestic trade object that is being accepted
	 */
	public ServerResponse movesAcceptTrade(AcceptTrade at);

	/**
	 * Conducts a Maritime trade by sending the maritime trade object
	 * @param the maritime trade object that holds the info for the trade
	 */
	public ServerResponse movesMaritimeTrade(MaritimeTradeOff mTrade);

	/**
	 * Discards cards because a 7 was rolled
	 * @param the player object that has to discard cards
	 */
	public ServerResponse movesdiscardCards(PlayerInfo player);

	/**
	 * Sets the log level of the server
	 * @param the loglevel object
	 */
	public ServerResponse utilChangeLogLevel(ServerLogLevel loglevel);

	/**
	 * Retrieves the latest game state
	 * @return the Game object that holds all the updated game state
	 */
	public IGame getGameModel();

	Facade getFacade();

}