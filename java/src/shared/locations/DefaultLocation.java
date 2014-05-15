package shared.locations;

public class DefaultLocation implements ILocation
{
	
	protected HexLocation hexLocation;
	protected IDirection direction;

	public DefaultLocation(HexLocation hexLocation2, VertexDirection direction2) {
		this.hexLocation = hexLocation2;
		this.direction = direction2;
	}

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
	public void setDirection(IDirection direction) {
		this.direction = direction;
	}

}
