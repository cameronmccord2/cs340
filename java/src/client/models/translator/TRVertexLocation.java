package client.models.translator;

import client.models.exceptions.InvalidTranslatorModelException;

public class TRVertexLocation implements IModelValid
{
	protected Integer x;
	protected Integer y;
	protected String direction;

	public Integer getX()
	{
		return x;
	}
	public void setX(Integer x)
	{
		this.x = x;
	}
	public Integer getY()
	{
		return y;
	}
	public void setY(Integer y)
	{
		this.y = y;
	}
	public String getDirection()
	{
		return direction;
	}
	public void setDirection(String direction)
	{
		this.direction = direction;
	}

	@Override
	public void validate() throws InvalidTranslatorModelException
	{

	}
}
