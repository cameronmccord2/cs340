package client.models;

import java.util.Collection;
import java.util.HashSet;

import shared.definitions.PieceType;
import shared.definitions.ResourceType;
import shared.locations.ILocation;

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
		cost = new HashSet<>();
		cost.add(new Resource(ResourceType.BRICK, 1));
		cost.add(new Resource(ResourceType.WOOD, 1));
		cost.add(new Resource(ResourceType.SHEEP, 1));
		cost.add(new Resource(ResourceType.WHEAT, 1));
	}

	@Override
	public PieceType getPieceType()
	{
		return PieceType.SETTLEMENT;
	}

	@Override
	public Collection<Resource> getResourceCost()
	{
		return cost;
	}

	@Override
	public int getPointValue()
	{
		return pointValue;
	}

}
