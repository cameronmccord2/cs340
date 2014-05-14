package client.models;

import shared.definitions.*;
import shared.locations.*;

public interface IHex
{
	public HexLocation getLocation();
	public void setLocation(HexLocation location);

	public int getHexNumber();
	public void setHexNumber(int number);

	public HexType getHexType();
	public void setHexType(HexType type);
}
