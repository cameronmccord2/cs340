package client.models;

import shared.locations.*;

public interface ISettlement {
	public VertexLocation getVertexLocation();
	public void setVertexLocation(VertexLocation location);
	
	public IPlayer getPlayer();
	public void setPlayer(IPlayer player);
	
	public int getPointValue();
}
