package client.models;

import client.data.GameInfo;

/**
 * The Game interface
 * @author scottdaly
 *
 */
public interface IGame {
	
	/**
	 * First pull game state and set the models.
	 */
	void startGame();
	
	/**
	 * Pulls in objects that will be sent to appropriate models to update 
	 */
	void updateGameState(Object ob);

	void setBank(IBank bank);

	IBank getBank();

	void setPlayers(IPlayer[] players);

	IPlayer[] getPlayers();

	void setModelVersion(Integer modelVersion);

	Integer getModelVersion();

	void setMap(ICatanMap map);

	ICatanMap getMap();

	GameInfo getGameInfo();

	void setGameInfo(GameInfo gameInfo);
}
