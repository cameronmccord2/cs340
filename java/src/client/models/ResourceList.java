package client.models;

import java.util.HashMap;
import java.util.Map;

public class ResourceList
{
	private Map<IResourceCard, Integer> resources;

	public ResourceList()
	{
		resources = new HashMap<>();
	}

	public ResourceList(Map<IResourceCard, Integer> resources)
	{
		this.resources = resources;
	}

	public Map<IResourceCard, Integer> getResources()
	{
		return resources;
	}

	public void setResources(Map<IResourceCard, Integer> resources)
	{
		this.resources = resources;
	}
}
