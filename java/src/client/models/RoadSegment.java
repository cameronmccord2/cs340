package client.models;

import java.util.Collection;

import shared.definitions.CatanColor;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.ILocation;

public class RoadSegment implements IRoadSegment
{
	private ILocation location;
	private IPlayer player;
	private CatanColor color;
	private Collection<Resource> cost;

	public RoadSegment()
	{

	}

	@Override
	public ILocation getLocation()
	{
		return location;
	}

	@Override
	public void setLocation(ILocation location)
	{
		this.location = location;
	}

	@Override
	public IPlayer getPlayer()
	{
		return null;
	}

	@Override
	public void setPlayer(IPlayer player)
	{

	}

	@Override
	public CatanColor getColor()
	{
		return null;
	}

	@Override
	public void setColor(CatanColor color)
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
