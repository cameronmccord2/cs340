package server.facades;

import persistence.IPlugin;
import server.commands.CommandResponse;
import server.modelFacade.IServerModelFacade;
import server.models.Login;
import server.models.Register;
import server.models.UserAttributes;

import com.google.gson.Gson;

public class UserFacade extends CommandCreationFacade implements IUserFacade{

	private IPlugin plugin;

	public UserFacade(IServerModelFacade modelFacade, IPlugin plugin) {
		super(modelFacade);
		this.plugin = plugin;
	}

	@Override
	public CommandResponse login(String json, UserAttributes ua) {
		Gson gson = new Gson();
		Login params = gson.fromJson(json, Login.class);
		return this.genericCommandCreate(params, ua, false, plugin);
	}

	@Override
	public CommandResponse register(String json, UserAttributes ua) {
		Gson gson = new Gson();
		Register params = gson.fromJson(json, Register.class);
		return this.genericCommandCreate(params, ua, false, plugin);
	}

}