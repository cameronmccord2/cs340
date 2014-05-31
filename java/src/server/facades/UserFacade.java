package server.facades;

import server.commands.CommandResponse;
import server.modelFacade.IServerModelFacade;
import server.models.UserAttributes;

public class UserFacade implements IUserFacade{
	
	private IServerModelFacade modelFacade;


	public UserFacade(IServerModelFacade modelFacade) {
		this.modelFacade = modelFacade;
	}

	@Override
	public CommandResponse login(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResponse register(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

}
