package client.models;

import java.util.List;

import client.communication.LogEntry;
import client.data.PlayerInfo;
import client.models.exceptions.CantFindPlayerException;
import client.models.translator.TRTradeOffer;
import shared.definitions.ResourceType;

public interface IFacade {

	void registerAsObserver(ICatanModelObserver domesticTradeController);

	void updatedCatanModel();

	Integer getPlayerResourceCount(ResourceType resource);

	void setCurrentUser(String user);

	IPlayer getCurrentUser() throws CantFindPlayerException;

	IPlayer getPlayerWithIndex(Integer playerToTradeWith) throws CantFindPlayerException;

	PlayerInfo[] getAllPlayerInfos();

	boolean isMyTurn();

	TRTradeOffer getCurrentTrade();

	Integer getCurrentUserIndex();
	
	List<LogEntry> getChats();

}