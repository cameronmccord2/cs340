package persistence.sqlite;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import client.models.interfaces.IGame;

/**
 * The Class SqliteGameDAO.
 */
public class SqliteGameDAO {

	public SqliteGameDAO(){
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		this.createTable();
	}

	private void createTable(){
		final String sql = "create table if not exists games (id INTEGER PRIMARY KEY, currentGameData BLOB, beginningGameData BLOB, nowGameData BLOB)";
		try(Connection connection = DriverManager.getConnection(SQLitePlugin.dbPath); PreparedStatement statement = connection.prepareStatement(sql);){
			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Insert new game into the sqlite db. This will save the game under the new game column.
	 * @param game the game to insert
	 */
	public void insertNewGame(IGame game){
		this.deleteGameById(game.getGameInfo().getId());
		final String sql = "INSERT INTO games (id, beginningGameData, currentGameData) VALUES (?, ?, ?)";
		try(Connection connection = DriverManager.getConnection(SQLitePlugin.dbPath); PreparedStatement statement = connection.prepareStatement(sql);){
			statement.setInt(1, game.getGameInfo().getId());
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutput out = new ObjectOutputStream(bos);
			out.writeObject(game);
			statement.setBytes(2, bos.toByteArray());
			statement.setBytes(3, bos.toByteArray());
			out.close();
			bos.close();

			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Updates the game on the sqlite db
	 * @param game the game to update
	 */
	public void updateGame(IGame game){
		final String sql = "UPDATE games SET currentGameData = ? WHERE id = ?";
		try(Connection connection = DriverManager.getConnection(SQLitePlugin.dbPath); PreparedStatement statement = connection.prepareStatement(sql);){
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutput out = new ObjectOutputStream(bos);
			out.writeObject(game);
			statement.setBytes(1, bos.toByteArray());
			out.close();
			bos.close();
			statement.setInt(2, game.getGameInfo().getId());

			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Delete game by id.
	 *
	 * @param connection the connection to talk to the sqlite db on
	 * @param gameId the game id to delete
	 */
	private void deleteGameById(Integer gameId){
		final String sql = "DELETE FROM games WHERE id = ?";
		try(Connection connection = DriverManager.getConnection(SQLitePlugin.dbPath); PreparedStatement statement = connection.prepareStatement(sql);){
			statement.setInt(1, gameId);

			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets the all games in this sqlite db
	 *
	 * @return all the games in the sqlite store
	 */
	public List<IGame> getAllGames(){
		final String sql = "SELECT * FROM games";
		List<IGame> results = new ArrayList<>();
		try(Connection connection = DriverManager.getConnection(SQLitePlugin.dbPath); PreparedStatement statement = connection.prepareStatement(sql);){

			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()){
				byte[] b = resultSet.getBytes("currentGameData");
				ByteArrayInputStream bis = new ByteArrayInputStream(b);
				ObjectInput in = new ObjectInputStream(bis);
				IGame g = (IGame)in.readObject();
				results.add(g);
			}
			resultSet.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}

	public IGame getNewGameByGameId(Integer gameId){
		final String sql = "SELECT * FROM games where id = ?";
		IGame result = null;
		try(Connection connection = DriverManager.getConnection(SQLitePlugin.dbPath); PreparedStatement statement = connection.prepareStatement(sql);){
			statement.setInt(1, gameId);

			ResultSet resultSet = statement.executeQuery();

			while(resultSet.next()){
				byte[] b = resultSet.getBytes("beginningGameData");
				ByteArrayInputStream bis = new ByteArrayInputStream(b);
				ObjectInput in = new ObjectInputStream(bis);
				result = (IGame)in.readObject();
			}
			resultSet.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public void saveNow(IGame game) {
		final String sql = "UPDATE games SET nowGameData = ? WHERE id = ?";
		try(Connection connection = DriverManager.getConnection(SQLitePlugin.dbPath); PreparedStatement statement = connection.prepareStatement(sql);){
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutput out = new ObjectOutputStream(bos);
			out.writeObject(game);
			statement.setBytes(1, bos.toByteArray());
			out.close();
			bos.close();
			statement.setInt(2, game.getGameInfo().getId());

			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public IGame getNowGameById(Integer gameId) {
		final String sql = "SELECT * FROM games where id = ?";
		IGame result = null;
		try(Connection connection = DriverManager.getConnection(SQLitePlugin.dbPath); PreparedStatement statement = connection.prepareStatement(sql);){
			statement.setInt(1, gameId);

			ResultSet resultSet = statement.executeQuery();

			while(resultSet.next()){
				byte[] b = resultSet.getBytes("nowGameData");
				ByteArrayInputStream bis = new ByteArrayInputStream(b);
				ObjectInput in = new ObjectInputStream(bis);
				result = (IGame)in.readObject();
			}
			resultSet.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
