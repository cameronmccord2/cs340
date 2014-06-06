package server.modelFacade;

import server.commands.ICommandParams;
import server.models.ServerFacadeResponse;
import server.models.UserAttributes;

public interface IMovesServerModelFacade {

	/**
	 * Accept trade command from a user whether or not they will accept a given trade.
	 *
	 * @param params the params that get cast to an AccepTrade object
	 * @param userAttributes the user attributes
	 * @return the ServerFacadeResponse indicating success or failure
	 */
	public ServerFacadeResponse acceptTrade(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Buy dev card command
	 *
	 * @param params the params that get cast to a BuyDevCard object
	 * @param userAttributes the user attributes
	 * @return the ServerFacadeResponse indicating success or failure
	 */
	public ServerFacadeResponse buyDevCard(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Discard cards command
	 *
	 * @param params the params that get cast to a DiscardCards object
	 * @param userAttributes the user attributes
	 * @return the ServerFacadeResponse indicating success or failure
	 */
	public ServerFacadeResponse discardCards(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Finished turn command
	 *
	 * @param params the params that get cast to a FinishedTurn object
	 * @param userAttributes the user attributes
	 * @return the ServerFacadeResponse indicating success or failure
	 */
	public ServerFacadeResponse finishTurn(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Maritime trade off command
	 *
	 * @param params the params that get cast to a MaritimeTradeOff object
	 * @param userAttributes the user attributes
	 * @return the ServerFacadeResponse indicating success or failure
	 */
	public ServerFacadeResponse maritimeTradeOff(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Offer trade command
	 *
	 * @param params the params that get cast to a OfferTrade object
	 * @param userAttributes the user attributes
	 * @return the ServerFacadeResponse indicating success or failure
	 */
	public ServerFacadeResponse offerTrade(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Road building dev card command
	 *
	 * @param params the params that get cast to a RoadBuilding object
	 * @param userAttributes the user attributes
	 * @return the ServerFacadeResponse indicating success or failure
	 */
	public ServerFacadeResponse roadBuilding(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Build city command
	 *
	 * @param params the params that get cast to a ServerBuildCity object
	 * @param userAttributes the user attributes
	 * @return the ServerFacadeResponse indicating success or failure
	 */
	public ServerFacadeResponse buildCity(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Build road command
	 *
	 * @param params the params that get cast to a ServerBuildRoad object
	 * @param userAttributes the user attributes
	 * @return the ServerFacadeResponse indicating success or failure
	 */
	public ServerFacadeResponse buildRoad(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Build settlement command
	 *
	 * @param params the params that get cast to a ServerBuildSettlement object
	 * @param userAttributes the user attributes
	 * @return the ServerFacadeResponse indicating success or failure
	 */
	public ServerFacadeResponse buildSettlement(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Send chat command
	 *
	 * @param params the params that get cast to a ServerChat object
	 * @param userAttributes the user attributes
	 * @return the ServerFacadeResponse indicating success or failure
	 */
	public ServerFacadeResponse sendChat(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Monopoly dev card command
	 *
	 * @param params the params that get cast to a ServerMonopoly object
	 * @param userAttributes the user attributes
	 * @return the ServerFacadeResponse indicating success or failure
	 */
	public ServerFacadeResponse monopoly(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Monument dev card command
	 *
	 * @param params the params that get cast to a ServerMonument object
	 * @param userAttributes the user attributes
	 * @return the ServerFacadeResponse indicating success or failure
	 */
	public ServerFacadeResponse monument(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Rob player command
	 *
	 * @param params the params that get cast to a ServerRobPlayer object
	 * @param userAttributes the user attributes
	 * @return the ServerFacadeResponse indicating success or failure
	 */
	public ServerFacadeResponse robPlayer(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Roll command
	 *
	 * @param params the params that get cast to a ServerRoll object
	 * @param userAttributes the user attributes
	 * @return the ServerFacadeResponse indicating success or failure
	 */
	public ServerFacadeResponse roll(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Soldier dev card command
	 *
	 * @param params the params that get cast to a ServerSoldier object
	 * @param userAttributes the user attributes
	 * @return the ServerFacadeResponse indicating success or failure
	 */
	public ServerFacadeResponse soldier(ICommandParams params, UserAttributes userAttributes);
	
	/**
	 * Year of plenty dev card command
	 *
	 * @param params the params that get cast to a ServerYearOfPlenty object
	 * @param userAttributes the user attributes
	 * @return the ServerFacadeResponse indicating success or failure
	 */
	public ServerFacadeResponse yearOfPlenty(ICommandParams params, UserAttributes userAttributes);
	
}
