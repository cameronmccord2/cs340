package client.models;

/**
 * The Game interface
 * @author scottdaly
 *
 */
public interface IGame {
	
	/**
	 * Generate a random number for the die
	 * @return returns the number that was rolled 
	 */
	int rollDie();
	
	/**
	 * First pull game state and set the models.
	 */
	void startGame();
	
	/**
	 * Pulls in objects that will be sent to appropriate models to update 
	 */
	void updateGameState(Object ob);
}
