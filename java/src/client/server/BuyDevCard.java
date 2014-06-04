/**
 * 
 */
package client.server;

import server.commands.ICommandParams;
import server.commands.exceptions.CommandParamNotValidException;

/**
 * Holds info needed to buy a dev card on the server
 * @author scottdaly
 *
 */
public class BuyDevCard  implements ICommandParams{
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
	@Override
	public void validate() throws CommandParamNotValidException {
		if(this.type == null || this.type.length() == 0 || !this.type.equals("buyDevCard") || this.playerIndex < 0)
			throw new CommandParamNotValidException("type musnt be null or of length zero and must equal 'buyDevCard' and playerindex must be greater than zero: " + this.toString());
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BuyDevCard [type=");
		builder.append(type);
		builder.append(", playerIndex=");
		builder.append(playerIndex);
		builder.append("]");
		return builder.toString();
	}
	
	
}
