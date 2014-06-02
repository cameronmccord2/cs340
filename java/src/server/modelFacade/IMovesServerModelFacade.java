package server.modelFacade;

import server.commands.ICommandParams;
import server.models.UserAttributes;

public interface IMovesServerModelFacade {

	/**
	 * Accept trade command from a user whether or not they will accept a given trade.
	 *
	 * @param params the params that get cast to an AccepTrade object
	 * @param userAttributes the user attributes
	 * @return the string indicating success or failure
	 */
	public String acceptTrade(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Buy dev card command
	 *
	 * @param params the params that get cast to a BuyDevCard object
	 * @param userAttributes the user attributes
	 * @return the string indicating success or failure
	 */
	public String buyDevCard(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Discard cards command
	 *
	 * @param params the params that get cast to a DiscardCards object
	 * @param userAttributes the user attributes
	 * @return the string indicating success or failure
	 */
	public String discardCards(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Finished turn command
	 *
	 * @param params the params that get cast to a FinishedTurn object
	 * @param userAttributes the user attributes
	 * @return the string indicating success or failure
	 */
	public String finishTurn(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Maritime trade off command
	 *
	 * @param params the params that get cast to a MaritimeTradeOff object
	 * @param userAttributes the user attributes
	 * @return the string indicating success or failure
	 */
	public String maritimeTradeOff(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Offer trade command
	 *
	 * @param params the params that get cast to a OfferTrade object
	 * @param userAttributes the user attributes
	 * @return the string indicating success or failure
	 */
	public String offerTrade(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Road building dev card command
	 *
	 * @param params the params that get cast to a RoadBuilding object
	 * @param userAttributes the user attributes
	 * @return the string indicating success or failure
	 */
	public String roadBuilding(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Build city command
	 *
	 * @param params the params that get cast to a ServerBuildCity object
	 * @param userAttributes the user attributes
	 * @return the string indicating success or failure
	 */
	public String buildCity(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Build road command
	 *
	 * @param params the params that get cast to a ServerBuildRoad object
	 * @param userAttributes the user attributes
	 * @return the string indicating success or failure
	 */
	public String buildRoad(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Build settlement command
	 *
	 * @param params the params that get cast to a ServerBuildSettlement object
	 * @param userAttributes the user attributes
	 * @return the string indicating success or failure
	 */
	public String buildSettlement(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Send chat command
	 *
	 * @param params the params that get cast to a ServerChat object
	 * @param userAttributes the user attributes
	 * @return the string indicating success or failure
	 */
	public String sendChat(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Monopoly dev card command
	 *
	 * @param params the params that get cast to a ServerMonopoly object
	 * @param userAttributes the user attributes
	 * @return the string indicating success or failure
	 */
	public String monopoly(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Monument dev card command
	 *
	 * @param params the params that get cast to a ServerMonument object
	 * @param userAttributes the user attributes
	 * @return the string indicating success or failure
	 */
	public String monument(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Rob player command
	 *
	 * @param params the params that get cast to a ServerRobPlayer object
	 * @param userAttributes the user attributes
	 * @return the string indicating success or failure
	 */
	public String robPlayer(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Roll command
	 *
	 * @param params the params that get cast to a ServerRoll object
	 * @param userAttributes the user attributes
	 * @return the string indicating success or failure
	 */
	public String roll(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Soldier dev card command
	 *
	 * @param params the params that get cast to a ServerSoldier object
	 * @param userAttributes the user attributes
	 * @return the string indicating success or failure
	 */
	public String soldier(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Year of plenty dev card command
	 *
	 * @param params the params that get cast to a ServerYearOfPlenty object
	 * @param userAttributes the user attributes
	 * @return the string indicating success or failure
	 */
	public String yearOfPlenty(ICommandParams params, UserAttributes userAttributes);
	
}
