/**
 * 
 */
package client.server;

import server.commands.ICommandParams;

/**
 * 
 * Holds the game id and name to save
 * @author scottdaly
 *
 */
public class SaveGame implements ICommandParams{
	
	private int id;
	private String name;
	
	public SaveGame(int id, String name) {
		this.id = id;
		this.name = name;
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
	
	

}
