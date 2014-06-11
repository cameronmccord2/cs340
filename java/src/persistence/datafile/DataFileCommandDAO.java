package persistence.datafile;

import server.commands.ICommand;

import java.util.List;

/**
 * The Class dataFileCommandDAO.
 */
public class DataFileCommandDAO {

	/**
	 * Gets the commands for game id.
	 *
	 * @param gameId the game id
	 * @return the commands for game id
	 */
	public List<ICommand> getCommandsForGameId(Integer gameId){
		return null;
	}
	
	/**
	 * Save command for game id in the following format:
	 * filename: commands/gameId.dat
	 * serializedCommandData1
	 * serializedCommandData2
	 * ...
	 *
	 * @param command the command to save
	 * @param gameId the game id to save the command for
	 */
	public void saveCommandForGameId(ICommand command, Integer gameId){
		
	}
	
	/**
	 * Count commands for game id.
	 *
	 * @param gameId the game id
	 * @return the count of the current commands for a game
	 */
	public Integer countCommandsForGameId(Integer gameId){
		return null;
	}
	
	/**
	 * Delete oldest x commands for the game by id.
	 *
	 * @param count the count to delete
	 * @param gameId the game id
	 */
	public void deleteOldestXCommandsForGameId(Integer count, Integer gameId){
		
	}
}
