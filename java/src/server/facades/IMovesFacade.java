package server.facades;

import server.commands.CommandResponse;
import server.models.UserAttributes;

/**
 * This facades creates a Command object and executes it. This handles all requests from the /moves endpoints
 * @author scottdaly
 *
 */
public interface IMovesFacade extends IServerModelFacade
{
	/**
	 * Creates a buyDevCard command to change the models appropriately.
	 * @param json
	 * @param ua
	 * @return a string holding the currentgame model
	 */
	public CommandResponse buyDevCard(String json, UserAttributes ua);
	
	/**
	 * Creates a yearOfPlenty command to change the models appropriately.
	 * @param json
	 * @param ua
	 * @return a string holding the currentgame model
	 */
	public CommandResponse yearOfPlenty(String json, UserAttributes ua);
	
	/**
	 * Creates a roll soldier to change the models appropriately.
	 * @param json
	 * @param ua
	 * @return a string holding the currentgame model
	 */
	public CommandResponse soldier(String json, UserAttributes ua);
	
	/**
	 * Creates a monopoly command to change the models appropriately.
	 * @param json
	 * @param ua
	 * @return a string holding the currentgame model
	 */
	public CommandResponse monopoly(String json, UserAttributes ua);
	
	/**
	 * Creates a monument command to change the models appropriately.
	 * @param json
	 * @param ua
	 * @return a string holding the currentgame model
	 */
	public CommandResponse monument(String json, UserAttributes ua);
	
	/**
	 * Creates a offerTrade command to change the models appropriately.
	 * @param json
	 * @param ua
	 * @return a string holding the currentgame model
	 */
	public CommandResponse offerTrade(String json, UserAttributes ua);
	
	/**
	 * Creates a acceptTrade command to change the models appropriately.
	 * @param json
	 * @param ua
	 * @return a string holding the currentgame model
	 */
	public CommandResponse acceptTrade(String json, UserAttributes ua);
	
	/**
	 * Creates a maritimeTrade command to change the models appropriately.
	 * @param json
	 * @param ua
	 * @return a string holding the currentgame model
	 */
	public CommandResponse maritimeTrade(String json, UserAttributes ua);
	
	/**
	 * Creates a discardCards command to change the models appropriately.
	 * @param json
	 * @param ua
	 * @return a string holding the currentgame model
	 */
	public CommandResponse discardCards(String json, UserAttributes ua);
	
	/**
	 * Creates a roll command to change the models appropriately.
	 * @param json
	 * @param ua
	 * @return a string holding the currentgame model
	 */
	public CommandResponse rollNumber(String json, UserAttributes ua);
	
	/**
	 * Creates a sendChat command to change the models appropriately.
	 * @param json
	 * @param ua
	 * @return a string holding the currentgame model
	 */
	public CommandResponse sendChat(String json, UserAttributes ua);
	
	/**
	 * Creates a finishTurn command to change the models appropriately.
	 * @param json
	 * @param ua
	 * @return a string holding the currentgame model
	 */
	public CommandResponse finishTurn(String json, UserAttributes ua);
}
