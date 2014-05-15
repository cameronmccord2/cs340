package client.models;

import client.models.translator.TRPort;
import shared.definitions.PortType;
import shared.locations.HexLocation;
import shared.locations.ILocation;

public class Port implements IPort {
	
	protected ILocation location;
	protected int exchangeRate;
	protected PortType portType;

	public Port(TRPort port) {
		this.location = new HexLocation(port.getLocation().getX(), port.getLocation().getY());
		this.exchangeRate = port.getRatio();
		switch(port.getResource().toUpperCase()){
		case "WOOD":
			this.portType = PortType.WOOD;
			break;
			
		case "BRICK":
			this.portType = PortType.BRICK;
			break;
			
		case "SHEEP":
			this.portType = PortType.SHEEP;
			break;
			
		case "WHEAT":
			this.portType = PortType.WHEAT;
			break;
			
		case "ORE":
			this.portType = PortType.ORE;
			break;
			
		case "THREE":
			this.portType = PortType.THREE;
			break;
		}
	}
	
	@Override
	public int getExchangeRate() {
		return exchangeRate;
	}

	@Override
	public void setExchangeRate(int exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	@Override
	public PortType getPortType() {
		return portType;
	}

	@Override
	public void setPortType(PortType portType) {
		this.portType = portType;
	}

	@Override
	public void setLocation(ILocation location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ILocation getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
