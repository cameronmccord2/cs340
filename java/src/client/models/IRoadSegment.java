package client.models;

import java.util.Collection;

public interface IRoadSegment extends IPiece
{
	public Collection<Resource> getResourceCost();
}
