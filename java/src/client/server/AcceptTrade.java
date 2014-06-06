/**
 * 
 */
package client.server;

import server.commands.ICommandParams;
import server.commands.exceptions.CommandParamNotValidException;
import client.models.translator.TRTradeOffer;

/**
 * Holds info to accept a trade from the server
 * @author scottdaly
 *
 */
public class AcceptTrade implements ICommandParams{
	private String type;
	private int playerIndex;
	private boolean willAccept;
	public AcceptTrade(String type, int playerIndex, boolean willAccept) {
		this.type = type;
		this.playerIndex = playerIndex;
		this.willAccept = willAccept;
	}
	public AcceptTrade(TRTradeOffer currentTrade, boolean willAccept) {
		this.type = "acceptTrade";
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AcceptTrade [type=");
		builder.append(type);
		builder.append(", playerIndex=");
		builder.append(playerIndex);
		builder.append(", willAccept=");
		builder.append(willAccept);
		builder.append("]");
		return builder.toString();
	}
	
	@Override
	public void validate() throws CommandParamNotValidException {
		if(this.type == null || this.type.length() == 0 || this.playerIndex < 0 || !type.equals("acceptTrade"))
			throw new CommandParamNotValidException("type musnt be null or of length zero and player index must be greater than zero: " + this.toString());
	}
	
	
}
