package client.models;

import java.util.Collection;

import shared.definitions.CatanColor;
import shared.definitions.PieceType;
import shared.locations.ILocation;

public interface IPiece
{
	public ILocation getLocation();
	public void setLocation(ILocation location);

	public IPlayer getPlayer();
	public void setPlayer(IPlayer player);

	public PieceType getPieceType();
}
