package client.server;

/**
 * Holds info to play a Year of Plenty card on the server
 * @author scottdaly
 *
 */
public class ServerYearofPlenty {
	private String type;
	private int playerIndex;
	private String resource1;
	private String resource2;
	public ServerYearofPlenty(String type, int playerIndex, String resource1,
			String resource2) {
		this.type = type;
		this.playerIndex = playerIndex;
		this.resource1 = resource1;
		this.resource2 = resource2;
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
	/**
	 * @return the resource1
	 */
	public String getResource1() {
		return resource1;
	}
	/**
	 * @param resource1 the resource1 to set
	 */
	public void setResource1(String resource1) {
		this.resource1 = resource1;
	}
	/**
	 * @return the resource2
	 */
	public String getResource2() {
		return resource2;
	}
	/**
	 * @param resource2 the resource2 to set
	 */
	public void setResource2(String resource2) {
		this.resource2 = resource2;
	}
	
	
}
