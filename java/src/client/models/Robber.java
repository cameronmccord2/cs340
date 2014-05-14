package client.models;

import java.util.Collection;

import shared.definitions.PieceType;
import shared.locations.HexLocation;
import client.models.translator.TRHexLocation;

/**
 * @author scottdaly
 *
 */
public class Robber extends Piece implements IRobber
{

	public Robber(TRHexLocation robber) {
		this.setLocation(new HexLocation(robber.getX(), robber.getY()));
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
