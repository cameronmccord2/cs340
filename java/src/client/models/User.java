/**
 * 
 */
package client.models;

/**
 * Simply holds the name and password of the user trying to log in
 * @author scottdaly
 *
 */
public class User {
	
	private String username;
	private String password;
	
	public User(String user, String password) {
		this.username = user;
		this.password = password;
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
	
	
	
	

}
