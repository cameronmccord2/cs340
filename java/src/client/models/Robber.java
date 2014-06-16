package client.models;

import java.io.Serializable;

import shared.definitions.PieceType;
import shared.locations.DefaultLocation;
import shared.locations.HexLocation;
import client.models.interfaces.IRobber;
import client.models.translator.TRHexLocation;

/**
 * @author scottdaly
 *
 */
public class Robber extends Piece implements IRobber, Serializable
{

	public Robber(TRHexLocation robber) {
		HexLocation hex = new HexLocation(robber.getX(), robber.getY());
		this.location = new DefaultLocation(hex, null);
	}
	
	public Robber(HexLocation robber) {
		this.location = new DefaultLocation(robber,null);
	}

	public void setLocation(HexLocation hexLocation) {
		location.setHexLocation(hexLocation);
	}

	@Override
	public PieceType getPieceType()
	{
		return PieceType.ROBBER;
	}
}
