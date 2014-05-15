package client.models;

import java.util.Collection;

import shared.definitions.CatanColor;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.ILocation;

/**
 * @author Craig Call
 *
 */
@SuppressWarnings({"unused"})
public class Road implements IRoad
{
	private CatanColor color;
	private IPlayer player;
	private boolean isLongestRoad;
	private ILocation location;

	public Road(CatanColor color, IPlayer player)
	{
		this(color, player, false);
	}

	public Road(CatanColor color, IPlayer player, boolean isLongestRoad)
	{
		this.setColor(color);
		this.setPlayer(player);
		this.isLongestRoad = isLongestRoad;
	}

	public Road(CatanColor color, IPlayer player, boolean isLongestRoad, EdgeLocation location)
	{
		this(color, player, isLongestRoad);
		this.location = location;
	}

	public CatanColor getColor()
	{
		return color;
	}

	public void setColor(CatanColor color)
	{
		this.color = color;
	}

	public IPlayer getPlayer() {
		return player;
	}

	public void setPlayer(IPlayer player)
	{
		this.player = player;
	}

	@Override
	public boolean isLongestRoad()
	{
		return this.isLongestRoad;
	}

	@Override
	public Collection<IRoadSegment> getRoadSegments()
	{
		return null;
	}

	@Override
	public void buildRoad(IRoadSegment segment)
	{

	}


}
