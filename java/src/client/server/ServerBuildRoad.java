
package client.server;

import shared.locations.EdgeLocation;

/**
 * Holds info to build a road on the server
 * @author scottdaly
 *
 */
public class ServerBuildRoad {

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
	
	
}

class SimplifiedEdgeLocation {
    int x;
    int y;
    String direction;

    public SimplifiedEdgeLocation() {
        x = 0;
        y = 0;
        direction = "N";
    }
    public SimplifiedEdgeLocation(EdgeLocation edge) {
        x = edge.getHexLocation().getX();
        y = edge.getHexLocation().getY();
        switch(edge.getDirection().toString().toUpperCase()){
            case "NORTHWEST":
                direction = "NW"; break;
            case "NORTH":
                direction = "N"; break;
            case "NORTHEAST":
                direction = "NE"; break;
            case "SOUTHEAST":
                direction = "SE"; break;
            case "SOUTH":
                direction = "S"; break;
            case "SOUTHWEST":
                direction = "SW"; break;
        }
    }
}