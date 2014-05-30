package server.facades;

import server.models.UserAttributes;

public interface IUserFacade extends IServerModelFacade {

	public String login(String json, UserAttributes ua);
	
	public String register(String json, UserAttributes ua);
	
}
