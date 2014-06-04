/**
 * 
 */
package client.server;

import server.commands.ICommandParams;
import server.commands.exceptions.CommandParamNotValidException;
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
	@Override
	public void validate() throws CommandParamNotValidException {
		if(this.type == null || this.type.length() == 0 || !this.type.equals("maritimeTrade") || this.playerIndex < 0)
			throw new CommandParamNotValidException("Type musnt be null, length zero, or not equal to 'maritimeTrade', player index must be greater than zero: " + this.toString());
		if(this.vertexLocation == null)
			throw new CommandParamNotValidException("Vertex location cant be null" + this.toString());
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServerBuildSettlement [type=");
		builder.append(type);
		builder.append(", playerIndex=");
		builder.append(playerIndex);
		builder.append(", vertexLocation=");
		builder.append(vertexLocation);
		builder.append(", free=");
		builder.append(free);
		builder.append("]");
		return builder.toString();
	}
	
	
}