package client.models;

import java.util.HashSet;

import shared.definitions.ResourceType;
import shared.locations.VertexLocation;

/**
 * @author Craig Call
 *
 */
public class City extends Settlement implements ICity
{
	public City(VertexLocation vertexLocation, IPlayer newPlayer, int points) {
		super(vertexLocation, newPlayer, points);
		cost = new HashSet<>();
		cost.add(new Resource(ResourceType.ORE, 3));
		cost.add(new Resource(ResourceType.WHEAT, 2));
	}
}
