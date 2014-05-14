package client.models;

import java.util.*;
import shared.locations.*;

public interface ICatanMap {
	public boolean canPlaceSegment(IRoadSegment segment);
	public boolean canPlaceSettlement(ISettlement settlement);
	public boolean canPlaceCity(ICity city);
	public boolean canMoveRobber(IPlayer player);
	public boolean doesDistanceRuleApply(VertexLocation location);
//	This is just a funny default.
//	public boolean canVerbObject(Object object);

	public Collection<IHex> getHexes();
	public void addHex(IHex hex);

	public Collection<IRoad> getRoads();
	public void placeRoadSegment(IRoadSegment segment) throws InvalidLocationException;

	public Collection<IPort> getPorts();
	public void addPort(IPort port);

	public Collection<ISettlement> getSettlements();
	public void placeSettlement(ISettlement settlement) throws InvalidLocationException;

	public Collection<ICity> getCities();
	public void placeCity(ICity city) throws InvalidLocationException;

	public IRobber getRobber();
	public void setRobber(IRobber robber);

	public int getRadius();
	public void setRadius(int radius);
}
