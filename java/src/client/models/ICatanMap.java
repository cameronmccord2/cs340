package client.models;

import java.util.*;

public interface ICatanMap {
	public boolean canPlaceSegment(IRoadSegment segment);
	public boolean canPlaceSettlement(ISettlement settlement);
	public boolean canPlaceCity(ICity city);
	
	public Collection<IHex> getHexes();
	public void addHex(IHex hex);
	
	public Collection<IRoadSegment> getRoads();
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
