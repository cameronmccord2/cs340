package client.models;

import java.util.Collection;

import shared.definitions.CatanColor;
import shared.definitions.PieceType;
import shared.locations.ILocation;

public abstract class Piece implements IPiece
{
	protected IPlayer player;
	protected CatanColor color;
	protected ILocation location;

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
		return player;
	}

	@Override
	public void setPlayer(IPlayer player)
	{
		this.player = player;
	}

	@Override
	public CatanColor getColor()
	{
		return color;
	}

	@Override
	public void setColor(CatanColor color)
	{
		this.color = color;
	}

	@Override
	public abstract PieceType getPieceType();

	@Override
	public abstract Collection<Resource> getResourceCost();

}
