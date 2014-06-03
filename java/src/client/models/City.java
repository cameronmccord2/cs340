package client.models;

import java.util.Collection;
import java.util.HashSet;

import shared.definitions.PieceType;
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
	}
	
	@Override
	public PieceType getPieceType()
	{
		return PieceType.CITY;
	}
	
	public static Collection<Resource> getResourceCost()
	{
		return cost;
	}
	
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("City: [");
		builder.append("pointValue=");
		builder.append(pointValue);
		builder.append("\n	   ");
		if(player == null)
		{
			builder.append("player=null");
			builder.append("\n	   ");
		}
		else
		{
			builder.append("playerId=");
			builder.append(player.getPlayerInfo().getId());
			builder.append("\n	   ");
		}
		builder.append("location=");
		builder.append(location);
		builder.append("\n	   ");
		return builder.toString();
	}
}
