package client.models.interfaces;

import shared.locations.HexLocation;

import java.util.*;

public interface ICatanMap
{
	public boolean canPlaceSettlement(ISettlement settlement);
	public boolean canPlaceCity(ICity city);

	public boolean isOceanHex(HexLocation hex);

	public void setProxy(IProxy proxy);
	public IProxy getProxy();

	public boolean canPlaceRoad(IRoadSegment segment);

	public boolean canMoveRobber(IPlayer player);
	public IPiece distanceRule(IPiece piece);

	public Collection<IHex> getHexes();
	public Collection<IHex> getTerrainHexes();
	public void addHex(IHex hex);

	public Collection<IPort> getPorts();
	public void addPort(IPort port);

	public IRobber getRobber();
	public void setRobber(IRobber robber);

	public Collection<IRoadSegment> getRoads();
	public void placeRoadSegment(IRoadSegment segment) throws InvalidLocationException;

	public Collection<ISettlement> getSettlements();
	public void placeSettlement(ISettlement settlement) throws InvalidLocationException;

	public Collection<ICity> getCities();
	public void placeCity(ICity city) throws InvalidLocationException;

	public Collection<IPiece> getPieces();

	public int getRadius();
	public void setRadius(int radius);

	public void placeInitialSettlement(ISettlement settlement) throws InvalidLocationException;
	public void placeInitialRoadSegment(IRoadSegment segment) throws InvalidLocationException;
	public void placeInitialCity(ICity city) throws InvalidLocationException;
	public Collection<IPiece> getSettlementsAroundHex(HexLocation hex);

	public void setupNewMap(boolean randomTiles, boolean randomNumbers, boolean randomPorts);
	boolean canPlaceSettlement(ISettlement settlement, boolean serverVersion);
	void placeSettlement(ISettlement settlement, boolean isServer)
			throws InvalidLocationException;
	boolean canPlaceRoad(IRoadSegment segment, boolean isServer);
	void placeRoadSegment(IRoadSegment segment, boolean isServer)
			throws InvalidLocationException;
}
