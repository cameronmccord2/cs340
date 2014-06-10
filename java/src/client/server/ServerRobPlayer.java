package client.server;

import server.commands.ICommandParams;
import server.commands.exceptions.CommandParamNotValidException;
import shared.locations.HexLocation;

/**
 * Holds info needed to rob a player on the server
 * @author scottdaly
 *
 */
public class ServerRobPlayer implements ICommandParams{

	private String type;
	private int playerIndex;
	private int victimIndex;
	private HexLocation location;
	
	public ServerRobPlayer(String type, int playerIndex, int victimIndex,
			HexLocation location) {
		this.type = type;
		this.playerIndex = playerIndex;
		this.victimIndex = victimIndex;
		this.location = location;
	}

	@Override
	public void validate() throws CommandParamNotValidException {
		if(this.type == null || this.type.length() == 0 || !this.type.equals("robPlayer") || this.playerIndex < 0)
			throw new CommandParamNotValidException("Type musnt be null, length zero, or not equal to 'maritimeTrade', player index must be greater than zero: " + this.toString());
		//if(this.victimIndex < 0 || this.location == null)
		//	throw new CommandParamNotValidException("victim index cant be less than zero and location cant be null: " + this.toString());
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
	 * @return the victimIndex
	 */
	public int getVictimIndex() {
		return victimIndex;
	}

	/**
	 * @param victimIndex the victimIndex to set
	 */
	public void setVictimIndex(int victimIndex) {
		this.victimIndex = victimIndex;
	}

	/**
	 * @return the location
	 */
	public HexLocation getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(HexLocation location) {
		this.location = location;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServerRobPlayer [type=");
		builder.append(type);
		builder.append(", playerIndex=");
		builder.append(playerIndex);
		builder.append(", victimIndex=");
		builder.append(victimIndex);
		builder.append(", location=");
		builder.append(location);
		builder.append("]");
		return builder.toString();
	}

	
	
}
