package client.models;

import shared.definitions.ResourceType;

public class Resource
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
}
