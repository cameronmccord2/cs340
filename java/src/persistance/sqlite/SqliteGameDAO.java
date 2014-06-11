package persistance.sqlite;

import java.sql.Connection;
import java.util.List;

import client.models.interfaces.IGame;

/**
 * The Class SqliteGameDAO.
 */
public class SqliteGameDAO {
	
	/**
	 * Insert new game into the sqlite db. This will save the game under the new game column.
	 *
	 * @param connection the connection to talk to the sqlite db on
	 * @param game the game to insert
	 */
	public void insertNewGame(Connection connection, IGame game){
		
	}
	
	/**
	 * Updates the game on the sqlite db
	 *
	 * @param connection the connection to talk to the sqlite db on
	 * @param game the game to update
	 */
	public void updateGame(Connection connection, IGame game){
		
	}
	
	/**
	 * Delete game by id.
	 *
	 * @param connection the connection to talk to the sqlite db on
	 * @param gameId the game id to delete
	 */
	public void deleteGameById(Connection connection, Integer gameId){
		
	}
	
	/**
	 * Gets the all games in this sqlite db
	 *
	 * @return all the games in the sqlite store
	 */
	public List<IGame> getAllGames(){
		return null;
	}
}
