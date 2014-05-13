package client.models;

import shared.locations.*;

public interface IHex {
	public HexLocation getLocation();
	public void setLocation(HexLocation location);
	
	public int getHexNumber();
	public void setHexNumber(int number);
}
