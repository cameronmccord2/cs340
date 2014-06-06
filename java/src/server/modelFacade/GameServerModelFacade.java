package server.modelFacade;

import javax.naming.OperationNotSupportedException;

import com.google.gson.Gson;

import server.commands.ICommandParams;
import server.model.translator.ModelTRConverter;
import server.models.ServerFacadeResponse;
import server.models.UserAttributes;
import server.models.exceptions.InvalidUserAttributesException;

public class GameServerModelFacade extends ServerModelFacade implements IGameServerModelFacade
{
	
	
	public GameServerModelFacade()
	{
		super();
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
	public String getJsonGameModelString(UserAttributes ua) {
		Gson gson = new Gson();
		try {
			String modelJson =  gson.toJson(ModelTRConverter.toTRObject(gameList.getGameById(ua.getGameId())));
			System.out.println("model: " + modelJson);
			return modelJson;
		} catch (InvalidUserAttributesException e) {
			e.printStackTrace();
		}
		return null;
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
