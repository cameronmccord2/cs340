package server.facades;

import server.models.UserAttributes;

public interface IGameFacade extends IServerModelFacade {
	
	public String getCommands(String json, UserAttributes ua);
	
	public String runCommands(String json, UserAttributes ua);
	
	public String reset(String json, UserAttributes ua);
	
	public String getGameModel(String json, UserAttributes ua);
	
}
