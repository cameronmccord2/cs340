package client.models;

import java.util.*;

public class CatanMap implements ICatanMap
{
	private Map<ILocation, IPiece> map;

	public CatanMap()
	{
		map = new HashMap<>();
	}

	@Override
	public boolean canPlaceSegment(IRoadSegment segment)
	{
		// TODO Check the location to see if we can add an
		// IRoadSegment on the map.
		return false;
	}

	@Override
	public boolean canPlaceSettlement(ISettlement settlement)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPlaceCity(ICity city)
	{
		// TODO Auto-generated method stub
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
		return 0;
	}

	@Override
	public void setRadius(int radius)
	{
		// TODO Auto-generated method stub

	}

}
