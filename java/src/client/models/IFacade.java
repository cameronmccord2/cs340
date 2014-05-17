package client.models;

import client.models.exceptions.CantFindPlayerException;
import shared.definitions.ResourceType;

public interface IFacade {

	void registerAsObserver(ICatanModelObserver domesticTradeController);

	void updatedCatanModel();

	Integer getPlayerResourceCount(ResourceType resource);

	void setCurrentUser(String user);

	IPlayer getCurrentUser() throws CantFindPlayerException;

	IParticipant getPlayerWithIndex(Integer playerToTradeWith) throws CantFindPlayerException;

}