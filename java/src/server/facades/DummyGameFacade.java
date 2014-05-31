/**
 * 
 */
package server.facades;

import server.commands.CommandResponse;
import server.modelFacade.IServerModelFacade;
import server.models.UserAttributes;

/**
 * This is used for dependency injection for the GameFacade for testing.
 * @author scottdaly
 *
 */
public class DummyGameFacade implements IGameFacade{

	public DummyGameFacade() {
		// TODO Auto-generated constructor stub
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
