package client.models;

import shared.definitions.PieceType;
import shared.locations.DefaultLocation;
import shared.locations.HexLocation;
import client.models.interfaces.IRobber;
import client.models.translator.TRHexLocation;

/**
 * @author scottdaly
 *
 */
public class Robber extends Piece implements IRobber
{

	public Robber(TRHexLocation robber) {
		HexLocation hex = new HexLocation(robber.getX(), robber.getY());
		this.location = new DefaultLocation(hex, null);
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
