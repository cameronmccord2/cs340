/**
 * 
 */
package client.models;

import client.data.GameInfo;

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
	
	
	public Game() {
		
	}
	
	public Game(GameInfo gameInfo, ICatanMap map, IPlayer[] players){
		this.gameInfo = gameInfo;
		this.map = map;
		this.players = players;
		this.modelVersion = 0;
	}

	@Override
	public int rollDie() {
		return 0;
	}
	
	@Override
	public void startGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateGameState(Object ob) {
		// TODO Auto-generated method stub
		
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
		builder.append(", map=");
		builder.append(map);
		builder.append(", players=");
		builder.append(players);
		builder.append(", modelVersion=");
		builder.append(modelVersion);
		builder.append(", bank=");
		builder.append(bank);
		builder.append("]");
		return builder.toString();
	}
	
}
