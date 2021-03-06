package server.modelFacade;

import java.util.List;

import client.models.User;
import client.models.interfaces.IGame;
import server.commands.ICommandParams;
import server.models.ServerFacadeResponse;
import server.models.UserAttributes;

/**
 * This is extended by each server facade.
 *
 * @author scottdaly
 */
public interface IServerModelFacade {
	
	public ServerFacadeResponse getJsonGameModelString(ICommandParams params, UserAttributes ua);

	IGame getGameById(Integer id);

	public void setUsers(List<User> userss);
	
}
