package client.models.translator;

import client.models.exceptions.InvalidTranslatorModelException;

public class TRMap  implements IModelValid{
	protected TRHex[] hexes;
	protected TRPort[] ports;
	protected TRRoad[] roads;
	protected TRVertexObject[] settlements;
	protected TRVertexObject[] cities;
	protected Integer radius;
	protected TRHexLocation robber;
	public TRHex[] getHexes() {
		return hexes;
	}
	public void setHexes(TRHex[] hexes) {
		this.hexes = hexes;
	}
	public TRPort[] getPorts() {
		return ports;
	}
	public void setPorts(TRPort[] ports) {
		this.ports = ports;
	}
	public TRRoad[] getRoads() {
		return roads;
	}
	public void setRoads(TRRoad[] roads) {
		this.roads = roads;
	}
	public TRVertexObject[] getSettlements() {
		return settlements;
	}
	public void setSettlements(TRVertexObject[] settlements) {
		this.settlements = settlements;
	}
	public TRVertexObject[] getCities() {
		return cities;
	}
	public void setCities(TRVertexObject[] cities) {
		this.cities = cities;
	}
	public Integer getRadius() {
		return radius;
	}
	public void setRadius(Integer radius) {
		this.radius = radius;
	}
	public TRHexLocation getRobber() {
		return robber;
	}
	public void setRobber(TRHexLocation robber) {
		this.robber = robber;
	}
	@Override
	public boolean isValid() throws InvalidTranslatorModelException{
		// TODO Auto-generated method stub
		return false;
	}
}
/*
hexes (array[Hex]): A list of all the hexes on the grid - it's only land tiles, 
ports (array[Port]), 
roads (array[Road]), 
settlements (array[VertexObject]), cities (array[VertexObject]), 
radius (integer): The radius of the map (it includes the center hex, and the ocean hexes; pass
this into the hexgrid constructor), 
robber (HexLocation): The current location of the robber
*/