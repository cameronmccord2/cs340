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

public class CatanMap implements ICatanMap
{
	private Map<HexLocation, IHex> hexMap;
	private Map<EdgeLocation, IRoad> roadMap;
	private Map<VertexLocation, ISettlement> settlementMap;
	private Map<VertexLocation, ICity> cityMap;
	private Map<VertexLocation, IPort> portMap;
	private int radius;

	public CatanMap()
	{
		hexMap = new HashMap<>();
		roadMap = new HashMap<>();
		settlementMap = new HashMap<>();
		cityMap = new HashMap<>();
		portMap = new HashMap<>();
	}

	/**
	 *
	 */
	@Override
	public boolean canPlaceSegment(IRoadSegment segment)
	{
		// TODO Check the location to see if we can add an
		// IRoadSegment on the map.
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 	This is intended to be a helper method for the "can do"
	 * 	methods, such as determining whether or not a
	 * 	settlement can be built. Cities implicitly must follow
	 * 	this rule as a Settlement must exist prior to building
	 * 	a city.
	 *
	 *		@param	location
	 *						The location where a player wants to place
	 *						a settlement or city.
	 */
	@Override
	public boolean doesDistanceRuleApply(VertexLocation location)
	{
		return false;
	}

	@Override
	public boolean canMoveRobber(IPlayer player)
	{
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		hexMap.put(hex.getLocation(), hex);
	}

	@Override
	public Collection<IRoad> getRoads()
	{
		Collection<IRoad> roads = roadMap.values();
		return roads;
	}

	@Override
	public void placeRoadSegment(IRoadSegment segment)
			throws InvalidLocationException
	{
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<ISettlement> getSettlements()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void placeSettlement(ISettlement settlement)
			throws InvalidLocationException
	{
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<ICity> getCities()
	{
		Collection<ICity> cities = cityMap.values();
		return cities;
	}

	@Override
	public void placeCity(ICity city) throws InvalidLocationException
	{
		// TODO Auto-generated method stub

	}

	@Override
	public IRobber getRobber()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRobber(IRobber robber)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public int getRadius()
	{
		// TODO Auto-generated method stub
		return radius;
	}

	@Override
	public void setRadius(int radius)
	{
		// TODO Auto-generated method stub
		this.radius = radius;
	}

}
