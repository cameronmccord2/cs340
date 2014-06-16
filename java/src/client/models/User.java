package client.models;

import java.io.Serializable;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2166392048484053108L;
	private String name;
	private String password;
	private int playerID;
	
	public User(String name, String password, int playerID) {
		this.name = name;
		this.password = password;
		this.playerID = playerID;
	}

	/**
	 * @return the name
	 */
	public String getname() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setname(String name) {
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [name=");
		builder.append(name);
		builder.append(", password=");
		builder.append(password);
		builder.append(", playerID=");
		builder.append(playerID);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
