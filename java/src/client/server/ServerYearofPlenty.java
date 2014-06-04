package client.server;

import server.commands.ICommandParams;
import server.commands.exceptions.CommandParamNotValidException;

/**
 * Holds info to play a Year of Plenty card on the server
 * @author scottdaly
 *
 */
public class ServerYearofPlenty implements ICommandParams{
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
	@Override
	public void validate() throws CommandParamNotValidException {
		if(this.type == null || this.type.length() == 0 || !this.type.equals("maritimeTrade") || this.playerIndex < 0)
			throw new CommandParamNotValidException("Type musnt be null, length zero, or not equal to 'maritimeTrade', player index must be greater than zero: " + this.toString());
		if(this.resource1 == null || this.resource1.length() == 0 || this.resource2 == null || this.resource2.length() == 0)
			throw new CommandParamNotValidException("Resource1 and 2 cant be null or of length zero: " + this.toString());
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServerYearofPlenty [type=");
		builder.append(type);
		builder.append(", playerIndex=");
		builder.append(playerIndex);
		builder.append(", resource1=");
		builder.append(resource1);
		builder.append(", resource2=");
		builder.append(resource2);
		builder.append("]");
		return builder.toString();
	}
	
	
}
