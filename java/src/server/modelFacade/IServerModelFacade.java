package server.modelFacade;

import server.commands.ICommandParams;
import server.models.UserAttributes;

/**
 * This is extended by each server facade.
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
	
	/**
	 * Accept trade command from a user whether or not they will accept a given trade.
	 *
	 * @param params the params that get cast to an AccepTrade object
	 * @param userAttributes the user attributes
	 * @return the string indicating success or failure
	 */
	String acceptTrade(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Buy dev card command
	 *
	 * @param params the params that get cast to a BuyDevCard object
	 * @param userAttributes the user attributes
	 * @return the string indicating success or failure
	 */
	String buyDevCard(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Create Game command
	 *
	 * @param params the params that get cast to a CreateGame object
	 * @param userAttributes the user attributes
	 * @return the string indicating success or failure
	 */
	String createGame(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Discard cards command
	 *
	 * @param params the params that get cast to a DiscardCards object
	 * @param userAttributes the user attributes
	 * @return the string indicating success or failure
	 */
	String discardCards(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Finished turn command
	 *
	 * @param params the params that get cast to a FinishedTurn object
	 * @param userAttributes the user attributes
	 * @return the string indicating success or failure
	 */
	String finishedTurn(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Game load command
	 *
	 * @param params the params that get cast to a GameLoad object
	 * @param userAttributes the user attributes
	 * @return the string indicating success or failure
	 */
	String gameLoad(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Maritime trade off command
	 *
	 * @param params the params that get cast to a MaritimeTradeOff object
	 * @param userAttributes the user attributes
	 * @return the string indicating success or failure
	 */
	String maritimeTradeOff(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Offer trade command
	 *
	 * @param params the params that get cast to a OfferTrade object
	 * @param userAttributes the user attributes
	 * @return the string indicating success or failure
	 */
	String offerTrade(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Road building dev card command
	 *
	 * @param params the params that get cast to a RoadBuilding object
	 * @param userAttributes the user attributes
	 * @return the string indicating success or failure
	 */
	String roadBuilding(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Build city command
	 *
	 * @param params the params that get cast to a ServerBuildCity object
	 * @param userAttributes the user attributes
	 * @return the string indicating success or failure
	 */
	String buildCity(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Build road command
	 *
	 * @param params the params that get cast to a ServerBuildRoad object
	 * @param userAttributes the user attributes
	 * @return the string indicating success or failure
	 */
	String buildRoad(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Build settlement command
	 *
	 * @param params the params that get cast to a ServerBuildSettlement object
	 * @param userAttributes the user attributes
	 * @return the string indicating success or failure
	 */
	String buildSettlement(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Send chat command
	 *
	 * @param params the params that get cast to a ServerChat object
	 * @param userAttributes the user attributes
	 * @return the string indicating success or failure
	 */
	String sendChat(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Join game command
	 *
	 * @param params the params that get cast to a ServerJoinGame object
	 * @param userAttributes the user attributes
	 * @return the string indicating success or failure
	 */
	String joinGame(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Monopoly dev card command
	 *
	 * @param params the params that get cast to a ServerMonopoly object
	 * @param userAttributes the user attributes
	 * @return the string indicating success or failure
	 */
	String monopoly(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Monument dev card command
	 *
	 * @param params the params that get cast to a ServerMonument object
	 * @param userAttributes the user attributes
	 * @return the string indicating success or failure
	 */
	String monument(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Rob player command
	 *
	 * @param params the params that get cast to a ServerRobPlayer object
	 * @param userAttributes the user attributes
	 * @return the string indicating success or failure
	 */
	String robPlayer(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Roll command
	 *
	 * @param params the params that get cast to a ServerRoll object
	 * @param userAttributes the user attributes
	 * @return the string indicating success or failure
	 */
	String roll(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Soldier dev card command
	 *
	 * @param params the params that get cast to a ServerSoldier object
	 * @param userAttributes the user attributes
	 * @return the string indicating success or failure
	 */
	String soldier(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Year of plenty dev card command
	 *
	 * @param params the params that get cast to a ServerYearOfPlenty object
	 * @param userAttributes the user attributes
	 * @return the string indicating success or failure
	 */
	String yearOfPlenty(ICommandParams params, UserAttributes userAttributes);
	
}
