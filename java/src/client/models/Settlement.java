package client.models;

/**
 * @author Craig Call
 *
 */
public class Settlement implements ISettlement
{
	protected IPlayer player;
	protected VertexLocation location;
	protected int pointValue;
	
	public Settlement()
	{
		
	}
	
	public VertexLocation getVertexLocation()
	{
		return location;
	}
	
	public void setVertexLocation(VertexLocation location)
	{
		this.location = location;
	}
	
	public IPlayer getPlayer()
	{
		return player;
	}
	
	public void setPlayer(IPlayer player)
	{
		this.player = player;
	}
	
	public int getPointValue()
	{
		return pointValue;
	}
}
