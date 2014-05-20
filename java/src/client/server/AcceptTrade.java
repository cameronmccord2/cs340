/**
 * 
 */
package client.server;

import client.models.translator.TRTradeOffer;

/**
 * Holds info to accept a trade from the server
 * @author scottdaly
 *
 */
public class AcceptTrade {
	private String type;
	private int playerIndex;
	private boolean willAccept;
	public AcceptTrade(String type, int playerIndex, boolean willAccept) {
		this.type = type;
		this.playerIndex = playerIndex;
		this.willAccept = willAccept;
	}
	public AcceptTrade(TRTradeOffer currentTrade, boolean willAccept) {
		this.type = "willAccept";
		this.playerIndex = currentTrade.getReceiver();
		this.willAccept = willAccept;
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
