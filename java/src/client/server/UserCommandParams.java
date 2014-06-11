/**
 * 
 */
package client.server;

import server.commands.ICommandParams;
import server.commands.exceptions.CommandParamNotValidException;

/**
 * Simply holds the name and password of the user trying to log in
 * @author scottdaly
 *
 */
public class UserCommandParams implements ICommandParams{
	
	private String username;
	private String password;
	
	public UserCommandParams(String user, String password) {
		this.username = user;
		this.password = password;
	}

	public UserCommandParams() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return username;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.username = user;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void validate() throws CommandParamNotValidException {
		if(this.username == null || this.username.length() == 0 || this.password == null || this.password.length() == 0)
			throw new CommandParamNotValidException("Username and password musnt be null or of length zero: " + this.toString());
	}

	@Override
	public String getType() {
		return "user";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
	
	
	
	

}
