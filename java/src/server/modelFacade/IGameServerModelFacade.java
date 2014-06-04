package server.modelFacade;

import javax.naming.OperationNotSupportedException;

import server.commands.ICommandParams;
import server.models.UserAttributes;

public interface IGameServerModelFacade {

	/**
	 * Gets the JSON game model string for the requested game.
	 *
	 * @param	ua
	 * 				The user attributes indicating which game to get.
	 * 
	 * @return	The JSON game model string of the requested model
	 */
	public String getJsonGameModelString(UserAttributes ua);
	
	/**
	 * Gets the list of commands that have been executed for the specified game.
	 *
	 * @param	params
	 * 				The json indicating which game to get the commands for
	 * @param	ua
	 * 				The user attributes from the requesting user
	 * 
	 * @return	The commands executed in this game
	 */
	public String getCommands(ICommandParams params, UserAttributes ua);
	
	/**
	 * Run commands that are in the format that the getCommands end point returns. Allows replaying of events.
	 *
	 * @param	params
	 * 				a JSON array of commands to execute
	 * @param	ua
	 * 				the user attributes from the requesting user
	 * 
	 * @return	The string "Success" or an error
	 */
	public String runCommands(ICommandParams params, UserAttributes ua);
	
	/**
	 * Resets the game model to the create game state.
	 *
	 * @param	params
	 * 				The JSON indicating which game id
	 * @param	ua
	 * 				The user attributes from the requesting user
	 * 
	 * @return	The string "Success"
	 */
	public String reset(ICommandParams params, UserAttributes ua);
	
	/**
	 * Lists all of the available 
	 * 
	 * @param	params
	 * @param	ua
	 * 
	 * @return	The 
	 * 
	 * @throws	OperationNotSupportedException
	 */
	public String listAI(ICommandParams params, UserAttributes ua) throws OperationNotSupportedException;
	
	/**
	 * This
	 * 
	 * @param	params
	 * @param	ua
	 * 
	 * @return
	 * 
	 * @throws	OperationNotSupportedException
	 */
	public String addAI(ICommandParams params, UserAttributes ua) throws OperationNotSupportedException;
}
