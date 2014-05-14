package client.models;

import java.util.*;

public class CatanMap implements ICatanMap
{
	private Map<ILocation, IPiece> map;
	private int radius;

	public CatanMap()
	{
		map = new HashMap<>();
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
	 *
	 */
	@Override
	public boolean doesDistanceRuleApply(ILocation first, ILocation second)
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addHex(IHex hex)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<IRoadSegment> getRoads()
	{
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
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
