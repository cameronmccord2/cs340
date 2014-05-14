
package client.server;

import shared.locations.EdgeLocation;

/**
 * Holds info to build a road on the server
 * @author scottdaly
 *
 */
public class ServerBuildRoad {

	private String type;
	private int playerindex;
	private EdgeLocation roadLocation;
	private boolean free;
	public ServerBuildRoad(String type, int playerindex,
			EdgeLocation roadLocation, boolean free) {
		this.type = type;
		this.playerindex = playerindex;
		this.roadLocation = roadLocation;
		this.free = free;
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
	 * @return the playerindex
	 */
	public int getPlayerindex() {
		return playerindex;
	}
	/**
	 * @param playerindex the playerindex to set
	 */
	public void setPlayerindex(int playerindex) {
		this.playerindex = playerindex;
	}
	/**
	 * @return the roadLocation
	 */
	public EdgeLocation getRoadLocation() {
		return roadLocation;
	}
	/**
	 * @param roadLocation the roadLocation to set
	 */
	public void setRoadLocation(EdgeLocation roadLocation) {
		this.roadLocation = roadLocation;
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
	
	
}
