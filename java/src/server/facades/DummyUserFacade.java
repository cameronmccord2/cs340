/**
 * 
 */
package server.facades;

import server.commands.CommandResponse;
import server.modelFacade.IServerModelFacade;
import server.models.UserAttributes;


/**
 * This is used for dependency injection for the UserFacade for testing.
 * @author scottdaly
 *
 */
public class DummyUserFacade implements IUserFacade{

	public DummyUserFacade() {
		
	}

	@Override
	public CommandResponse login(String json, UserAttributes ua) {
		return new CommandResponse("Success","200");
	}

	@Override
	public CommandResponse register(String json, UserAttributes ua) {
		return new CommandResponse("Success","200");
	}

	

}
