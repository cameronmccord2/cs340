package client.models;

import java.util.Collection;

import shared.definitions.CatanColor;

public interface IRoad {
	public CatanColor getColor();
	public void setColor(CatanColor color);

	public IPlayer getPlayer();
	public void setPlayer(IPlayer player);

	public boolean isLongestRoad();

	public Collection<IRoadSegment> getRoadSegments();
	public void buildRoad(IRoadSegment segment);
}
