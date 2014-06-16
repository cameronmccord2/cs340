package persistence.datafile;

import client.models.interfaces.IGame;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class dataFileGameDAO.
 */
public class DataFileGameDAO {

	private String gamesDir;
	private String gameFilenameFormat;

	public DataFileGameDAO() {
		gamesDir = "games";
		gameFilenameFormat = gamesDir + File.separator + "gameId%dstate%s.catanGameData";
		File gameDir = new File(gamesDir);

		// if the directory does not exist, create it
		if (!gameDir.exists()) {
			boolean success = gameDir.mkdir();
			if(!success)
				System.out.println("Couldn't make games directory");
		}
	}

	/**
	 * Write a new game to a dataFile. Extracts the gameId from the game and saves the
	 * game in the following format:
	 * filename: games/gameId.dat
	 * serializedGameData
	 *
	 * @param game the game to insert
	 */
	public void insertNewGame(IGame game){
		int gameId = game.getGameInfo().getId();

		String filename = String.format(gameFilenameFormat, gameId, "beginning");

		File dataFile = new File(filename);

		// No need to check if game exists, we'll just overwrite it anyway
		try {
			OutputStream os = new FileOutputStream(dataFile, false);
			OutputStream buffer = new BufferedOutputStream(os);
			ObjectOutput output = new ObjectOutputStream(buffer);
			dataFile.createNewFile();

			output.writeObject(game);

			output.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Updates the saved game. Extracts the gameId, searches for the matching dataFile
	 * and overwrites the data in file with the new serialized game.
	 *
	 * @param game the game to update
	 */
	public void updateGame(IGame game){
		int gameId = game.getGameInfo().getId();

		String filename = String.format(gameFilenameFormat, gameId, "current");

		File dataFile = new File(filename);

		// No need to check if game exists, we'll just overwrite it anyway
		try {
			OutputStream os = new FileOutputStream(dataFile, false);
			OutputStream buffer = new BufferedOutputStream(os);
			ObjectOutput output = new ObjectOutputStream(buffer);
			dataFile.createNewFile();

			output.writeObject(game);

			output.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Delete game by id.
	 *
	 * @param gameId the game id to delete
	 */
	public void deleteGameById(Integer gameId){

		String filename;
		File gameFile;

		// Delete beginning game if exists
		filename = String.format(gameFilenameFormat, gameId, "beginning");
		gameFile = new File(filename);
		gameFile.delete();

		// Delete current game if exists
		filename = String.format(gameFilenameFormat, gameId, "current");
		gameFile = new File(filename);
		gameFile.delete();
	}
	
	/**
	 * Gets the all games found in the dataFiles
	 *
	 * @return List of all games found.
	 */
	public List<IGame> getAllGames(){

		List<IGame> gamesList = new ArrayList<>();
		byte[] gameData;

		for (File dataFile : new File(gamesDir).listFiles()) {

			// TODO Check if a current game exists before returning the beginning game
			if (dataFile.isFile()) {
				try {
					gameData = Files.readAllBytes(Paths.get(dataFile.getPath()));
					ByteArrayInputStream bis = new ByteArrayInputStream(gameData);
					ObjectInput in = new ObjectInputStream(bis);
					IGame game = (IGame)in.readObject();

					gamesList.add(game);

				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}

		return gamesList;

	}
}
