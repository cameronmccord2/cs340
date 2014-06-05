package client.models;

import java.util.HashMap;
import java.util.Map;

public class DevCardList
{
	private Map<IDevelopmentCard, Integer> devcards;

	public DevCardList()
	{
		devcards = new HashMap<>();
	}

	public DevCardList(Map<IDevelopmentCard, Integer> devcards)
	{
		this.devcards = devcards;
	}

	public Map<IDevelopmentCard, Integer> getDevcards()
	{
		return devcards;
	}

	public void setDevcards(Map<IDevelopmentCard, Integer> devcards)
	{
		this.devcards = devcards;
	}

}
