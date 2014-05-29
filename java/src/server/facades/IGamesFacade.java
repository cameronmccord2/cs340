package server.facades;

import server.commands.ICommandParams;
import server.models.UserAttributes;

public interface IGamesFacade extends IServerModelFacade {

	public String listGames(ICommandParams params, UserAttributes ua);
	
	public String createGame(ICommandParams params, UserAttributes ua);
	
	public String joinGame(ICommandParams params, UserAttributes ua);
	
}
