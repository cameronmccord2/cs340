package client.models;

import java.util.Collection;

import shared.definitions.CatanColor;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.ILocation;

public class RoadSegment extends Piece implements IRoadSegment
{
	private Collection<Resource> cost;

	public RoadSegment()
	{

	}

	@Override
	public Collection<Resource> getResourceCost()
	{
		return cost;
	}

	@Override
	public PieceType getPieceType()
	{
		return PieceType.ROAD;
	}

}
