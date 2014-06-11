package persistance.sqlite;

import java.sql.Connection;
import java.util.List;

import server.commands.ICommand;

/**
 * The Class SqliteCommandDAO.
 */
public class SqliteCommandDAO {

	/**
	 * Gets the commands for game id.
	 *
	 * @param connection the connection to talk to the sqlite db on
	 * @param gameId the game id
	 * @return the commands for game id
	 */
	public List<ICommand> getCommandsForGameId(Connection connection, Integer gameId){
		return null;
	}
	
	/**
	 * Save command for game id.
	 *
	 * @param connection the connection to talk to the sqlite db on
	 * @param command the command to save
	 * @param gameId the game id to save the command for
	 */
	public void saveCommandForGameId(Connection connection, ICommand command, Integer gameId){
		
	}
	
	/**
	 * Count commands for game id.
	 *
	 * @param connection the connection to talk to the sqlite db on
	 * @param gameId the game id
	 * @return the count of the current commands for a game
	 */
	public Integer countCommandsForGameId(Connection connection, Integer gameId){
		return null;
	}
	
	/**
	 * Delete oldest x commands for the game by id.
	 *
	 * @param connection the connection to talk to the sqlite db on
	 * @param count the count to delete
	 * @param gameId the game id
	 */
	public void deleteOldestXCommandsForGameId(Connection connection, Integer count, Integer gameId){
		
	}
}
