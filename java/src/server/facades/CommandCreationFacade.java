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
			System.out.println("gn1");
			params.validate();
			ICommand c = new Command(params.getType(), params, ua, this.facade, false, plugin);
			String response = c.execute();
//			System.out.println("gn2");
//			if(ua.getGameId() != null){
//				System.out.println("not null gn2");
//				IGame game = this.facade.getGameById(ua.getGameId());
//				System.out.println("gn3");
//				if(game == null){
//					System.out.println("throw");
//					throw new RuntimeException("Couldn't get the game by id: " + ua.getGameId());
//				}
//				System.out.println("gn4");
//				plugin.addCommandToGame(params, game);
//				System.out.println("added command");
//			}else
//			System.out.println("null gn2");
			System.out.println("after execute: " + response);
			return new CommandResponse(response, "200");
			
		} catch (CommandParamNotValidException e) {
			e.printStackTrace();
			System.out.println("bad1");
			return new CommandResponse("Request was syntatically incorrect, error: " + e.getLocalizedMessage(), "400");
			
		} catch(NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
			System.out.println("bad2");
			return new CommandResponse("This error is our problem boys, the reflection failed somehow: " + e.getLocalizedMessage(), "500");
		}
	}
}
