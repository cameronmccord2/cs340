package client.models;

import java.util.*;

public interface ICatanMap
{
	public boolean canBuildSettlement(IPlayer player, ISettlement settlement);
	public boolean canPlaceSettlement(ISettlement settlement);

	public boolean canBuildCity(IPlayer player, ICity city);
	public boolean canPlaceCity(ICity city);

	public boolean canBuildRoad(IPlayer player, IRoadSegment segment);
	public boolean canPlaceRoad(IRoadSegment segment);

	public boolean canMoveRobber(IPlayer player);
	public IPiece distanceRule(IPiece piece);

	public Collection<IHex> getHexes();
	public void addHex(IHex hex);

	public Collection<IPort> getPorts();
	public void addPort(IPort port);

	public IRobber getRobber();
	public void setRobber(IRobber robber);

	public Collection<IRoad> getRoads();
	public void placeRoadSegment(IRoadSegment segment) throws InvalidLocationException;

	public Collection<ISettlement> getSettlements();
	public void placeSettlement(ISettlement settlement) throws InvalidLocationException;

	public Collection<ICity> getCities();
	public void placeCity(ICity city) throws InvalidLocationException;

	public int getRadius();
	public void setRadius(int radius);
	
}
