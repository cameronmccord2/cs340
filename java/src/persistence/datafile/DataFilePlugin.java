package persistence.datafile;

import client.models.User;
import client.models.interfaces.IGame;
import persistence.IPlugin;
import server.commands.ICommand;

import java.util.List;

public class DataFilePlugin implements IPlugin {
	
	private Integer n;
	
	public dataFilePlugin(){}

	public DataFilePlugin(Integer n){
		this.n = n;
	}

	/**
	 * Private utility function for reading files by filename
	 * @param filename
	 * @return
	 */
	private String readFile(String filename) {
		return null;
	}

	/**
	 * Private utility function for writing files by filename
	 * @param filename
	 * @return
	 */
	private String writeFile(String filename) {
		return null;
	}

	/**
	 * Adds the supplied user to the users directory. If the file already exists then it gets replaced
	 *
	 * @param user the user to save
	 */
	@Override
	public void addUser(User user) {
		System.out.println("inside plugin");
	}
	
	/**
	 * Adds the command to the game in dataFile. This will keep up to n commands, as specified in the constructor.
	 * A snapshot of the game will be made every n commands.
	 *
	 * @param command the command to add. If null then we will assume this is a new game and will save it as a new game
	 * @param game the game to add the command to
	 */
	@Override
	public void addCommandToGame(ICommand command, IGame game){
		
	}

	/**
	 * Gets the game associated with the provided gameId
	 *
	 * @param id ID of the game to search for
	 * @return IGame contains all game model data
	 */
	public IGame getGameById(int id) {
		return null;
	}

	/**
	 * Gets the users that have been saved to the users directory
	 *
	 * @return the users that this store has had registered
	 */
	@Override
	public List<User> getRegisteredUsers(){
		return null;
	}
	
	/**
	 * Gets the games that have been saved to dataFiles
	 *
	 * @return the games that have been saved
	 */
	@Override
	public List<IGame> getGames(){
		return null;
	}
	
	/**
	 * Gets the commands for game by the id that have been saved to dataFiles.
	 *
	 * @param gameId the gameId to get the commands for
	 * @return the commands for gameId
	 */
	@Override
	public List<ICommand> getCommandsForGameId(Integer gameId){
		return null;
	}

	@Override
	public void createGame(IGame game)
	{
		// TODO Auto-generated method stub
		
	}
}
