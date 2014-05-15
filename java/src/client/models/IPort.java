package client.models;

import shared.definitions.PortType;
import shared.locations.*;

public interface IPort
{
	public ILocation getLocation();
	public void setLocation(ILocation location);

	public int getExchangeRate();
	public void setExchangeRate(int rate);

	public PortType getPortType();
	public void setPortType(PortType type);
}
