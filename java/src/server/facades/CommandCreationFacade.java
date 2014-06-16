package server.facades;

import java.lang.reflect.InvocationTargetException;

import persistence.IPlugin;
import server.commands.Command;
import server.commands.CommandResponse;
import server.commands.ICommand;
import server.commands.ICommandParams;
import server.commands.exceptions.CommandParamNotValidException;
import server.modelFacade.IServerModelFacade;
import server.models.UserAttributes;
import client.models.interfaces.IGame;

public class CommandCreationFacade {

	protected IServerModelFacade facade;
	
	
	public CommandCreationFacade(IServerModelFacade modelFacade) {
		this.facade = modelFacade;
	}
	
	protected CommandResponse genericCommandCreate(ICommandParams params, UserAttributes ua, boolean keepInHistory){
		return this.genericCommandCreate(params, ua, keepInHistory, null);
	}
	
	protected CommandResponse genericCommandCreate(ICommandParams params, UserAttributes ua, boolean keepInHistory, IPlugin plugin) {
		try {
			params.validate();
			ICommand c = new Command(params.getType(), params, ua, this.facade, false, plugin);
			String response = c.execute();
			IGame game = this.facade.getGameById(ua.getGameId());
			if(game == null)
				throw new RuntimeException("Couldn't get the game by id: " + ua.getGameId());
			plugin.addCommandToGame(params, game);
			return new CommandResponse(response, "200");
			
		} catch (CommandParamNotValidException e) {
			e.printStackTrace();
			return new CommandResponse("Request was syntatically incorrect, error: " + e.getLocalizedMessage(), "400");
			
		} catch(NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
			return new CommandResponse("This error is our problem boys, the reflection failed somehow: " + e.getLocalizedMessage(), "500");
		}
	}
}
