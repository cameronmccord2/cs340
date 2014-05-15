package client.models;

import shared.definitions.PortType;
import shared.locations.*;

public interface IPort
{
	public HexLocation getLocation();
	public void setLocation(HexLocation location);

	public int getExchangeRate();
	public void setExchangeRate(int rate);

	public PortType getPortType();
	public void setPortType(PortType type);
}
