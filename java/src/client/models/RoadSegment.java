package client.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import client.models.interfaces.IPlayer;
import client.models.interfaces.IRoadSegment;
import client.models.translator.TRRoad;
import shared.definitions.PieceType;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

public class RoadSegment extends Piece implements IRoadSegment, Serializable
{
	private static Collection<Resource> cost;
	static
	{
		cost = new HashSet<>();
		cost.add(new Resource(ResourceType.BRICK, 1));
		cost.add(new Resource(ResourceType.WOOD, 1));
	}

	public RoadSegment()
	{

	}

	public RoadSegment(TRRoad road, IPlayer player)
	{
		this();
		this.setPlayer(player);
		this.setLocation(new EdgeLocation(road.getLocation()));
	}

	public static Collection<Resource> getResourceCost()
	{
		return cost;
	}

	@Override
	public PieceType getPieceType()
	{
		return PieceType.ROAD;
	}

	@Override
	public VertexLocation getStartLocation()
	{
		EdgeLocation roadEdge = (EdgeLocation)this.getLocation();
		VertexDirection direction = VertexDirection.NorthWest;
		switch(roadEdge.getDirection())
		{
			case North:
				direction = VertexDirection.NorthWest;
				break;
			case NorthEast:
				direction = VertexDirection.NorthEast;
				break;
			case SouthEast:
				direction = VertexDirection.SouthEast;
				break;
			case South:
				direction = VertexDirection.SouthWest;
				break;
			case SouthWest:
				direction = VertexDirection.West;
				break;
			case NorthWest:
				direction = VertexDirection.West;
				break;
		}
		VertexLocation start = new VertexLocation(roadEdge.getHexLocation(),
												  direction);
		return start.getNormalizedLocation();
	}

	@Override
	public VertexLocation getEndLocation()
	{
		EdgeLocation roadEdge = (EdgeLocation)this.getLocation();
		VertexDirection direction = VertexDirection.NorthWest;
		switch(roadEdge.getDirection())
		{
			case North:
				direction = VertexDirection.NorthEast;
				break;
			case NorthEast:
				direction = VertexDirection.East;
				break;
			case SouthEast:
				direction = VertexDirection.East;
				break;
			case South:
				direction = VertexDirection.SouthEast;
				break;
			case SouthWest:
				direction = VertexDirection.SouthWest;
				break;
			case NorthWest:
				direction = VertexDirection.NorthWest;
				break;
		}
		VertexLocation end = new VertexLocation(roadEdge.getHexLocation(),
												direction);
		return end.getNormalizedLocation();
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("RoadSegment [player=");
		if(player != null)
			builder.append(player.getPlayerInfo().getId());
		else
			builder.append("null");
		builder.append(", color=");
		builder.append(color);
		builder.append(", location=");
		builder.append(location);
		builder.append(", getPieceType()=");
		builder.append(getPieceType());
		builder.append("]");
		return builder.toString();
	}


}
