package server.facades;

import server.commands.ICommandParams;
import server.models.UserAttributes;

public interface IMovesFacade extends IServerModelFacade {

	public String buyDevCard(ICommandParams params, UserAttributes ua);
	
	public String yearOfPlenty(ICommandParams params, UserAttributes ua);
	
	public String soldier(ICommandParams params, UserAttributes ua);
	
	public String monopoly(ICommandParams params, UserAttributes ua);
	
	public String monument(ICommandParams params, UserAttributes ua);
	
	public String offerTrade(ICommandParams params, UserAttributes ua);
	
	public String acceptTrade(ICommandParams params, UserAttributes ua);
	
	public String maritimeTrade(ICommandParams params, UserAttributes ua);
	
	public String discardCards(ICommandParams params, UserAttributes ua);
	
}
