package client.models;

import shared.definitions.CatanColor;
import shared.locations.EdgeLocation;

/**
 * @author Craig Call
 *
 */
public class Road implements IRoad
{
	private CatanColor color;
	private IPlayer player;
	private boolean isLongestRoad;
	private EdgeLocation location;

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

	@Override
	public CatanColor getColor()
	{
		// TODO Auto-generated method stub
		return color;
	}

	@Override
	public void setColor(CatanColor color) {
		// TODO Auto-generated method stub
		this.color = color;
	}

	@Override
	public IPlayer getPlayer() {
		// TODO Auto-generated method stub
		return player;
	}

	@Override
	public void setPlayer(IPlayer player) {
		// TODO Auto-generated method stub
		this.player = player;
	}

	@Override
	public boolean isLongestRoad() {
		// TODO Auto-generated method stub
		return this.isLongestRoad;
	}

}
