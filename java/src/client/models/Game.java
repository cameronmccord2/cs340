/**
 * 
 */
package client.models;

import java.util.Map;

import client.data.GameInfo;

/**
 * The Game class will hold the map, a dice object, and the list of participants.
 * @author scottdaly
 *
 */
public class Game implements IGame{
	
	private GameInfo gameInfo;
	private Map map;
	private Participant participants[];
	
	public Game() {
		
	}

	@Override
	public int rollDie() {
		return 0;
		
	}
	
	/**
	 * @return the gameInfo
	 */
	public GameInfo getGameInfo() {
		return gameInfo;
	}

	/**
	 * @param gameInfo the gameInfo to set
	 */
	public void setGameInfo(GameInfo gameInfo) {
		this.gameInfo = gameInfo;
	}

	/**
	 * @return the map
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(Map map) {
		this.map = map;
	}

	/**
	 * @return the participants
	 */
	public Participant[] getParticipants() {
		return participants;
	}

	/**
	 * @param participants the participants to set
	 */
	public void setParticipants(Participant[] participants) {
		this.participants = participants;
	}

	@Override
	public void startGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateGameState(Object ob) {
		// TODO Auto-generated method stub
		
	}
	
}
