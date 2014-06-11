package persistance.sqlite;

import java.util.List;

import persistance.IPlugin;
import server.commands.ICommand;
import client.models.User;
import client.models.interfaces.IGame;
import client.models.interfaces.IParticipant;

public class SQLitePlugin implements IPlugin {
	
	private Integer n;

	public SQLitePlugin(Integer n){
		this.n = n;
	}

	/**
	 * Adds the supplied user to the sqlite database. If it already exists then it gets updated in the db
	 *
	 * @param user the user to save
	 */
	@Override
	public void addUser(User user) {

	}
	
	/**
	 * Adds the command to the game in the sqlite db. This will keep up to n commands, as specified in the constructor. A snapshot of the game will be made every n commands.
	 *
	 * @param command the command to add. If null then we will assume this is a new game and will save it as a new game
	 * @param Igame the game to add the command to
	 */
	@Override
	public void addCommandToGame(ICommand command, IGame game){
		
	}
	
	/**
	 * Gets the users that have been registered with this sqlite db
	 *
	 * @return the users that this store has had registered
	 */
	@Override
	public List<User> getRegisteredUsers(){
		return null;
	}
	
	/**
	 * Gets the games that have been saved to this sqlite db
	 *
	 * @return the games that have been saved
	 */
	@Override
	public List<IGame> getGames(){
		return null;
	}
	
	/**
	 * Gets the commands for game by the id that have been saved in this sqlite db.
	 *
	 * @param gameId the gameId to get the commands for
	 * @return the commands for gameId
	 */
	@Override
	public List<ICommand> getCommandsForGameId(Integer gameId){
		return null;
	}
}
