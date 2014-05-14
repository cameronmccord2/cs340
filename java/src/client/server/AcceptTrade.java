/**
 * 
 */
package client.server;

/**
 * Holds info to accept a trade from the server
 * @author scottdaly
 *
 */
public class AcceptTrade {
	private String type;
	private int playerIndex;
	public AcceptTrade(String type, int playerIndex) {
		this.type = type;
		this.playerIndex = playerIndex;
	}
	private boolean willAccept;
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
	 * @return the willAccept
	 */
	public boolean isWillAccept() {
		return willAccept;
	}
	/**
	 * @param willAccept the willAccept to set
	 */
	public void setWillAccept(boolean willAccept) {
		this.willAccept = willAccept;
	}
	
	
}
