package persistance.sqlite;

import java.sql.Connection;
import java.util.List;

import client.models.User;

/**
 * The Class SqliteUserDAO.
 */
public class SqliteUserDAO {

	/**
	 * Upsert user. This updates the user if it exists in our db or inserts it if it doesn't exist
	 *
	 * @param connection the connection to talk to the sqlite db on
	 * @param user the user to upsert
	 */
	public void upsertUser(Connection connection, User user){
		
	}
	
	/**
	 * Gets the all users in this sqlite db
	 *
	 * @param connection the connection to talk to the sqlite db on
	 * @return the all users in this sqlite db
	 */
	public List<User> getAllUsers(Connection connection){
		return null;
	}
}
