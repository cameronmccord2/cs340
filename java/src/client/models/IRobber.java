package client.models;

import shared.locations.HexLocation;

public interface IRobber {
	
	public HexLocation getLocation();
	public void setLocation(HexLocation location);
	
	
}
