package client.models;

import shared.locations.HexLocation;
import client.models.translator.TRHexLocation;

/**
 * @author scottdaly
 *
 */
public class Robber implements IRobber{

	protected HexLocation location;
	
	public Robber(TRHexLocation robber) {
		this.setLocation(new HexLocation(robber.getX(), robber.getY()));
	}

	@Override
	public HexLocation getLocation() {
		return this.location;
	}

	@Override
	public void setLocation(HexLocation location) {
		this.location = location;
	}


}
