package client.models;

import shared.definitions.ResourceType;

public interface IFacade {

	void registerAsObserver(ICatanModelObserver domesticTradeController);

	void updatedCatanModel();

	Integer getPlayerResourceCount(ResourceType resource);

	void setCurrentUser(String user);

}