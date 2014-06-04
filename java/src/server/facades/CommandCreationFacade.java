package server.facades;

import java.lang.reflect.InvocationTargetException;

import server.commands.Command;
import server.commands.CommandResponse;
import server.commands.ICommand;
import server.commands.ICommandParams;
import server.commands.exceptions.CommandParamNotValidException;
import server.modelFacade.IServerModelFacade;
import server.models.UserAttributes;

public class CommandCreationFacade {

	protected IServerModelFacade facade;
	
	
	public CommandCreationFacade(IServerModelFacade modelFacade) {
		this.facade = modelFacade;
	}
	
	protected CommandResponse genericCommandCreate(ICommandParams params, UserAttributes ua, boolean keepInHistory) {
		try {
			params.isValid();
			ICommand c = new Command(params.getType(), params, ua, this.facade, false);
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
}
