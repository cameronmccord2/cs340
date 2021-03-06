package client.server;

import server.commands.ICommandParams;
import server.commands.exceptions.CommandParamNotValidException;

/**
 * Class to hold info for sending a rolled dice to the server
 * @author scottdaly
 *
 */
public class ServerRoll implements ICommandParams{

	private String type;
	private int playerIndex;
	private int number;
	
	public ServerRoll(String type, int playerIndex, int number) {
		this.type = type;
		this.playerIndex = playerIndex;
		this.number = number;
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
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public void validate() throws CommandParamNotValidException {
		if(this.type == null || this.type.length() == 0 || this.playerIndex < 0 || !this.type.equals("rollNumber"))
			throw new CommandParamNotValidException("type musnt be null or of length zero and player index must be greater than zero: " + this.toString());
		if(this.number < 2 || this.number > 12)
			throw new CommandParamNotValidException("the rolled number must be 2 <= number <= 12");
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServerRoll [type=");
		builder.append(type);
		builder.append(", playerIndex=");
		builder.append(playerIndex);
		builder.append(", number=");
		builder.append(number);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
