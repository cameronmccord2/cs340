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

import server.commands.Command;
import server.commands.ICommand;

/**
 * The Class SqliteCommandDAO.
 */
public class SqliteCommandDAO {

	public SqliteCommandDAO(){
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		this.createTable();
	}

	private void createTable(){
		final String sql = "create table if not exists commands (id INTEGER PRIMARY KEY, gameId NUMBER, commandData BLOB)";
		try(Connection connection = DriverManager.getConnection(SQLitePlugin.dbPath); PreparedStatement statement = connection.prepareStatement(sql);){
			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets the commands for game id.
	 * @param gameId the game id
	 *
	 * @return the commands for game id
	 */
	public List<ICommand> getCommandsForGameId(Integer gameId){
		final String sql = "select * from commands where gameid = ?";
		List<ICommand> results = new ArrayList<>();
		try(Connection connection = DriverManager.getConnection(SQLitePlugin.dbPath); PreparedStatement statement = connection.prepareStatement(sql);){
			statement.setInt(1, gameId);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()){
				byte[] b = resultSet.getBytes("commandData");
				ByteArrayInputStream bis = new ByteArrayInputStream(b);
				ObjectInput in = new ObjectInputStream (bis);
				Command c = (Command)in.readObject();
				results.add(c);
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

	/**
	 * Save command for game id.
	 * @param command the command to save
	 * @param gameId the game id to save the command for
	 */
	public void saveCommandForGameId(ICommand command, Integer gameId){
		final String sql = "INSERT INTO commands (gameId, commandData) VALUES (?, ?)";
		try(Connection connection = DriverManager.getConnection(SQLitePlugin.dbPath); PreparedStatement statement = connection.prepareStatement(sql);){
			statement.setInt(1, gameId);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutput out = new ObjectOutputStream(bos);
			out.writeObject(command);
			statement.setBytes(2, bos.toByteArray());
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

//	/**
//	 * Count commands for game id.
//	 * @param gameId the game id
//	 *
//	 * @return the count of the current commands for a game
//	 */
//	private Integer countCommandsForGameId(Integer gameId){
//		return null;
//	}
//
//	/**
//	 * Delete oldest x commands for the game by id.
//	 * @param count the count to delete
//	 * @param gameId the game id
//	 */
//	private void deleteOldestXCommandsForGameId(Integer count, Integer gameId){
//
//	}
}
