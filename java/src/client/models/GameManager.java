/**
 * 
 */
package client.models;

import java.util.List;

/**
 * Needed for our server implementation for keeping track of all games.
 * @author scottdaly
 *
 */
public class GameManager {
	private List<IGame> games;

	public GameManager(List<IGame> games) {
		this.games = games;
	}

	/**
	 * @return the games
	 */
	public List<IGame> getGames() {
		return games;
	}

	/**
	 * @param games the games to set
	 */
	public void setGames(List<IGame> games) {
		this.games = games;
	}
	
	
}
