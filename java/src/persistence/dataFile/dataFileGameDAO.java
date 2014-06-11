package persistence.dataFile;

import client.models.interfaces.IGame;

import java.util.List;

/**
 * The Class dataFileGameDAO.
 */
public class dataFileGameDAO {
	
	/**
	 * Write a new game to a dataFile. Extracts the gameId from the game and saves the
	 * game in the following format:
	 * filename: games/gameId.dat
	 * serializedGameData
	 *
	 * @param game the game to insert
	 */
	public void insertNewGame(IGame game){
		
	}
	
	/**
	 * Updates the saved game. Extracts the gameId, searches for the matching dataFile
	 * and overwrites the data in file with the new serialized game.
	 *
	 * @param game the game to update
	 */
	public void updateGame(IGame game){
		
	}
	
	/**
	 * Delete game by id.
	 *
	 * @param gameId the game id to delete
	 */
	public void deleteGameById(Integer gameId){
		
	}
	
	/**
	 * Gets the all games found in the dataFiles
	 *
	 * @return List of all games found.
	 */
	public List<IGame> getAllGames(){
		return null;
	}
}
