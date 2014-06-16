package persistence.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import persistence.IPlugin;
import server.commands.ICommand;
import client.models.User;
import client.models.interfaces.IGame;
import client.models.interfaces.IParticipant;

@SuppressWarnings({"unused"})
public class SQLitePlugin implements IPlugin {

	public static final String dbPath = "jdbc:sqlite:team1Catan.db";
	private Integer n;
	private Integer nCounter;
	private SqliteCommandDAO commandDAO;
	private SqliteGameDAO gameDAO;
	private SqliteUserDAO userDAO;
	protected DataSource dataSource;

	public SQLitePlugin(){
		//    DriverManager.getConnection("jdbc:sqlite:test.db")
		commandDAO = new SqliteCommandDAO();
		gameDAO = new SqliteGameDAO();
		userDAO = new SqliteUserDAO();
		nCounter = 0;
	}

	public SQLitePlugin(Integer n){
		this.n = n;
		commandDAO = new SqliteCommandDAO();
		gameDAO = new SqliteGameDAO();
		userDAO = new SqliteUserDAO();
		nCounter = 0;
	}

	/**
	 * Adds the supplied user to the sqlite database. If it already exists then it gets updated in the db
	 *
	 * @param user the user to save
	 */
	@Override
	public void addUser(User user) {
		this.userDAO.upsertUser(user);
	}

	@Override
	public void createGame(IGame game) {
		this.gameDAO.insertNewGame(game);
	}

	/**
	 * Adds the command to the game in the sqlite db. This will keep up to n commands, as specified in the constructor. A snapshot of the game will be made every n commands.
	 *
	 * @param command the command to add. If null then we will assume this is a new game and will save it as a new game
	 * @param game the game to add the command to
	 */
	@Override
	public void addCommandToGame(ICommand command, IGame game){
		this.commandDAO.saveCommandForGameId(command, game.getGameInfo().getId());
		nCounter++;
		if(nCounter % n == 0){
			this.gameDAO.updateGame(game);
		}
	}

	/**
	 * Gets the users that have been registered with this sqlite db
	 *
	 * @return the users that this store has had registered
	 */
	@Override
	public List<User> getRegisteredUsers(){
		return this.userDAO.getAllUsers();
	}

	/**
	 * Gets the games that have been saved to this sqlite db
	 *
	 * @return the games that have been saved
	 */
	@Override
	public List<IGame> getGames(){
		return this.gameDAO.getAllGames();
	}

	/**
	 * Gets the commands for game by the id that have been saved in this sqlite db.
	 *
	 * @param gameId the gameId to get the commands for
	 * @return the commands for gameId
	 */
	@Override
	public List<ICommand> getCommandsForGameId(Integer gameId){
		return this.commandDAO.getCommandsForGameId(gameId);
	}

	@Override public void updateGame(IGame game){
		this.gameDAO.updateGame(game);
	}

	@Override
	public IGame getNewGameByGameId(Integer gameId){
		return this.gameDAO.getNewGameByGameId(gameId);
	}

	@Override
	public void clear()
	{
		String dropTables = "DROP TABLE IF EXISTS";
	}

	@Override
	public IGame getGameById(int gameId)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
