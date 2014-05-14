/**
 * 
 */
package client.server;

import shared.locations.VertexLocation;

/**
 * Holds info to build a Settlement on the server
 * @author scottdaly
 *
 */
public class ServerBuildSettlement {
	private String type;
	private int playerindex;
	private VertexLocation vertexLocation;
	private boolean free;
	public ServerBuildSettlement(String type, int playerindex,
			VertexLocation vertexLocation, boolean free) {
		this.type = type;
		this.playerindex = playerindex;
		this.vertexLocation = vertexLocation;
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
	 * @return the vertexLocation
	 */
	public VertexLocation getVertexLocation() {
		return vertexLocation;
	}
	/**
	 * @param vertexLocation the vertexLocation to set
	 */
	public void setVertexLocation(VertexLocation vertexLocation) {
		this.vertexLocation = vertexLocation;
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
