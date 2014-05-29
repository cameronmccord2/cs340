package server.facades;

import server.commands.ICommandParams;
import server.models.UserAttributes;

public interface IUserFacade extends IServerModelFacade {

	public String login(ICommandParams params, UserAttributes ua);
	
	public String register(ICommandParams params, UserAttributes ua);
	
}
