/**
 * 
 */
package client.server;

import server.commands.ICommandParams;

/**
 * Holds the name of the game to laod
 * @author scottdaly
 *
 */
public class GameLoad  implements ICommandParams{
	private String name;

	public GameLoad(String name) {
		this.name = name;
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
