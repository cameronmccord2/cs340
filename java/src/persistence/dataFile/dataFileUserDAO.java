package persistence.dataFile;

import client.models.User;

import java.util.List;

/**
 * The Class dataFileUserDAO.
 */
public class dataFileUserDAO {

	/**
	 * Insert User as a new datafile in the following format:
	 * filename: users/userId.dat
	 * serializedUserData
	 *
	 * If a dataFile for the user already exists, data will be overwritten.
	 *
	 * @param user the user to add/update
	 */
	public void upsertUser(User user){
		
	}
	
	/**
	 * Gets the all users in the dataFiles
	 *
	 * @return the all users in the dataFiles
	 */
	public List<User> getAllUsers(){
		return null;
	}
}
