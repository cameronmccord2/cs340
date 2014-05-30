package server.facades;

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
	public String buyDevCard(String json, UserAttributes ua);
	
	/**
	 * Creates a yearOfPlenty command to change the models appropriately.
	 * @param json
	 * @param ua
	 * @return a string holding the currentgame model
	 */
	public String yearOfPlenty(String json, UserAttributes ua);
	
	/**
	 * Creates a roll soldier to change the models appropriately.
	 * @param json
	 * @param ua
	 * @return a string holding the currentgame model
	 */
	public String soldier(String json, UserAttributes ua);
	
	/**
	 * Creates a monopoly command to change the models appropriately.
	 * @param json
	 * @param ua
	 * @return a string holding the currentgame model
	 */
	public String monopoly(String json, UserAttributes ua);
	
	/**
	 * Creates a monument command to change the models appropriately.
	 * @param json
	 * @param ua
	 * @return a string holding the currentgame model
	 */
	public String monument(String json, UserAttributes ua);
	
	/**
	 * Creates a offerTrade command to change the models appropriately.
	 * @param json
	 * @param ua
	 * @return a string holding the currentgame model
	 */
	public String offerTrade(String json, UserAttributes ua);
	
	/**
	 * Creates a acceptTrade command to change the models appropriately.
	 * @param json
	 * @param ua
	 * @return a string holding the currentgame model
	 */
	public String acceptTrade(String json, UserAttributes ua);
	
	/**
	 * Creates a maritimeTrade command to change the models appropriately.
	 * @param json
	 * @param ua
	 * @return a string holding the currentgame model
	 */
	public String maritimeTrade(String json, UserAttributes ua);
	
	/**
	 * Creates a discardCards command to change the models appropriately.
	 * @param json
	 * @param ua
	 * @return a string holding the currentgame model
	 */
	public String discardCards(String json, UserAttributes ua);
	
	/**
	 * Creates a roll command to change the models appropriately.
	 * @param json
	 * @param ua
	 * @return a string holding the currentgame model
	 */
	public String rollNumber(String json, UserAttributes ua);
	
	/**
	 * Creates a sendChat command to change the models appropriately.
	 * @param json
	 * @param ua
	 * @return a string holding the currentgame model
	 */
	public String sendChat(String json, UserAttributes ua);
	
	/**
	 * Creates a finishTurn command to change the models appropriately.
	 * @param json
	 * @param ua
	 * @return a string holding the currentgame model
	 */
	public String finishTurn(String json, UserAttributes ua);
}
