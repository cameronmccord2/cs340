package client.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import client.models.interfaces.IDevelopmentCard;

public class DevCardList implements Serializable 
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DevCardList [devcards=");
		builder.append(devcards);
		builder.append("]");
		return builder.toString();
	}

}
