package client.models.interfaces;

import shared.locations.VertexLocation;

public interface IRoadSegment extends IPiece
{
	public VertexLocation getStartLocation();
	public VertexLocation getEndLocation();
}
