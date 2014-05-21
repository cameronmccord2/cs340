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
	static
	{
		cost = new HashSet<>();
		cost.add(new Resource(ResourceType.ORE, 3));
		cost.add(new Resource(ResourceType.WHEAT, 2));
	}
	
	public City(VertexLocation vertexLocation, IPlayer newPlayer)
	{
		super(vertexLocation, newPlayer);
		this.pointValue = 2;
	}
}
