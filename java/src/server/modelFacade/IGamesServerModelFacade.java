package server.modelFacade;

import server.commands.ICommandParams;
import server.models.ServerFacadeResponse;
import server.models.UserAttributes;

public interface IGamesServerModelFacade {

	/**
	 * Lists the games that are currently in the server
	 *
	 * @param json not really anything for this endpoint
	 * @param ua the user attributes from the requesting user
	 * @return the json string of the games in the server
	 */
	public ServerFacadeResponse listGames(ICommandParams params, UserAttributes ua);
	
	/**
	 * Creates the game based on the flags in the json
	 *
	 * @param json the json that indicates how to set up the game
	 * @param ua the user attributes from the requesting user
	 * @return the json string of the game model
	 */
	public ServerFacadeResponse createGame(ICommandParams params, UserAttributes ua);
	
	/**
	 * Join game that already exists
	 *
	 * @param json the json indicating which game to join
	 * @param ua the user attributes from the requesting user
	 * @return the json string of the game model
	 */
	public ServerFacadeResponse joinGame(ICommandParams params, UserAttributes ua);
	
	/**
	 * Load game from a file indicated in the json
	 *
	 * @param json the json indicating which game to load from file
	 * @param ua the user attributes from the requesting user
	 * @return the string saying "Success"
	 */
	public ServerFacadeResponse loadGame(ICommandParams params, UserAttributes ua);
	
	/**
	 * Saves the game to the specified filename
	 *
	 * @param json the json indicating what the filename should be
	 * @param ua the user attributes from the requesting user
	 * @return the string "Success"
	 */
	public ServerFacadeResponse saveGame(ICommandParams params, UserAttributes ua);
	
}
