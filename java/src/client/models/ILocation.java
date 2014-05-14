package client.models;

<<<<<<< HEAD
import shared.locations.*;
import shared.definitions.*;

public interface ILocation
=======
>>>>>>> de95b82f55911934df8e276a272baba3d17818fc
{
	public HexLocation getHexLocation();
	public void setHexLocation(HexLocation location);

	public EdgeDirection getEdgeDirection();
	public void setEdgeDirection(EdgeDirection edge);

	public VertexDirection getVertexDirection();
	public void setVertexDirection(VertexDirection vertex);
}
