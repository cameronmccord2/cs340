package persistence;

import java.util.List;

import server.commands.ICommand;
import client.models.User;
import client.models.interfaces.IGame;
import client.models.interfaces.IParticipant;

/**
 * This will need to be updated. But I figured we could start making the
 * interfaces we would be using.
 * 
 * @author Craig Call
 *
 */
@SuppressWarnings({"unused"})
public interface IPlugin
{   
	
	/**
	 * Adds the supplied user to the persistent store
	 *
	 * @param user the user to save
	 */
	public void addUser(User user);
	
	/**
	 * Adds a game to the list of games to be saved.
	 * 
	 * @param game	The game to be added
	 */
	public void createGame(IGame game);

	/**
	 * Gets the users that have been registered with this persistent store
	 *
	 * @return the users that this store has had registered
	 */
	public List<User> getRegisteredUsers();

	/**
	 * Gets the games that have been saved to this persistent store
	 *
	 * @return the games that have been saved
	 */
	public List<IGame> getGames();

	/**
	 * Gets the commands for game by the id.
	 *
	 * @param gameId the gameId to get the commands for
	 * @return the commands for gameId
	 */
	public List<ICommand> getCommandsForGameId(Integer gameId);

	/**
	 * Adds the command to the game. This will keep up to n commands, as specified in the constructor. A snapshot of the game will be made every n commands.
	 *
	 * @param command the command to add. If null then we will assume this is a new game and will save it as a new game
	 * @param Igame the game to add the command to
	 */
	public void addCommandToGame(ICommand command, IGame game);

	void updateGame(IGame game);

	IGame getNewGameByGameId(Integer gameId);
}
