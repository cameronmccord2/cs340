/**
 * 
 */
package client.server;

import shared.locations.SimplifiedVertexLocation;
import shared.locations.VertexLocation;

/**
 * Holds info to build a city on the server
 * @author scottdaly
 *
 */
public class ServerBuildCity {
	private String type;
	private int playerIndex;
	private SimplifiedVertexLocation vertexLocation;
	public ServerBuildCity(String type, int playerIndex,
			VertexLocation vertexLocation) {
		this.type = type;
		this.playerIndex = playerIndex;
		this.vertexLocation = new SimplifiedVertexLocation(vertexLocation);
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
	
}
