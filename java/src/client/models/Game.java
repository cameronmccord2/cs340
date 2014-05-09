/**
 * 
 */
package client.models;

import java.util.Map;

/**
 * The Game class will hold the map, a dice object, and the list of participants.
 * @author scottdaly
 *
 */
public class Game implements IGame{
	
	private Map map;
	private Die die;
	private Participant participants[];
	
	public Game() {
		
	}

	@Override
	public int rollDie() {
		
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
	 * @return the dice
	 */
	public Die getDie() {
		return die;
	}

	/**
	 * @param dice the dice to set
	 */
	public void setDie(Die die) {
		this.die = die;
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
