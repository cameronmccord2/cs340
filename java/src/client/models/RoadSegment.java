package client.models;

import java.util.Collection;
import java.util.HashSet;

import client.models.translator.TRRoad;

import shared.definitions.PieceType;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;

public class RoadSegment extends Piece implements IRoadSegment
{
	private Collection<Resource> cost;

	public RoadSegment()
	{

	}

	public RoadSegment(TRRoad road, IPlayer player)
	{
		this.setPlayer(player);
		this.setLocation(new EdgeLocation(road.getLocation()));
		cost = new HashSet<>();
		cost.add(new Resource(ResourceType.BRICK, 1));
		cost.add(new Resource(ResourceType.WOOD, 1));
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
