package client.models;

import shared.definitions.CatanColor;
import shared.locations.ILocation;

public interface IPiece
{
	public ILocation getLocation();
	public void setLocation(ILocation location);

	public IPlayer getPlayer();
	public void setPlayer(IPlayer player);

	public CatanColor getColor();
	public void setColor(CatanColor color);
}
