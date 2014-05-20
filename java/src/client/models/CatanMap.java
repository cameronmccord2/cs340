package client.models;

import java.util.*;

import shared.definitions.PieceType;
import shared.locations.*;

/**
 * Okay.  So here are some important notes about the x-y system that
 * Dr. Rodham is using in his MapView/MapComponent/MapController.
 *
 * The center Hex is located at x=0, y=0 and radiates outward from
 * there. The column in the center is the y-axis.  The diagonal that
 * goes from the top left corner to the bottom right corner is the
 * x-axis.  The positive y-axis is DOWNWARD.  The positive x-axis is
 * RIGHTWARD.
 *
 *
 * @author Craig Call
 *
 */

@SuppressWarnings({"unused"})
public class CatanMap implements ICatanMap
{
	private Map<HexLocation, IHex> hexMap;
	private Map<ILocation, IPiece> catanMap;
	private Map<ILocation, IPort> portMap;
	private IRobber robber;
	
	private IProxy proxy;

	private int radius;

	public CatanMap()
	{
		this.hexMap = new HashMap<>();
		this.catanMap = new HashMap<>();
		this.portMap = new HashMap<>();
		this.robber = null;
		this.radius = 3;
	}
	
	public void setProxy(IProxy proxy)
	{
		this.proxy = proxy;
	}

	/**
	 *
	 */
	@Override
	public boolean canPlaceRoad(IRoadSegment segment)
	{
		if(catanMap.get(segment.getLocation()) != null)
			return false;
		
		for(IPiece piece : catanMap.values())
		{
			if(piece.getPlayer().equals(segment.getPlayer()))
			{
				
			}
		}
		return false;
	}

	/**
	 *
	 *
	 *		@param	settlement
	 *					The ISettlement to be placed on the map.
	 */
	@Override
	public boolean canPlaceSettlement(ISettlement settlement)
	{
		HexLocation hex = settlement.getLocation().getHexLocation();
		if(Math.abs(hex.getX()) > radius || Math.abs(hex.getY()) > radius)
			return false;
		
		return false;
	}

	/**
	 * 	Only an existing settlement can be upgraded to a
	 * 	City.
	 *
	 * 	@param	city
	 * 				The ICity that is to replace the current
	 * 				settlement at that location.
	 *
	 * 	@return	Whether or not that ICity can be placed
	 * 				on the requested Location.
	 */
	@Override
	public boolean canPlaceCity(ICity city)
	{
		IPiece piece = catanMap.get(city.getLocation());
		if(piece == null)
			return false;
		if(piece.getPieceType() != PieceType.SETTLEMENT)
			return false;
		if(!piece.getPlayer().equals(city.getPlayer()))
			return false;
		return true;
	}

	/**
	 * 	This is intended to be a helper method for the "can do"
	 * 	methods, such as determining whether or not a
	 * 	settlement can be built. Cities implicitly must follow
	 * 	this rule as a Settlement must exist prior to building
	 * 	a city.
	 *
	 *		@param	piece
	 *						The piece that a player wants to place.
	 *
	 *		@return	the piece that causes a conflict, or null if
	 *					there is no conflict.
	 */
	@Override
	public IPiece distanceRule(IPiece piece)
	{
		/*
		 * Things to consider for the algorithm:
		 * 		: We need only consider the three adjacent
		 * 			verteces, which are:
		 * 				if (x,y).NE then
		 * 					+ (x  , y  ).NW -> left
		 * 					+ (x+1, y-1).NW -> above
		 * 					+ (x+1, y  ).NW -> below
		 * 				if (x,y).NW then
		 * 					+ (x  , y  ).NE -> right
		 * 					+ (x-1, y  ).NE -> above
		 * 					+ (x-1, y+1).NE -> below
		 * 
		 */
		IPiece conflictPiece = null;
		VertexLocation location = (VertexLocation)piece.getLocation();
		List<VertexLocation> adjacentCorners = new ArrayList<>();
		
		if(location.getDirection() == VertexDirection.NorthEast)
		{
			HexLocation hex = location.getHexLocation();
			VertexDirection direction = VertexDirection.NorthWest;
			
			VertexLocation left = new VertexLocation(hex, direction);
			
			HexLocation aboveHex = new HexLocation(hex.getX()+1, hex.getY()-1);
			VertexLocation above = new VertexLocation(aboveHex, direction);
			
			HexLocation belowHex = new HexLocation(hex.getX()+1, hex.getY());
			VertexLocation below = new VertexLocation(belowHex, direction);
			
			adjacentCorners.add(left);
			adjacentCorners.add(above);
			adjacentCorners.add(below);
		}
		else if(location.getDirection() == VertexDirection.NorthWest)
		{
			HexLocation hex = location.getHexLocation();
			VertexDirection direction = VertexDirection.NorthEast;
			
			VertexLocation right = new VertexLocation(hex, direction);
			
			HexLocation aboveHex = new HexLocation(hex.getX()-1, hex.getY());
			VertexLocation above = new VertexLocation(aboveHex, direction);
			
			HexLocation belowHex = new HexLocation(hex.getX()-1, hex.getY()+1);
			VertexLocation below = new VertexLocation(belowHex, direction);
			
			adjacentCorners.add(right);
			adjacentCorners.add(above);
			adjacentCorners.add(below);
		}
		else
		{
			assert false;
		}
		
		for(VertexLocation corner : adjacentCorners)
		{
			conflictPiece = catanMap.get(corner.getNormalizedLocation());
			if(conflictPiece != null)
				return conflictPiece;
		}

		return conflictPiece;
	}

	@Override
	public boolean canMoveRobber(IPlayer player)
	{
		int playerId = player.getPlayerInfo().getId();
		return true;
	}

	@Override
	public Collection<IHex> getHexes()
	{
		Collection<IHex> hexes = hexMap.values();
		return hexes;
	}

	@Override
	public void addHex(IHex hex)
	{
		hexMap.put(hex.getLocation(), hex);
	}

	@Override
	public Collection<IRoadSegment> getRoads()
	{
		Collection<IRoadSegment> roads = new HashSet<>();
		for(ILocation location : catanMap.keySet())
		{
			IPiece piece = catanMap.get(location);
			if(piece.getPieceType() == PieceType.ROAD)
				roads.add((IRoadSegment)piece);
		}
		return roads;
	}

	@Override
	public void placeRoadSegment(IRoadSegment segment)
			throws InvalidLocationException
	{
		if(!canPlaceRoad(segment))
			throw new InvalidLocationException();
		catanMap.put(segment.getLocation(), segment);
	}

	@Override
	public Collection<IPort> getPorts()
	{
		Collection<IPort> ports = portMap.values();
		return ports;
	}

	@Override
	public void addPort(IPort port)
	{
		portMap.put(port.getLocation(), port);
	}

	@Override
	public Collection<ISettlement> getSettlements()
	{
		Collection<ISettlement> settlements = new HashSet<>();
		for(ILocation location : catanMap.keySet())
		{
			IPiece piece = catanMap.get(location);
			if(piece.getPieceType() == PieceType.SETTLEMENT)
				settlements.add((ISettlement)piece);
		}
		return settlements;
	}

	@Override
	public void placeSettlement(ISettlement settlement)
			throws InvalidLocationException
	{
		if(!canPlaceSettlement(settlement))
			throw new InvalidLocationException();
		catanMap.put(settlement.getLocation(), settlement);
	}

	@Override
	public Collection<ICity> getCities()
	{
		Collection<ICity> cities = new HashSet<>();
		for(ILocation location : catanMap.keySet())
		{
			IPiece piece = catanMap.get(location);
			if(piece.getPieceType() == PieceType.CITY)
				cities.add((ICity)piece);
		}
		return cities;
	}

	@Override
	public void placeCity(ICity city) throws InvalidLocationException
	{
		if(!canPlaceCity(city))
			throw new InvalidLocationException();
		catanMap.put(city.getLocation(), city);
	}

	/**
	 * @return	the robber or null if on the desert or uninitialized.
	 */
	@Override
	public IRobber getRobber()
	{
		return robber;
	}

	@Override
	public void setRobber(IRobber robber)
	{
		this.robber = robber;
	}

	@Override
	public int getRadius()
	{
		return radius;
	}

	@Override
	public void setRadius(int radius)
	{
		this.radius = radius;
	}

	/*
	 * I just realized that these help methods should go in the
	 * Player implementation because these are intended to check
	 * whether or not the player has the proper amount of resources.
	 */
	
	@Override
	public boolean canBuildSettlement(IPlayer player, ISettlement settlement)
	{
		return false;
	}

	@Override
	public boolean canBuildCity(IPlayer player, ICity city)
	{
		return false;
	}

	@Override
	public boolean canBuildRoad(IPlayer player, IRoadSegment segment)
	{
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CatanMap [hexMap=");
		builder.append(hexMap);
		builder.append(", catanMap=");
		builder.append(catanMap);
		builder.append(", portMap=");
		builder.append(portMap);
		builder.append(", radius=");
		builder.append(radius);
		builder.append("]");
		return builder.toString();
	}
}
