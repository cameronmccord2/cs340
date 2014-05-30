package server.facades;

import server.commands.CommandResponse;
import server.models.UserAttributes;

/**
 * The Interface IGamesFacade.
 */
public interface IGamesFacade extends IServerModelFacade {

	/**
	 * Lists the games that are currently in the server
	 *
	 * @param json not really anything for this endpoint
	 * @param ua the user attributes from the requesting user
	 * @return the json string of the games in the server
	 */
	public CommandResponse listGames(String json, UserAttributes ua);
	
	/**
	 * Creates the game based on the flags in the json
	 *
	 * @param json the json that indicates how to set up the game
	 * @param ua the user attributes from the requesting user
	 * @return the json string of the game model
	 */
	public CommandResponse createGame(String json, UserAttributes ua);
	
	/**
	 * Join game that already exists
	 *
	 * @param json the json indicating which game to join
	 * @param ua the user attributes from the requesting user
	 * @return the json string of the game model
	 */
	public CommandResponse joinGame(String json, UserAttributes ua);
	
	/**
	 * Load game from a file indicated in the json
	 *
	 * @param json the json indicating which game to load from file
	 * @param ua the user attributes from the requesting user
	 * @return the string saying "Success"
	 */
	public CommandResponse loadGame(String json, UserAttributes ua);
	
	/**
	 * Saves the game to the specified filename
	 *
	 * @param json the json indicating what the filename should be
	 * @param ua the user attributes from the requesting user
	 * @return the string "Success"
	 */
	public CommandResponse saveGame(String json, UserAttributes ua);
	
}
