package client.models;

import java.util.Map;
import java.util.List;

import shared.definitions.ResourceType;
import client.communication.LogEntry;
import client.data.PlayerInfo;
import client.models.exceptions.CantFindGameModelException;
import client.models.exceptions.CantFindPlayerException;
import client.models.translator.TRTradeOffer;
import shared.locations.HexLocation;

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
}