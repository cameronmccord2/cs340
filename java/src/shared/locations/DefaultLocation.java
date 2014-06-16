package shared.locations;

import java.io.Serializable;

public class DefaultLocation implements ILocation, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7778185721824277645L;
	protected HexLocation hexLocation;
	protected IDirection direction;

	public DefaultLocation()
	{
		this(null, null);
	}

	public DefaultLocation(HexLocation hexLocation, VertexDirection direction) {
		this.hexLocation = hexLocation;
		this.direction = direction;
	}

	@Override
	public HexLocation getHexLocation()
	{
		return hexLocation;
	}

	@Override
	public void setHexLocation(HexLocation hexLocation)
	{
		this.hexLocation = hexLocation;
	}

	@Override
	public IDirection getDirection()
	{
		return direction;
	}

	@Override
	public ILocation getNormalizedLocation()
	{
		return this;
	}

	@Override
	public void setDirection(IDirection direction) {
		this.direction = direction;
	}

}
