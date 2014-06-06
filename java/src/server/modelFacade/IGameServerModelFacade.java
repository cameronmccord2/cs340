package server.modelFacade;

import javax.naming.OperationNotSupportedException;

import server.commands.ICommandParams;
import server.models.ServerFacadeResponse;
import server.models.UserAttributes;

public interface IGameServerModelFacade {

	/**
	 * Gets the JSON game model ServerFacadeResponse for the requested game.
	 *
	 * @param	ua
	 * 				The user attributes indicating which game to get.
	 * 
	 * @return	The JSON game model ServerFacadeResponse of the requested model
	 */
	public String getJsonGameModelString(ICommandParams params, UserAttributes ua);

	
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
	public ServerFacadeResponse getCommands(ICommandParams params, UserAttributes ua);
	
	/**
	 * Run commands that are in the format that the getCommands end point returns. Allows replaying of events.
	 *
	 * @param	params
	 * 				a JSON array of commands to execute
	 * @param	ua
	 * 				the user attributes from the requesting user
	 * 
	 * @return	The ServerFacadeResponse "Success" or an error
	 */
	public ServerFacadeResponse runCommands(ICommandParams params, UserAttributes ua);
	
	/**
	 * Resets the game model to the create game state.
	 *
	 * @param	params
	 * 				The JSON indicating which game id
	 * @param	ua
	 * 				The user attributes from the requesting user
	 * 
	 * @return	The ServerFacadeResponse "Success"
	 */
	public ServerFacadeResponse reset(ICommandParams params, UserAttributes ua);
	
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
	public ServerFacadeResponse listAI(ICommandParams params, UserAttributes ua) throws OperationNotSupportedException;
	
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
	public ServerFacadeResponse addAI(ICommandParams params, UserAttributes ua) throws OperationNotSupportedException;

}
