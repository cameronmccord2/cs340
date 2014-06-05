package client.models.translator;

public class TRBank
{
	TRDevCardList devcards;
	TRResourceList resources;

	public TRDevCardList getDevCards()
	{
		return devcards;
	}
	public void setDevCards(TRDevCardList devcards)
	{
		this.devcards = devcards;
	}
	public TRResourceList getResources()
	{
		return resources;
	}
	public void setResources(TRResourceList resources)
	{
		this.resources = resources;
	}
}
