/**
 * 
 */
package client.models;

import server.models.exceptions.GameModelException;
import client.data.GameInfo;
import client.models.translator.TRTradeOffer;

/**
 * The Game class will hold the map, a dice object, and the list of participants.
 * @author scottdaly
 *
 */
public class Game implements IGame{
	
	private GameInfo gameInfo;
	private ICatanMap map;
	private IPlayer players[];
	private Integer modelVersion;
	private IBank bank;
	private MessageList log;
	private MessageList chat;
	private TurnTracker turnTracker;
	private Integer winner;
	private TRTradeOffer currentTrade;
	
	private int version;
	
	public Game() {
		
	}
	
	public Game(GameInfo gameInfo, ICatanMap map, IPlayer[] players){
		this.gameInfo = gameInfo;
		this.map = map;
		this.players = players;
		this.modelVersion = 0;
	}

	@Override
	public GameInfo getGameInfo() {
		return gameInfo;
	}

	@Override
	public void setGameInfo(GameInfo gameInfo) {
		this.gameInfo = gameInfo;
	}

	@Override
	public ICatanMap getMap() {
		return map;
	}

	@Override
	public void setMap(ICatanMap map) {
		this.map = map;
	}

	@Override
	public Integer getModelVersion() {
		return modelVersion;
	}

	@Override
	public void setModelVersion(Integer modelVersion) {
		this.modelVersion = modelVersion;
	}

	@Override
	public IPlayer[] getPlayers() {
		return players;
	}

	@Override
	public void setPlayers(IPlayer[] players) {
		this.players = players;
	}
	
	public void addPlayer(Player player){
		this.players[this.players.length] = player;
	}

	@Override
	public IBank getBank() {
		return bank;
	}

	@Override
	public void setBank(IBank bank) {
		this.bank = bank;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Game [gameInfo=");
		builder.append(gameInfo);
		builder.append(", \n\tmap=");
		builder.append(map);
		builder.append(", \n\tplayers=");
		builder.append(players);
		builder.append(", \n\tmodelVersion=");
		builder.append(modelVersion);
		builder.append(", \n\tbank=");
		builder.append(bank);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public MessageList getLog() {
		return log;
	}

	@Override
	public void setLog(MessageList log) {
		this.log = log;
	}

	@Override
	public MessageList getChat() {
		return chat;
	}

	@Override
	public void setChat(MessageList chat) {
		this.chat = chat;
	}
	
	@Override
	public void addChat(MessageLine line){
		this.chat.addLine(line);
	}
	
	@Override
	public void addLog(MessageLine line){
		this.log.addLine(line);
	}

	@Override
	public TurnTracker getTurnTracker() {
		return turnTracker;
	}

	@Override
	public void setTurnTracker(TurnTracker turnTracker) {
		this.turnTracker = turnTracker;
	}

	@Override
	public Integer getWinner() {
		return winner;
	}

	@Override
	public void setWinner(Integer winner) {
		this.winner = winner;
	}

	@Override
	public TRTradeOffer getCurrentTrade() {
		return currentTrade;
	}

	@Override
	public void setCurrentTrade(TRTradeOffer currentTrade) {
		this.currentTrade = currentTrade;
	}

	/**
	 * Needed for our server implementation
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * Needed for our server implementation
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public IPlayer getPlayerForPlayerIndex(Integer playerIndex) throws GameModelException {
		for (IPlayer p : this.players) {
			if(p.getPlayerInfo().getPlayerIndex() == playerIndex)
				return p;
		}
		throw new GameModelException("Cant find player by index in this game: " + playerIndex);
	}
	
	
}
