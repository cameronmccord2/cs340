/**
 * 
 */
package client.server;

import server.commands.ICommandParams;

/**
 * Stores the info needed to join a game on the server
 * @author scottdaly
 *
 */
public class ServerJoinGame implements ICommandParams{
	
	private int id;
	private String color;
	
	public ServerJoinGame(int id, String color) {
		this.id = id;
		this.color = color;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
	

}
