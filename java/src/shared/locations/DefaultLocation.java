package shared.locations;

public class DefaultLocation implements ILocation
{

	@Override
	public HexLocation getHexLocation()
	{
		return null;
	}

	@Override
	public void setHexLocation(HexLocation hexLocation)
	{

	}

	@Override
	public IDirection getDirection()
	{
		return null;
	}

	@Override
	public ILocation getNormalizedLocation()
	{
		return null;
	}

	@Override
	public <D extends IDirection> void setDirection(D direction)
	{

	}

}
