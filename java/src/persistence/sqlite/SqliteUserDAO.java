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

import client.models.User;

/**
 * The Class SqliteUserDAO.
 */
public class SqliteUserDAO {
	
	public SqliteUserDAO(){
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		this.createTable();
	}
	
	private void createTable(){
		final String sql = "create table if not exists users (id INTEGER PRIMARY KEY, playerData BLOB)";
		try(Connection connection = DriverManager.getConnection("jdbc:sqlite:team1Catan.db"); PreparedStatement statement = connection.prepareStatement(sql);){
			statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Upsert user. This updates the user if it exists in our db or inserts it if it doesn't exist
	 *
	 * @param connection the connection to talk to the sqlite db on
	 * @param user the user to upsert
	 */
	public void upsertUser(User user){
		this.deleteUser(user);
		this.insertUser(user);
	}
	
	private void insertUser(User user) {
		final String sql = "INSERT INTO users (id, playerData) VALUES (?, ?)";
		try(Connection connection = DriverManager.getConnection("jdbc:sqlite:team1Catan.db"); PreparedStatement statement = connection.prepareStatement(sql);){
			statement.setInt(1, user.getPlayerID());
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutput out = new ObjectOutputStream(bos);
			out.writeObject(user);
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

	private void deleteUser(User user) {
		final String sql = "DELETE FROM users WHERE id = ?";
		try(Connection connection = DriverManager.getConnection("jdbc:sqlite:team1Catan.db"); PreparedStatement statement = connection.prepareStatement(sql);){
			statement.setInt(1, user.getPlayerID());
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets the all users in this sqlite db
	 *
	 * @param connection the connection to talk to the sqlite db on
	 * @return the all users in this sqlite db
	 */
	public List<User> getAllUsers(){
		final String sql = "SELECT * FROM users";
		List<User> results = new ArrayList<>();
		try(Connection connection = DriverManager.getConnection("jdbc:sqlite:team1Catan.db"); PreparedStatement statement = connection.prepareStatement(sql);){
			
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()){
				byte[] b = resultSet.getBytes("playerData");
				ByteArrayInputStream bis = new ByteArrayInputStream(b);
				ObjectInput in = new ObjectInputStream(bis);
				User u = (User)in.readObject();
				results.add(u);
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
}
