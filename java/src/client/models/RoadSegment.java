package client.models;

import java.util.Collection;

import client.models.translator.TRRoad;
import shared.definitions.CatanColor;
import shared.definitions.PieceType;
import shared.locations.DefaultLocation;
import shared.locations.HexLocation;
import shared.locations.ILocation;
import shared.locations.VertexDirection;

public class RoadSegment extends Piece implements IRoadSegment
{
	private Collection<Resource> cost;

	public RoadSegment()
	{

	}

	public RoadSegment(TRRoad road, Player player) {
		this.location = new DefaultLocation(new HexLocation(road.getLocation().getX(), road.getLocation().getY()), VertexDirection.getDirectionFromServerString(road.getLocation().getDirection()));
		this.player = player;
		this.color = player.getPlayerInfo().getColor();
	}

	@Override
	public ILocation getLocation()
	{
		this.setPlayer(player);
		this.setLocation(new EdgeLocation(road.getLocation()));
	}

	@Override
	public void setLocation(ILocation location)
	{
		this.location = location;
	}

	@Override
	public IPlayer getPlayer()
	{
		return this.player;
	}

	@Override
	public void setPlayer(IPlayer player)
	{
		this.player = player;
	}

	@Override
	public CatanColor getColor()
	{
		return this.color;
	}

	@Override
	public void setColor(CatanColor color)
	{
		this.color = color;
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
