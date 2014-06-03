package server.modelFacade;

import javax.naming.OperationNotSupportedException;

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
		String jsonModel = "";
		return jsonModel;
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
	public String getCommands(String json, UserAttributes ua)
	{
		String jsonModel = "";
		return jsonModel;
	}
	
	/**
	 * Run commands that are in the format that the getCommands endpoint returns.
	 * Allows replaying of events.
	 *
	 * @param	json
	 * 				The JSON array of commands to execute
	 * @param	ua
	 * 				The user attributes from the requesting user
	 * 
	 * @return	The string "Successful" or an error
	 */
	@Override
	public String runCommands(String json, UserAttributes ua)
	{
		return null;
	}
	
	/**
	 * Resets the game model to the create game state.
	 *
	 * @param	json
	 * 				The JSON indicating which game id
	 * @param	ua
	 * 				The user attributes from the requesting user
	 * 
	 * @return	The string "Successful" 
	 */
	@Override
	public String reset(String json, UserAttributes ua)
	{
		return null;
	}

	@Override
	public String listAI(String json, UserAttributes ua) throws OperationNotSupportedException
	{
		throw new OperationNotSupportedException();
	}

	@Override
	public String addAI(String json, UserAttributes ua) throws OperationNotSupportedException
	{
		throw new OperationNotSupportedException();
	}
}
