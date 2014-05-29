package client.server;

import server.commands.ICommandParams;
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
	
	
}
