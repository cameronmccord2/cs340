package server.modelFacade;

import server.commands.ICommandParams;
import server.models.GameList;
import server.models.Login;
import server.models.Register;
import server.models.ServerFacadeResponse;
import server.models.UserAttributes;
import client.models.UserManager;

public class UserServerModelFacade extends ServerModelFacade implements IUserServerModelFacade {
	
	public UserServerModelFacade() {
		super();
	}
	
	@Override
	public ServerFacadeResponse login(ICommandParams params, UserAttributes ua) {
		Login loggingInUser = (Login)params;
		return new ServerFacadeResponse(false, userManager.login(loggingInUser));
	}

	@Override
	public ServerFacadeResponse register(ICommandParams params, UserAttributes ua) {
		Register newUser = (Register)params;
		return new ServerFacadeResponse(false,userManager.register(newUser));
	}

	public GameList getGameList() {
		return gameList;
	}

	public void setGameList(GameList gameList) {
		this.gameList = gameList;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	
}
