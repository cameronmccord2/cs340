/**
 * 
 */
package client.server;

import server.commands.ICommandParams;
import shared.locations.SimplifiedVertexLocation;
import shared.locations.VertexLocation;

/**
 * Holds info to build a Settlement on the server
 * @author scottdaly
 *
 */
public class ServerBuildSettlement implements ICommandParams{
	private String type;
	private int playerIndex;
	private SimplifiedVertexLocation vertexLocation;
	private boolean free;
	public ServerBuildSettlement(String type, int playerIndex,
			VertexLocation vertexLocation, boolean free) {
		this.type = type;
		this.playerIndex = playerIndex;
		this.vertexLocation = new SimplifiedVertexLocation(vertexLocation);
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
	 * @return the vertexLocation
	 */
	public SimplifiedVertexLocation getVertexLocation() {
		return vertexLocation;
	}
	/**
	 * @param vertexLocation the vertexLocation to set
	 */
	public void setVertexLocation(VertexLocation vertexLocation) {
		this.vertexLocation = new SimplifiedVertexLocation(vertexLocation);
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