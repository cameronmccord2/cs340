package client.models;

import shared.locations.*;

public interface IPort
{
	public VertexLocation getLocation();
	public void setLocation(VertexLocation location);
	
	public int getExchangeRate();
	public void setExchangeRate(int rate);
}
