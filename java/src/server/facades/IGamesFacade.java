package server.facades;

import server.models.UserAttributes;

public interface IGamesFacade extends IServerModelFacade {

	public String listGames(String json, UserAttributes ua);
	
	public String createGame(String json, UserAttributes ua);
	
	public String joinGame(String json, UserAttributes ua);
	
	public String loadGame(String json, UserAttributes ua);
	
	public String saveGame(String json, UserAttributes ua);
	
}
