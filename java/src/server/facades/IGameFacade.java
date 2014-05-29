package server.facades;

import server.commands.ICommandParams;
import server.models.UserAttributes;

public interface IGameFacade extends IServerModelFacade {

	public String loadGame(ICommandParams params, UserAttributes ua);
	
	public String saveGame(ICommandParams params, UserAttributes ua);
	
	public String getCommands(ICommandParams params, UserAttributes ua);
	
	public String runCommands(ICommandParams params, UserAttributes ua);
	
	public String reset(ICommandParams params, UserAttributes ua);
	
	public String getGameModel(ICommandParams params, UserAttributes ua);
	
}
