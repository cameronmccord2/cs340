package client.models;

import client.models.translator.TRHex;
import shared.locations.*;
import shared.definitions.*;

public class Hex implements IHex
{
	private HexLocation location;
	private int hexNumber;
	private HexType type;

	public Hex()
	{
		this.setHexNumber(0);
		this.setHexType(HexType.DESERT);
	}

	public Hex(TRHex hex) {
		this.location = new HexLocation(hex.getLocation().getX(), hex.getLocation().getY());
		if(hex.getNumber() != null)
			this.hexNumber = hex.getNumber();
		if(hex.getResource() != null)
			switch(hex.getResource().toUpperCase()){
			case "WOOD":
				this.type = HexType.WOOD;
				break;
				
			case "BRICK":
				this.type = HexType.BRICK;
				break;
				
			case "SHEEP":
				this.type = HexType.SHEEP;
				break;
				
			case "WHEAT":
				this.type = HexType.WHEAT;
				break;
				
			case "ORE":
				this.type = HexType.ORE;
				break;
				
			case "DESERT":
				this.type = HexType.DESERT;
				break;
				
			case "WATER":
				this.type = HexType.WATER;
				break;
			}
	}

	@Override
	public HexLocation getLocation()
	{
		return location;
	}

	@Override
	public void setLocation(HexLocation location)
	{
		this.location = location;
	}

	/**
	 * @return	The number of the chit associated with
	 * 			this Hex, or 0 if it is the Desert.
	 */
	@Override
	public int getHexNumber()
	{
		return hexNumber;
	}

	@Override
	public void setHexNumber(int number)
	{
		this.hexNumber = number;
	}

	@Override
	public HexType getHexType()
	{
		return type;
	}

	@Override
	public void setHexType(HexType type)
	{
		this.type = type;
	}
}
