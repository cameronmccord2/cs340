package shared.locations;

public interface IDirection
{
	public IDirection getOppositeDirection();
	
	public String asServerString();
}
