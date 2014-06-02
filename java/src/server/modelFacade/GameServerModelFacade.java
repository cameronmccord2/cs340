package server.modelFacade;

import server.models.UserAttributes;

public class GameServerModelFacade implements IGameServerModelFacade
{
	
	/**
	 * 
	 */
	public GameServerModelFacade()
	{
		
	}
	
	/**
	 * Gets the json game model string for the requested game.
	 *
	 * @param userAttributes the user attributes indicating which game to get.
	 * @return the json game model string of the requested model
	 */
	@Override
	public String getJsonGameModelString(UserAttributes userAttributes)
	{
		return null;
	}
	
	/**
	 * Gets the list of commands that have been executed for the specified game.
	 *
	 * @param json the json indicating which game to get the commands for
	 * @param ua the user attributes from the requesting user
	 * @return the commands execuded in this game
	 */
	@Override
	public String getCommands(String json, UserAttributes ua)
	{
		return null;
	}
	
	/**
	 * Run commands that are in the format that the getCommands endpoint returns.
	 * Allows replaying of events.
	 *
	 * @param json a json array of commands to execute
	 * @param ua the user attributes from the requesting user
	 * @return the string "Successful" or an error
	 */
	@Override
	public String runCommands(String json, UserAttributes ua)
	{
		return null;
	}
	
	/**
	 * Resets the game model to the create game state.
	 *
	 * @param json the json indicating which game id
	 * @param ua the user attributes from the requesting user
	 * @return the string "Successful"
	 */
	@Override
	public String reset(String json, UserAttributes ua)
	{
		return null;
	}
}
