package client.models.interfaces;

import shared.definitions.*;
import shared.locations.*;

public interface IHex
{
	public HexLocation getLocation();
	public void setLocation(HexLocation location);

	public Integer getHexNumber();
	public void setHexNumber(Integer number);

	public HexType getHexType();
	public void setHexType(HexType type);
}
