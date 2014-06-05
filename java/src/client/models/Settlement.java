package client.models;

import java.util.Collection;
import java.util.HashSet;

import client.models.interfaces.IPlayer;
import client.models.interfaces.ISettlement;
import shared.definitions.PieceType;
import shared.definitions.ResourceType;
import shared.locations.ILocation;

/**
 * @author Craig Call
 *
 */
public class Settlement extends Piece implements ISettlement
{
	protected int pointValue;
	private static Collection<Resource> cost;
	static
	{
		cost = new HashSet<>();
		cost.add(new Resource(ResourceType.BRICK, 1));
		cost.add(new Resource(ResourceType.WOOD, 1));
		cost.add(new Resource(ResourceType.SHEEP, 1));
		cost.add(new Resource(ResourceType.WHEAT, 1));
	}

	public Settlement(ILocation location, IPlayer player)
	{
		this.setLocation(location);
		this.setPlayer(player);
		this.pointValue = 1;
	}

	@Override
	public PieceType getPieceType()
	{
		return PieceType.SETTLEMENT;
	}

	public static Collection<Resource> getResourceCost()
	{
		return cost;
	}

	@Override
	public int getPointValue()
	{
		return pointValue;
	}

	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Settlement: [");
		builder.append("pointValue=");
		builder.append(pointValue);
		builder.append("\n			 ");
		if(player == null)
		{
			builder.append("player=null");
			builder.append("\n			 ");
		}
		else
		{
			builder.append("playerId=");
			builder.append(player.getPlayerInfo().getId());
			builder.append("\n			 ");
		}
		builder.append("location=");
		builder.append(location);
		builder.append("\n			 ");
		return builder.toString();
	}
}
