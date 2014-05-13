package client.models;

import shared.definitions.CatanColor;
import shared.locations.EdgeLocation;

public interface IRoadSegment {
	public EdgeLocation getEdgeLocation();
	public void setEdgeLocation(EdgeLocation egde);
	
	public CatanColor getColor();
	public void setColor(CatanColor color);
	
	public IPlayer getPlayer();
	public void setPlayer(IPlayer player);
}
