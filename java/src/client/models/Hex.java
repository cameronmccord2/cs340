package client.models;

import java.io.Serializable;

import client.models.interfaces.IHex;
import client.models.translator.TRHex;
import shared.locations.*;
import shared.definitions.*;

public class Hex implements IHex, Serializable
{
	private HexLocation location;
	private Integer hexNumber;
	private HexType type;

	public Hex()
	{
		this.setHexNumber(null);
		this.setHexType(HexType.WATER);
	}
	
	public Hex(HexLocation location)
	{
		this();
		this.setLocation(location);
	}

	public Hex(HexLocation loc, HexType type) {
		this.setLocation(loc);
		this.setHexType(type);
	}

	public Hex(TRHex hex) {
		this.location = new HexLocation(hex.getLocation().getX(), hex.getLocation().getY());
		if(hex.getNumber() != null)
			this.hexNumber = hex.getNumber();
		else
			this.hexNumber = null;
		
		// This isthe preferred, dynamic way to get an enum from a String
		// instead of using a switch statement.
		try
		{
			if(hex.getResource() != null)
				this.type = HexType.valueOf(hex.getResource().toUpperCase());
			else
				this.type = HexType.DESERT;
		}
		catch(IllegalArgumentException e)
		{
			this.type = HexType.DESERT;
		}
		
//		if(hex.getResource() != null)
//			switch(hex.getResource().toUpperCase()){
//			case "WOOD":
//				this.type = HexType.WOOD;
//				break;
//				
//			case "BRICK":
//				this.type = HexType.BRICK;
//				break;
//				
//			case "SHEEP":
//				this.type = HexType.SHEEP;
//				break;
//				
//			case "WHEAT":
//				this.type = HexType.WHEAT;
//				break;
//				
//			case "ORE":
//				this.type = HexType.ORE;
//				break;
//				
//			case "DESERT":
//				this.type = HexType.DESERT;
//				break;
//				
//			case "WATER":
//				this.type = HexType.WATER;
//				break;
//			}
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
	public Integer getHexNumber()
	{
		return hexNumber;
	}

	@Override
	public void setHexNumber(Integer number)
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Hex [location=");
		builder.append(location);
		builder.append(", hexNumber=");
		builder.append(hexNumber);
		builder.append(", type=");
		builder.append(type);
		builder.append("]");
		return builder.toString();
	}
}
