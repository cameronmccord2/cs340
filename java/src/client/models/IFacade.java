package client.models;

public interface IFacade {

	void registerAsObserver(ICatanModelObserver domesticTradeController);

	void updatedCatanModel();

}