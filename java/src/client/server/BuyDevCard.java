/**
 * 
 */
package client.server;

/**
 * Holds info needed to buy a dev card on the server
 * @author scottdaly
 *
 */
public class BuyDevCard  extends CommandParams{
	private String type;
	private int playerIndex;
	public BuyDevCard(String type, int playerIndex) {
		this.type = type;
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
