package client.models;

import shared.definitions.PortType;
import shared.locations.*;

public interface IPort
{
	public VertexLocation getLocation();
	public void setLocation(VertexLocation location);

	public int getExchangeRate();
	public void setExchangeRate(int rate);

	public PortType getPortType();
	public void setPortType(PortType type);
}
