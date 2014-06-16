package client.models.translator;

import java.io.Serializable;

import client.models.exceptions.InvalidTranslatorModelException;

public class TRResourceList implements IModelValid, Serializable
{
	protected Integer wood;
	protected Integer brick;
	protected Integer ore;
	protected Integer sheep;
	protected Integer wheat;

	public Integer getWood()
	{
		return wood;
	}

	public void setWood(Integer wood)
	{
		this.wood = wood;
	}

	public Integer getBrick()
	{
		return brick;
	}

	public void setBrick(Integer brick)
	{
		this.brick = brick;
	}

	public Integer getOre()
	{
		return ore;
	}

	public void setOre(Integer ore)
	{
		this.ore = ore;
	}

	public Integer getSheep()
	{
		return sheep;
	}

	public void setSheep(Integer sheep)
	{
		this.sheep = sheep;
	}

	public Integer getWheat()
	{
		return wheat;
	}

	public void setWheat(Integer wheat)
	{
		this.wheat = wheat;
	}

	@Override
	public void validate() throws InvalidTranslatorModelException
	{
		// if(this.wood < 0 || this.brick < 0 || this.ore < 0 || this.sheep < 0 ||
		// this.wheat < 0)
		// throw new InvalidTranslatorModelException(this.toString());
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("TRResourceList [wood=");
		builder.append(wood);
		builder.append(", brick=");
		builder.append(brick);
		builder.append(", ore=");
		builder.append(ore);
		builder.append(", sheep=");
		builder.append(sheep);
		builder.append(", wheat=");
		builder.append(wheat);
		builder.append("]");
		return builder.toString();
	}
}
