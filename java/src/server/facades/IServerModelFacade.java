package server.facades;

import client.server.AcceptTrade;
import server.commands.ICommandParams;
import server.models.UserAttributes;

/**
 * This is implemented by each server facade.
 *
 * @author scottdaly
 */
public interface IServerModelFacade {

	/**
	 * Gets the json game model string for the requested game.
	 *
	 * @param userAttributes the user attributes indicating which game to get.
	 * @return the json game model string of the requested model
	 */
	String getJsonGameModelString(UserAttributes userAttributes);
	
	String acceptTrade(ICommandParams params, UserAttributes userAttributes);
	
	String buyDevCard(ICommandParams params, UserAttributes userAttributes);
	
	String createGame(ICommandParams params, UserAttributes userAttributes);
	
	String discardCards(ICommandParams params, UserAttributes userAttributes);
	
	String finishedTurn(ICommandParams params, UserAttributes userAttributes);
	
	String gameLoad(ICommandParams params, UserAttributes userAttributes);
	
	String maritimeTradeOff(ICommandParams params, UserAttributes userAttributes);
	
	String offerTrade(ICommandParams params, UserAttributes userAttributes);
	
	String roadBuilding(ICommandParams params, UserAttributes userAttributes);
	
	String buildCity(ICommandParams params, UserAttributes userAttributes);
	
	String buildRoad(ICommandParams params, UserAttributes userAttributes);
	
	String buildSettlement(ICommandParams params, UserAttributes userAttributes);
	
	String sendChat(ICommandParams params, UserAttributes userAttributes);
	
	String joinGame(ICommandParams params, UserAttributes userAttributes);
	
	String monopoly(ICommandParams params, UserAttributes userAttributes);
	
	String monument(ICommandParams params, UserAttributes userAttributes);
	
	String robPlayer(ICommandParams params, UserAttributes userAttributes);
	
	String roll(ICommandParams params, UserAttributes userAttributes);
	
	String soldier(ICommandParams params, UserAttributes userAttributes);
	
	String yearOfPlenty(ICommandParams params, UserAttributes userAttributes);
	
}
