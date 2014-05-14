package client.models;

import shared.locations.*;
import shared.definitions.*;

public interface ILocation
{
	public HexLocation getHexLocation();
	public void setHexLocation(HexLocation location);

	public EdgeDirection getEdgeDirection();
	public void setEdgeDirection(EdgeDirection edge);

	public VertexDirection getVertexDirection();
	public void setVertexDirection(VertexDirection vertex);
}
