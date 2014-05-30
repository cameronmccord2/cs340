package server.facades;

import server.models.UserAttributes;

/**
 * The Interface IGameFacade.
 */
public interface IGameFacade extends IServerModelFacade {
	
	/**
	 * Gets the list of commands that have been execuded for the specified game.
	 *
	 * @param json the json indicating which game to get the commands for
	 * @param ua the user attributes from the requesting user
	 * @return the commands execuded in this game
	 */
	public String getCommands(String json, UserAttributes ua);
	
	/**
	 * Run commands that are in the format that the getCommands endpoint returns. Allows replaying of events.
	 *
	 * @param json a json array of commands to execute
	 * @param ua the user attributes from the requesting user
	 * @return the string "Successful" or an error
	 */
	public String runCommands(String json, UserAttributes ua);
	
	/**
	 * Resets the game model to the create game state.
	 *
	 * @param json the json indicating which game id
	 * @param ua the user attributes from the requesting user
	 * @return the string "Successful"
	 */
	public String reset(String json, UserAttributes ua);
	
	/**
	 * Gets the game model that needs to be sent to the client for gameplay.
	 *
	 * @param json the json representing the game model version
	 * @param ua the user attributes from the requesting user
	 * @return the game model as a json string
	 */
	public String getGameModel(String json, UserAttributes ua);
	
}
