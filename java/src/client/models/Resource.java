package client.models;

import java.io.Serializable;

import shared.definitions.ResourceType;

public class Resource implements Serializable 
{
	private ResourceType type;
	private int amount;

	public Resource(ResourceType type, int amount)
	{
		this.setResourceType(type);
		this.setAmount(amount);
	}

	public ResourceType getResourceType()
	{
		return type;
	}

	public void setResourceType(ResourceType type)
	{
		this.type = type;
	}

	public int getAmount()
	{
		return amount;
	}

	public void setAmount(int amount)
	{
		this.amount = amount;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resource other = (Resource) obj;
		if (amount != other.amount)
			return false;
		if (type != other.type)
			return false;
		return true;
	}
}
