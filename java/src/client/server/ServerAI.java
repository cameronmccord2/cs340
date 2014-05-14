/**
 * 
 */
package client.server;

/**
 * 
 * This holds the AI type player to add to the game
 * @author scottdaly
 *
 */
public class ServerAI {

	private String type;

	public ServerAI(String type) {
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
}
