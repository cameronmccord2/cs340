package persistence.sqlite;

import java.util.List;

import javax.sql.DataSource;

import persistence.IPlugin;
import server.commands.ICommand;
import server.commands.ICommandParams;
import client.models.User;
import client.models.interfaces.IGame;

public class SQLitePlugin implements IPlugin {

	public static final String dbPath = "jdbc:sqlite:team1Catan.db";
	private Integer n;
	private Integer nCounter;
	private SqliteCommandDAO commandDAO;
	private SqliteGameDAO gameDAO;
	private SqliteUserDAO userDAO;
	protected DataSource dataSource;

	public SQLitePlugin(){
		this(5);
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
	public void addCommandToGame(ICommandParams command, IGame game){
		System.out.println("adding in plugin");
		this.commandDAO.saveCommandForGameId(command, game.getGameInfo().getId());
		System.out.println("added");
		nCounter = this.commandDAO.countCommandsForGameId(game.getGameInfo().getId());
		System.out.println("counted");
		System.out.println("nc: " + nCounter);
		System.out.println("n: " + n);
		if(nCounter % n == 0){
			System.out.println("updating game");
			this.gameDAO.updateGame(game);
		}
		System.out.println("save now");
		this.gameDAO.saveNow(game);
		System.out.println("saved now");
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
	public List<ICommandParams> getCommandsForGameId(Integer gameId){
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
	public IGame getNowGameById(Integer gameId){
		return this.gameDAO.getNowGameById(gameId);
	}
	@Override
	public void clear()
	{
		this.gameDAO.clear();
	}

	@Override
	public IGame getGameById(int gameId)
	{
		return this.gameDAO.getNewGameByGameId(gameId);
	}

	@Override
	public void setN(int parseInt) {
		this.n = parseInt;
	}
}
