package shared.locations;

public interface ILocation
{
	public HexLocation getHexLocation();
	public void setHexLocation(HexLocation location);

	public IDirection getDirection();
	public <D extends IDirection> void setDirection(D direction);

	public ILocation getNormalizedLocation();
}
