package shared.locations;

public interface ILocation
{
	public HexLocation getHexLocation();
	public void setHexLocation(HexLocation location);

	public IDirection getDirection();

	public ILocation getNormalizedLocation();
	void setDirection(IDirection direction);
}
