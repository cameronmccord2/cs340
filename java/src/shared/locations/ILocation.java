package shared.locations;

public interface ILocation
{
	public HexLocation getHexLocation();

	public IDirection getDirection();

	public ILocation getNormalizedLocation();
}
