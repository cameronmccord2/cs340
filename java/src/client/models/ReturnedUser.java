/**
 * 
 */
package client.models;

/**
 * This is the object that is returned from the server when the user logs in
 * @author scottdaly
 *
 */
public class ReturnedUser{
	private String name;
	private String password;
	private int playerID;
	public ReturnedUser(String name, String password, int playerID) {
		this.name = name;
		this.password = password;
		this.playerID = playerID;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	/**
	 * @return the playerID
	 */
	public int getPlayerID() {
		return playerID;
	}
	/**
	 * @param playerID the playerID to set
	 */
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ReturnedUser [name=" + name + ", password=" + password
				+ ", playerID=" + playerID + "]";
	}
	
	
}
