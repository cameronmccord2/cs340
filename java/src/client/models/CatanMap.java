package client.models;

import java.util.*;

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
	private Map<VertexLocation, IPort> portMap;

	private int radius;

	public CatanMap()
	{
		this.hexMap = new HashMap<>();
		this.catanMap = new HashMap<>();
		this.portMap = new HashMap<>();
		this.radius = 3;
	}

	/**
	 *
	 */
	@Override
	public boolean canPlaceRoad(IRoadSegment segment)
	{
		return false;
	}

	/**
	 *
	 *
	 *		@param	settlement
	 *					The ISettlement to be placed at the
	 */
	@Override
	public boolean canPlaceSettlement(ISettlement settlement)
	{
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
		return false;
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
		return null;
	}

	@Override
	public boolean canMoveRobber(IPlayer player)
	{
		return false;
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
	public Collection<IRoad> getRoads()
	{
		return null;
	}

	@Override
	public void placeRoadSegment(IRoadSegment segment)
			throws InvalidLocationException
	{

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

	}

	@Override
	public Collection<ISettlement> getSettlements()
	{
		return null;
	}

	@Override
	public void placeSettlement(ISettlement settlement)
			throws InvalidLocationException
	{

	}

	@Override
	public Collection<ICity> getCities()
	{
		return null;
	}

	@Override
	public void placeCity(ICity city) throws InvalidLocationException
	{

	}

	@Override
	public IRobber getRobber()
	{
		return null;
	}

	@Override
	public void setRobber(IRobber robber)
	{

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

}
