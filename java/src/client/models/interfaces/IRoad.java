package client.models.interfaces;

import java.util.Collection;

public interface IRoad
{
	public boolean isLongestRoad();

	public Collection<IRoadSegment> getRoadSegments();
	public void buildRoad(IRoadSegment segment);
}
