package server.modelFacade;

import javax.naming.OperationNotSupportedException;

import server.commands.ICommandParams;
import server.models.UserAttributes;

public class GameServerModelFacade implements IGameServerModelFacade
{
	
	
	public GameServerModelFacade()
	{
		
	}
	
	/**
	 * Gets the JSON game model string for the requested game.
	 *
	 * @param	ua
	 * 				the user attributes indicating which game to get.
	 * 
	 * @return	The JSON game model string of the requested model
	 */
	@Override
	public String getJsonGameModelString(UserAttributes ua)
	{
		String jsonResult = "";
		return jsonResult;
	}
	
	/**
	 * Gets the list of commands that have been executed for the specified game.
	 *
	 * @param	json
	 * 				The JSON indicating which game to get the commands for
	 * @param	ua
	 * 				The user attributes from the requesting user
	 * 
	 * @return	The commands executed in this game as a properly formatted
	 * 			JSON string.
	 */
	@Override
	public String getCommands(ICommandParams params, UserAttributes ua)
	{
		String jsonResult = "";
		return jsonResult;
	}
	
	/**
	 * Run commands that are in the format that the getCommands end point returns.
	 * Allows replaying of events.
	 *
	 * @param	json
	 * 				The JSON array of commands to execute
	 * @param	ua
	 * 				The user attributes from the requesting user
	 * 
	 * @return	The string "Success" or an error
	 */
	@Override
	public String runCommands(ICommandParams params, UserAttributes ua)
	{
		String jsonResult = "Success";
		return jsonResult;
	}
	
	/**
	 * Resets the game model to the create game state.
	 *
	 * @param	json
	 * 				The JSON indicating which game id
	 * @param	ua
	 * 				The user attributes from the requesting user
	 * 
	 * @return	The string "Success" 
	 */
	@Override
	public String reset(ICommandParams params, UserAttributes ua)
	{
		String jsonResult = "Success";
		return jsonResult;
	}

	@Override
	public String listAI(ICommandParams params, UserAttributes ua) throws OperationNotSupportedException
	{
		throw new OperationNotSupportedException();
	}

	@Override
	public String addAI(ICommandParams params, UserAttributes ua) throws OperationNotSupportedException
	{
		throw new OperationNotSupportedException();
	}
}
