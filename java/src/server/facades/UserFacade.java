package server.facades;

import com.google.gson.Gson;

import server.commands.CommandResponse;
import server.modelFacade.IServerModelFacade;
import server.models.Login;
import server.models.Register;
import server.models.UserAttributes;

public class UserFacade extends CommandCreationFacade implements IUserFacade{

	public UserFacade(IServerModelFacade modelFacade) {
		super(modelFacade);
	}

	@Override
	public CommandResponse login(String json, UserAttributes ua) {
		Gson gson = new Gson();
		Login params = gson.fromJson(json, Login.class);
		return this.genericCommandCreate(params, ua, false);
	}

	@Override
	public CommandResponse register(String json, UserAttributes ua) {
		Gson gson = new Gson();
		Register params = gson.fromJson(json, Register.class);
		return this.genericCommandCreate(params, ua, false);
	}

}