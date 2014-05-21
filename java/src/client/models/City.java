package client.models;

import java.util.Collection;
import java.util.HashSet;

import shared.definitions.ResourceType;
import shared.locations.VertexLocation;

/**
 * @author Craig Call
 *
 */
public class City extends Settlement implements ICity
{
	private static Collection<Resource> cost;
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
		System.out.println("City cost:\t" + City.getResourceCost());
	}
	
	public static Collection<Resource> getResourceCost()
	{
		return cost;
	}
}
