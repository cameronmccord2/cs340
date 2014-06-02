package server.modelFacade;

import javax.naming.OperationNotSupportedException;

import server.models.UserAttributes;

public interface IGameServerModelFacade {

	/**
	 * Gets the json game model string for the requested game.
	 *
	 * @param userAttributes the user attributes indicating which game to get.
	 * @return the json game model string of the requested model
	 */
	public String getJsonGameModelString(UserAttributes userAttributes);
	
	/**
	 * Gets the list of commands that have been executed for the specified game.
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
	
	public String listAI(String json, UserAttributes ua) throws OperationNotSupportedException;
	
	public String addAI(String json, UserAttributes ua) throws OperationNotSupportedException;
}
