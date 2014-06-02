package server.facades;

import com.google.gson.Gson;

import server.commands.CommandResponse;
import server.modelFacade.IServerModelFacade;
import server.models.UserAttributes;
import client.models.UserManager;
import client.server.User;

public class UserFacade implements IUserFacade{
	
	private IServerModelFacade modelFacade;
	private UserManager manager = new UserManager();
	private Gson gson = new Gson();


	public UserFacade(IServerModelFacade modelFacade) {
		this.modelFacade = modelFacade;	
	}

	@Override
	public CommandResponse login(String json, UserAttributes ua) {
		User simpleUser = gson.fromJson(json, User.class);
		if(manager.login(simpleUser).equals("Success")){
			return new CommandResponse("Success","200");
		}
		else
			return new CommandResponse("Failed","400");
		
	}

	@Override
	public CommandResponse register(String json, UserAttributes ua) {
		User simpleUser = gson.fromJson(json, User.class);
		if(manager.register(simpleUser).equals("Success")){
			return new CommandResponse("Success","200");
		}
		else
			return new CommandResponse("Failed","400");
	}

}
