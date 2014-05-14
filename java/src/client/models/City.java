package client.models;

import shared.locations.VertexLocation;

/**
 * @author Craig Call
 *
 */
public class City extends Settlement implements ICity
{
	public City(VertexLocation vertexLocation, IPlayer newPlayer, int points) {
		super(vertexLocation, newPlayer, points);
	}
}
