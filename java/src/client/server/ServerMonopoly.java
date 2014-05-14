/**
 * 
 */
package client.server;

/**
 * Holds info needed to player a Monopoly card on the serevr
 * @author scottdaly
 *
 */
public class ServerMonopoly {
	private String type;
	private String resource;
	private int playerIndex;
	public ServerMonopoly(String type, String resource, int playerIndex) {
		this.type = type;
		this.resource = resource;
		this.playerIndex = playerIndex;
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
	/**
	 * @return the resource
	 */
	public String getResource() {
		return resource;
	}
	/**
	 * @param resource the resource to set
	 */
	public void setResource(String resource) {
		this.resource = resource;
	}
	/**
	 * @return the playerIndex
	 */
	public int getPlayerIndex() {
		return playerIndex;
	}
	/**
	 * @param playerIndex the playerIndex to set
	 */
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	
	
}
