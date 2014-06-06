package server.modelFacade;

import server.commands.ICommandParams;
import server.models.UserAttributes;

/**
 * This is extended by each server facade.
 *
 * @author scottdaly
 */
public interface IServerModelFacade {
	
	public String getJsonGameModelString(ICommandParams params, UserAttributes ua);
	
}
