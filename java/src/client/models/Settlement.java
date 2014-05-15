package client.models;

import java.util.Collection;

import shared.definitions.CatanColor;
import shared.definitions.PieceType;
import shared.locations.ILocation;
import shared.locations.VertexLocation;

/**
 * @author Craig Call
 *
 */
public class Settlement extends Piece implements ISettlement
{
	protected int pointValue;
	protected Collection<Resource> cost;

	public Settlement(ILocation location, IPlayer player, int pointValue)
	{
		this.setLocation(location);
		this.setPlayer(player);
		this.pointValue = pointValue;
	}

	@Override
	public PieceType getPieceType()
	{
		return PieceType.SETTLEMENT;
	}

	@Override
	public Collection<Resource> getResourceCost()
	{
		return null;
	}

	@Override
	public int getPointValue()
	{
		return pointValue;
	}

}
