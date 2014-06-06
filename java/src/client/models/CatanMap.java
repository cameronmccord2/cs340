package client.models;

import java.util.*;

import client.data.PlayerInfo;
import client.models.exceptions.CantFindGameModelException;
import client.models.interfaces.ICatanMap;
import client.models.interfaces.ICity;
import client.models.interfaces.IFacade;
import client.models.interfaces.IHex;
import client.models.interfaces.IPiece;
import client.models.interfaces.IPlayer;
import client.models.interfaces.IPort;
import client.models.interfaces.IProxy;
import client.models.interfaces.IRoadSegment;
import client.models.interfaces.IRobber;
import client.models.interfaces.ISettlement;
import client.models.interfaces.InvalidLocationException;
import client.models.translator.TRHexLocation;
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
	private Map<HexLocation, IHex> oceanMap;
	private Map<HexLocation, IHex> hexes;
	private Map<ILocation, IPiece> catanMap;
	private Map<ILocation, IPort> portMap;
	private IRobber robber;

	private IProxy proxy;

	private int radius;

	public CatanMap()
	{
		this(null);
	}

	public CatanMap(IProxy proxy)
	{
		this.hexMap = new HashMap<>();
		this.oceanMap = new HashMap<>();
		this.hexes = new HashMap<>();
		this.catanMap = new HashMap<>();
		this.portMap = new HashMap<>();
		this.robber = null;
		this.radius = 3;

		this.proxy = proxy;

		this.initializeOceanHexes();
	}

//	private void initializeOceanHexes()
//	{
//		for(int x = 0; x <= 3; x++)
//		{
//			int a = -3;
//			int b = 3-x;
//
//			IHex first = new Hex();hex
//			HexLocation one = new HexLocation(a, b);
//			first.setLocation(one);
//
//			IHex second = new Hex();
//			HexLocation two = new HexLocation(-b, -a);
//			second.setLocation(two);
//
//			System.out.printf("( a,  b) : (%2d, %2d)\n", a, b);
//			System.out.printf("(-b, -a) : (%2d, %2d)\n", -b, -a);
//
//			hexMap.put(one, first);
//			hexMap.put(two, second);
//		}
//
//		for(int x = 1; x <= 2; x++)
//		{
//			int a = x;
//			int b = 3-x;
//
//			IHex first = new Hex();
//			HexLocation one = new HexLocation(a, b);
//			first.setLocation(one);
//
//			IHex second = new Hex();
//			HexLocation two = new HexLocation(-b, -a);
//			second.setLocation(two);
//
//			hexMap.put(one, first);
//			hexMap.put(two, second);
//		}
//	}

	@Override
	public Collection<IPiece> getSettlementsAroundHex(HexLocation hex) {

	   Collection<IPiece> pieces = new HashSet<>();

	   for(VertexDirection dir: VertexDirection.values()) {
	  	 VertexLocation loc = new VertexLocation(hex, dir).getNormalizedLocation();

	  	 IPiece piece = catanMap.get(loc);
	  	 if (piece != null)
	  		 pieces.add(piece);
		}

		return pieces;
	}

	private void initializeOceanHexes()
	{
		oceanMap.put(new HexLocation(-3,3), new Hex(new HexLocation(-3,3)));
		oceanMap.put(new HexLocation(-3,2), new Hex(new HexLocation(-3,2)));
		oceanMap.put(new HexLocation(-3,1), new Hex(new HexLocation(-3,1)));
		oceanMap.put(new HexLocation(-3,0), new Hex(new HexLocation(-3,0)));

		oceanMap.put(new HexLocation(-2,3), new Hex(new HexLocation(-2,3)));
		oceanMap.put(new HexLocation(-2,-1), new Hex(new HexLocation(-2,-1)));

		oceanMap.put(new HexLocation(-1,3), new Hex(new HexLocation(-1,3)));
		oceanMap.put(new HexLocation(-1,-2), new Hex(new HexLocation(-1,-2)));

		oceanMap.put(new HexLocation(0,3), new Hex(new HexLocation(0,3)));
		oceanMap.put(new HexLocation(0,-3), new Hex(new HexLocation(0,-3)));

		oceanMap.put(new HexLocation(1,2), new Hex(new HexLocation(1,2)));
		oceanMap.put(new HexLocation(1,-3), new Hex(new HexLocation(1,-3)));

		oceanMap.put(new HexLocation(2,1), new Hex(new HexLocation(2,1)));
		oceanMap.put(new HexLocation(2,-3), new Hex(new HexLocation(2,-3)));

		oceanMap.put(new HexLocation(3,0), new Hex(new HexLocation(3,0)));
		oceanMap.put(new HexLocation(3,-1), new Hex(new HexLocation(3,-1)));
		oceanMap.put(new HexLocation(3,-2), new Hex(new HexLocation(3,-2)));
		oceanMap.put(new HexLocation(3,-3), new Hex(new HexLocation(3,-3)));

		hexes.putAll(oceanMap);
	}

	// Very ugly method.
	// 	-Not any more.
	@Override
	public boolean isOceanHex(HexLocation location) {
//		if( hex.equals(new HexLocation(-3,3)) ||
//			hex.equals(new HexLocation(-3,2)) ||
//			hex.equals(new HexLocation(-3,1)) ||
//			hex.equals(new HexLocation(-3,0)) ||
//
//			hex.equals(new HexLocation(-2,3)) ||
//			hex.equals(new HexLocation(-2,-1)) ||
//
//			hex.equals(new HexLocation(-1,3)) ||
//			hex.equals(new HexLocation(-1,-2)) ||
//
//			hex.equals(new HexLocation(0,3)) ||
//			hex.equals(new HexLocation(0,-3)) ||
//
//			hex.equals(new HexLocation(1,2)) ||
//			hex.equals(new HexLocation(1,-3)) ||
//
//			hex.equals(new HexLocation(2,1)) ||
//			hex.equals(new HexLocation(2,-3)) ||
//
//			hex.equals(new HexLocation(3,0)) ||
//			hex.equals(new HexLocation(3,-1)) ||
//			hex.equals(new HexLocation(3,-2)) ||
//			hex.equals(new HexLocation(3,-3)))
//		{
//			return true;
//		}
//		return false;
		return oceanMap.get(location) != null;
	}

	@Override
	public void setProxy(IProxy proxy)
	{
		this.proxy = proxy;
	}

	@Override
	public IProxy getProxy()
	{
		return this.proxy;
	}

	/**
	 *
	 */
	@Override
	public boolean canPlaceRoad(IRoadSegment segment)
	{
		if(catanMap.get(segment.getLocation()) != null)
			return false;

		IFacade facade = proxy.getFacade();
		try
		{
			boolean canPlace = true;
			String state = facade.getCurrentState();
			VertexLocation start = segment.getStartLocation();
			VertexLocation end = segment.getEndLocation();

			for(IPiece piece : catanMap.values())
			{
				PlayerInfo pieceOwner = piece.getPlayer().getPlayerInfo();
				PlayerInfo segmentOwner = segment.getPlayer().getPlayerInfo();

	//			System.out.println("pieceOwner:   " + pieceOwner);
	//			System.out.println("segmentOwner: " + segmentOwner);

				if(pieceOwner.equals(segmentOwner))
				{
					switch(piece.getPieceType())
					{
						case SETTLEMENT:
						case CITY:
							VertexLocation corner = (VertexLocation)piece.getLocation();
							if(corner.equals(start)||corner.equals(end))
								return true;
							break;
						case ROAD:
							// THIS NEEDS TO BE UPDATED TO TAKE INTO ACCOUNT
							// THE OCEAN HEXES! Currently, it allows you to
							// build roads on ocean hex sides.
							IRoadSegment roadSegment = (IRoadSegment)piece;
							if(start.equals(roadSegment.getStartLocation()) ||
							   start.equals(roadSegment.getEndLocation())   ||
							   end.equals(roadSegment.getStartLocation())   ||
							   end.equals(roadSegment.getEndLocation()))
								return true;
							break;
						default:
							break;
					}
				}
			}

			if(state.equals("FirstRound") || state.equals("SecondRound"))
				return canPlace;
		}
		catch(CantFindGameModelException e)
		{

		}
		return false;
	}

	@Override
	public boolean canPlaceSettlement(ISettlement settlement){
		return this.canPlaceSettlement(settlement, false);
	}
	/**
	 *
	 *
	 *	@param	settlement
	 *				The ISettlement to be placed on the map.
	 */
	@Override
	public boolean canPlaceSettlement(ISettlement settlement, boolean serverVersion)
	{
		if(catanMap.get(settlement.getLocation()) != null)
			return false;
		if(serverVersion){
			return true;
		}

		IFacade facade = proxy.getFacade();

		try
		{
			boolean canPlace = true;
			String state = facade.getCurrentState();

			HexLocation hex = settlement.getLocation().getHexLocation();
			if(Math.abs(hex.getX()) > radius || Math.abs(hex.getY()) > radius)
				return false;
			if(distanceRule(settlement) != null)
				return false;

			for(IPiece piece : catanMap.values())
			{
				if(piece.getPlayer().equals(settlement.getPlayer()))
				{
					if(piece.getPieceType() == PieceType.ROAD)
					{
						IRoadSegment roadSegment = (IRoadSegment)piece;
						VertexLocation start = roadSegment.getStartLocation();
						VertexLocation end = roadSegment.getEndLocation();
						VertexLocation corner = (VertexLocation)settlement.getLocation();
						if(corner.equals(start) || corner.equals(end))
							return true;
					}
				}
			}

			if(state.equals("FirstRound") || state.equals("SecondRound"))
				return canPlace;
		}
		catch(CantFindGameModelException e)
		{
			e.printStackTrace();
		}

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

			adjacentCorners.add(left.getNormalizedLocation());
			adjacentCorners.add(above.getNormalizedLocation());
			adjacentCorners.add(below.getNormalizedLocation());
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

			adjacentCorners.add(right.getNormalizedLocation());
			adjacentCorners.add(above.getNormalizedLocation());
			adjacentCorners.add(below.getNormalizedLocation());
		}
		else
		{
			assert false;
		}

		for(VertexLocation corner : adjacentCorners)
		{
			conflictPiece = catanMap.get(corner);
//			System.out.println(conflictPiece);
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
		return hexes.values();
	}

	@Override
	public Collection<IHex> getTerrainHexes()
	{
		return hexMap.values();
	}

	@Override
	public void addHex(IHex hex)
	{
		hexMap.put(hex.getLocation(), hex);
		hexes.put(hex.getLocation(), hex);
	}

	@Override
	public Collection<IRoadSegment> getRoads()
	{
		Collection<IRoadSegment> roads = new HashSet<>();
		for(IPiece piece : catanMap.values())
		{
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
		for(IPiece piece : catanMap.values())
		{
			if(piece.getPieceType() == PieceType.SETTLEMENT)
				settlements.add((ISettlement)piece);
		}
		return settlements;
	}
	@Override
	public void placeSettlement(ISettlement settlement)
			throws InvalidLocationException{
		this.placeSettlement(settlement, false);
	}
	@Override
	public void placeSettlement(ISettlement settlement, boolean isServer)
			throws InvalidLocationException
	{
		if(!canPlaceSettlement(settlement, isServer))
			throw new InvalidLocationException();
		catanMap.put(settlement.getLocation(), settlement);
	}

	@Override
	public Collection<ICity> getCities()
	{
		Collection<ICity> cities = new HashSet<>();
		for(IPiece piece : catanMap.values())
		{
			if(piece.getPieceType() == PieceType.CITY)
				cities.add((ICity)piece);
		}
		return cities;
	}

	@Override
	public Collection<IPiece> getPieces()
	{
		return catanMap.values();
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

	@Override
	public void placeInitialSettlement(ISettlement settlement) throws InvalidLocationException
	{
		if(distanceRule(settlement) != null)
			throw new InvalidLocationException();
		catanMap.put(settlement.getLocation(), settlement);
	}

	@Override
	public void placeInitialRoadSegment(IRoadSegment segment) throws InvalidLocationException
	{
		if(catanMap.get(segment.getLocation()) != null)
			throw new InvalidLocationException();
		catanMap.put(segment.getLocation(), segment);
	}

	@Override
	public void placeInitialCity(ICity city) throws InvalidLocationException
	{
		if(catanMap.get(city.getLocation()) != null)
			throw new InvalidLocationException();
		catanMap.put(city.getLocation(), city);
	}

	@Override
	public String toString()
	{
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

	@Override
	public void setupNewMap(boolean randomTiles, boolean randomNumbers, boolean randomPorts) {

		MapBuilder builder = new MapBuilder();

		builder.setupLandHexes(this,randomNumbers,randomTiles);
		builder.setupPorts(this, randomPorts);

		initializeOceanHexes();
	}
}
