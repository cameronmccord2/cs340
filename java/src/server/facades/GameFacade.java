/**
 * 
 */
package server.facades;

import server.commands.CommandResponse;
import server.modelFacade.IServerModelFacade;
import server.models.UserAttributes;

/**
 * @author scottdaly
 *
 */
public class GameFacade implements IGameFacade{

	private IServerModelFacade modelFacade;
		
	public GameFacade(IServerModelFacade modelFacade) {
		this.modelFacade = modelFacade;
	}

	@Override
	public CommandResponse getCommands(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResponse runCommands(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResponse reset(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResponse getGameModel(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

}
