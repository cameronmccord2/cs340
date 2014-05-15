package client.models;

import shared.locations.VertexLocation;

/**
 * @author Craig Call
 *
 */
public class City extends Settlement implements ICity
{
	public City()
	{
		this.pointValue = 2;
	}

	public City(VertexLocation vertexLocation, IPlayer iPlayer, int points) {
		super(vertexLocation, (Player)iPlayer, points);
	}
}
