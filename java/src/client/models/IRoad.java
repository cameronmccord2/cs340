package client.models;

import java.util.Collection;

import shared.definitions.CatanColor;

public interface IRoad
{
	public boolean isLongestRoad();

	public Collection<IRoadSegment> getRoadSegments();
	public void buildRoad(IRoadSegment segment);
}
