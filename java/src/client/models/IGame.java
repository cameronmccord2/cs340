package client.models;

import client.data.GameInfo;
import client.models.translator.TRTradeOffer;

/**
 * The Game interface
 * @author scottdaly
 *
 */
public interface IGame {

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

	MessageList getLog();

	void setLog(MessageList log);

	MessageList getChat();

	void setChat(MessageList chat);

	void addChat(MessageLine line);

	void addLog(MessageLine line);

	TurnTracker getTurnTracker();

	void setTurnTracker(TurnTracker turnTracker);

	Integer getWinner();

	void setWinner(Integer winner);

	TRTradeOffer getCurrentTrade();

	void setCurrentTrade(TRTradeOffer currentTrade);
}
