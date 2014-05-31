package client.models;

import java.util.List;
import java.util.Map;

import server.commands.ICommandParams;
import server.models.UserAttributes;
import shared.definitions.ResourceType;
import shared.locations.HexLocation;
import client.communication.LogEntry;
import client.data.PlayerInfo;
import client.models.exceptions.CantFindGameModelException;
import client.models.exceptions.CantFindPlayerException;
import client.models.translator.TRTradeOffer;

public interface IFacade {

	void registerAsObserver(ICatanModelObserver domesticTradeController);

	void updatedCatanModel();

	void setCurrentUser(String user);

	IPlayer getCurrentUser() throws CantFindGameModelException, CantFindPlayerException;

	PlayerInfo[] getAllPlayerInfos() throws CantFindGameModelException;

	boolean isMyTurn() throws CantFindGameModelException;

	TRTradeOffer getCurrentTrade() throws CantFindGameModelException;

	Integer getCurrentUserIndex() throws CantFindGameModelException;
	
	List<LogEntry> getChats();
	
	List<LogEntry> getGameHistory();

	Integer getBankResourceCount(ResourceCard resource) throws CantFindGameModelException;

	Integer getPlayerResourceCount(ResourceCard resource) throws CantFindGameModelException;

	Integer getWinner() throws CantFindGameModelException;

	TurnTracker getTurnTracker() throws CantFindGameModelException;

	String getCurrentState() throws CantFindGameModelException;

	ICatanMap getCatanMap() throws CantFindGameModelException;
	
	IPlayer[] getPlayers() throws CantFindGameModelException;

    HexLocation getRobberLocation() throws CantFindGameModelException;

	Integer getMaritimeTradeAmountForResource(ResourceType resource) throws CantFindPlayerException,
																			CantFindGameModelException;

	IPlayer getPlayerWithPlayerIndex(Integer playerIndex) throws CantFindPlayerException, CantFindGameModelException;

	Map<IResourceCard, Integer> getResourcesForPlayerIndex(Integer playerIndex) throws CantFindPlayerException, CantFindGameModelException;

	Map<IDevelopmentCard, Integer> getDevCardsForPlayerIndex(Integer playerIndex) throws CantFindPlayerException, CantFindGameModelException;

	
	//The following are methods are for the facade for server implementation
	/**
	 * This is called from the command objects and posts a new chat to the model
	 * @param commandParams
	 * @param userAttributes
	 * @return a string indicating success or failure
	 */
	String postChat(ICommandParams commandParams, UserAttributes userAttributes);
	
	/**
	 * This is called from the command objects and changes the model according to the number rolled
	 * @param commandParams
	 * @param userAttributes
	 * @return a string indicating success or failure
	 */
	String rollNumber(ICommandParams commandParams, UserAttributes userAttributes);
	
	/**
	 * This is called from the command objects and changes the current player
	 * @param commandParams
	 * @param userAttributes
	 * @return a string indicating success or failure
	 */
	String finishTurn(ICommandParams commandParams, UserAttributes userAttributes);
}