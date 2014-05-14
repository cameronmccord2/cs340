package client.models;

import java.util.Collection;

import client.models.translator.TRRoad;

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

//	new RoadSegment(road, this.getPlayerWithId(road.getOwner(), g.getPlayers())));
	public RoadSegment(TRRoad road, IPlayer player)
	{
		this.setPlayer(player);
		this.setLocation(new EdgeLocation(road.getLocation()));
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
