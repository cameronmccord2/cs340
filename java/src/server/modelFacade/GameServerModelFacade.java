package server.modelFacade;

import javax.naming.OperationNotSupportedException;

import com.google.gson.Gson;

import server.commands.ICommandParams;
import server.model.translator.ModelTRConverter;
import server.models.GameList;
import server.models.ServerFacadeResponse;
import server.models.UserAttributes;
import server.models.exceptions.InvalidUserAttributesException;

public class GameServerModelFacade extends ServerModelFacade implements IGameServerModelFacade
{
	
	public GameServerModelFacade(GameList gameList) {
		super(gameList);
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
	public ServerFacadeResponse getCommands(ICommandParams params, UserAttributes ua)
	{
		// TODO Auto-generated method stub
		return null;
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
	public ServerFacadeResponse runCommands(ICommandParams params, UserAttributes ua)
	{
		// TODO Auto-generated method stub
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
	 * @return	The string "Success" 
	 */
	@Override
	public ServerFacadeResponse reset(ICommandParams params, UserAttributes ua)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerFacadeResponse listAI(ICommandParams params, UserAttributes ua) throws OperationNotSupportedException
	{
		throw new OperationNotSupportedException();
	}

	@Override
	public ServerFacadeResponse addAI(ICommandParams params, UserAttributes ua) throws OperationNotSupportedException
	{
		throw new OperationNotSupportedException();
	}
}
