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
	
	@Override
	public void isValid() throws InvalidTranslatorModelException{
		if(this.hexes == null || this.ports == null || this.roads == null || this.settlements == null || this.cities == null)
			throw new InvalidTranslatorModelException(this.toString());
		for (TRHex hex : this.hexes) {
			hex.isValid();
		}
		for (TRPort port : this.ports) {
			port.isValid();
		}
		for (TRRoad road : this.roads) {
			road.isValid();
		}
		for (TRVertexObject sett : this.settlements) {
			sett.isValid();
		}
		for (TRVertexObject city : this.cities) {
			city.isValid();
		}
		this.robber.isValid();
		if(radius < 0)
			throw new InvalidTranslatorModelException(this.toString());
	}
	
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
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TRMap [hexes=");
		builder.append(hexes);
		builder.append(", ports=");
		builder.append(ports);
		builder.append(", roads=");
		builder.append(roads);
		builder.append(", settlements=");
		builder.append(settlements);
		builder.append(", cities=");
		builder.append(cities);
		builder.append(", radius=");
		builder.append(radius);
		builder.append(", robber=");
		builder.append(robber);
		builder.append("]");
		return builder.toString();
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