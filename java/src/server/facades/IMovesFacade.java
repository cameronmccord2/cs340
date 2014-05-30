package server.facades;

import server.models.UserAttributes;

public interface IMovesFacade extends IServerModelFacade
{
	public String buyDevCard(String json, UserAttributes ua);
	
	public String yearOfPlenty(String json, UserAttributes ua);
	
	public String soldier(String json, UserAttributes ua);
	
	public String monopoly(String json, UserAttributes ua);
	
	public String monument(String json, UserAttributes ua);
	
	public String offerTrade(String json, UserAttributes ua);
	
	public String acceptTrade(String json, UserAttributes ua);
	
	public String maritimeTrade(String json, UserAttributes ua);
	
	public String discardCards(String json, UserAttributes ua);
}
