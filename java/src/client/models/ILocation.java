package client.models;

import shared.locations.*;
import shared.definitions.*;

public interface ILocation
{
	public HexLocation getHexLocation();
	public void setHexLocation(HexLocation location);

	public IDirection getDirection();
	public void setDirection(IDirection edge);
}
