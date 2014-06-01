package server.facades;

import java.lang.reflect.InvocationTargetException;

import server.commands.Command;
import server.commands.CommandResponse;
import server.commands.ICommand;
import server.commands.exceptions.CommandParamNotValidException;
import server.modelFacade.IServerModelFacade;
import server.models.GetCommands;
import server.models.UserAttributes;

public class CommandCreationFacade implements IUserFacade, IGamesFacade, IGameFacade {

	protected IServerModelFacade facade;
	
	
	public CommandCreationFacade(IServerModelFacade modelFacade) {
		this.facade = modelFacade;
	}

	@Override
	public CommandResponse getCommands(String json, UserAttributes ua) {
		try {
			GetCommands gc = new GetCommands();
			gc.isValid();
			ICommand c = new Command("getCommands", gc, ua, this.facade, false);
			String response = c.execute();
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

	@Override
	public CommandResponse runCommands(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResponse reset(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResponse getGameModel(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResponse listGames(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResponse createGame(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResponse joinGame(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResponse loadGame(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResponse saveGame(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResponse login(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResponse register(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
