package client.models;

import shared.definitions.CatanColor;

public interface IPiece
{
	public ILocation getLocation();
	public void setLocation(ILocation location);

	public IPlayer getPlayer();
	public void setPlayer(IPlayer player);

	public CatanColor getColor();
	public void setColor(CatanColor color);
}
