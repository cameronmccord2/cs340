package client.models;

import java.util.Map;

import client.data.PlayerInfo;
import client.models.exceptions.CantFindGameModelException;
import client.models.exceptions.CantFindPlayerException;
import client.models.translator.TRTradeOffer;

public interface IFacade {

	void registerAsObserver(ICatanModelObserver domesticTradeController);

	void updatedCatanModel();

	void setCurrentUser(String user);

	IPlayer getCurrentUser() throws CantFindGameModelException, CantFindPlayerException;

	IPlayer getPlayerWithIndex(Integer playerToTradeWith) throws CantFindPlayerException, CantFindGameModelException;

	PlayerInfo[] getAllPlayerInfos() throws CantFindGameModelException;

	boolean isMyTurn() throws CantFindGameModelException;

	TRTradeOffer getCurrentTrade() throws CantFindGameModelException;

	Integer getCurrentUserIndex() throws CantFindGameModelException;

	Map<IResourceCard, Integer> getResourcesForPlayerId(Integer reciever) throws CantFindPlayerException, CantFindGameModelException;

	Integer getBankResourceCount(ResourceCard resource) throws CantFindGameModelException;

	Integer getPlayerResourceCount(ResourceCard resource) throws CantFindGameModelException;

}