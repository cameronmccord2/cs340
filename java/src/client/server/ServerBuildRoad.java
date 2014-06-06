
package client.server;

import server.commands.ICommandParams;
import server.commands.exceptions.CommandParamNotValidException;
import shared.locations.EdgeLocation;
import shared.locations.SimplifiedEdgeLocation;

/**
 * Holds info to build a road on the server
 * @author scottdaly
 *
 */
public class ServerBuildRoad implements ICommandParams{

	private String type;
	private int playerIndex;
	private SimplifiedEdgeLocation roadLocation;
	private boolean free;
	public ServerBuildRoad(String type, int playerIndex,
			EdgeLocation roadLocation, boolean free) {
		this.type = type;
		this.playerIndex = playerIndex;
		this.roadLocation = new SimplifiedEdgeLocation(roadLocation);
		this.free = free;
	}
	@Override
	public void validate() throws CommandParamNotValidException {
		if(this.type == null || this.type.length() == 0 || !this.type.equals("buildRoad") || this.playerIndex < 0)
			throw new CommandParamNotValidException("Type musnt be null, length zero, or not equal to 'maritimeTrade', player index must be greater than zero: " + this.toString());
		if(this.roadLocation == null)
			throw new CommandParamNotValidException("Road location cant be null: " + this.toString());
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
	 * @return the roadLocation
	 */
	public SimplifiedEdgeLocation getRoadLocation() {
		return roadLocation;
	}
	/**
	 * @param roadLocation the roadLocation to set
	 */
	public void setRoadLocation(EdgeLocation roadLocation) {
		this.roadLocation = new SimplifiedEdgeLocation(roadLocation);
	}
	/**
	 * @return the free
	 */
	public boolean isFree() {
		return free;
	}
	/**
	 * @param free the free to set
	 */
	public void setFree(boolean free) {
		this.free = free;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServerBuildRoad [type=");
		builder.append(type);
		builder.append(", playerIndex=");
		builder.append(playerIndex);
		builder.append(", roadLocation=");
		builder.append(roadLocation);
		builder.append(", free=");
		builder.append(free);
		builder.append("]");
		return builder.toString();
	}
	
	
}