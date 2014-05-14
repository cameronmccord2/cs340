package client.models;

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
