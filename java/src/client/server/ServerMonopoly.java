/**
 * 
 */
package client.server;

import server.commands.ICommandParams;
import server.commands.exceptions.CommandParamNotValidException;

/**
 * Holds info needed to player a Monopoly card on the serevr
 * @author scottdaly
 *
 */
public class ServerMonopoly implements ICommandParams{
	private String type;
	private String resource;
	private int playerIndex;
	public ServerMonopoly(String type, String resource, int playerIndex) {
		this.type = type;
		this.resource = resource;
		this.playerIndex = playerIndex;
	}
	@Override
	public void isValid() throws CommandParamNotValidException {
		if(this.type == null || this.type.length() == 0 || !this.type.equals("maritimeTrade") || this.playerIndex < 0)
			throw new CommandParamNotValidException("Type musnt be null, length zero, or not equal to 'maritimeTrade', player index must be greater than zero: " + this.toString());
		if(this.resource == null || this.resource.length() == 0)
			throw new CommandParamNotValidException("resource cant be null or of length zero: " + this.toString());
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServerMonopoly [type=");
		builder.append(type);
		builder.append(", resource=");
		builder.append(resource);
		builder.append(", playerIndex=");
		builder.append(playerIndex);
		builder.append("]");
		return builder.toString();
	}
	
	
}
