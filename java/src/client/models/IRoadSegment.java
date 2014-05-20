package client.models;

import java.util.Collection;

import shared.locations.VertexLocation;

public interface IRoadSegment extends IPiece
{
	public Collection<Resource> getResourceCost();
	
	public VertexLocation getStartLocation();
	public VertexLocation getEndLocation();
}
