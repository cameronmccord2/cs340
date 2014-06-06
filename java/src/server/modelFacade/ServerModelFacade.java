package server.modelFacade;

import server.models.GameList;
import client.models.UserManager;

public class ServerModelFacade implements IServerModelFacade {

	protected UserManager userManager;
	protected GameList gameList;

	public ServerModelFacade(){
		this.userManager = new UserManager();
		this.gameList = new GameList();
	}
}