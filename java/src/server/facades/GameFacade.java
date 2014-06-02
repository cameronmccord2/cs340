/**
 * 
 */
package server.facades;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import server.commands.Command;
import server.commands.CommandResponse;
import server.commands.ICommand;
import server.commands.ICommandParams;
import server.commands.exceptions.CommandParamNotValidException;
import server.modelFacade.IServerModelFacade;
import server.models.GetCommands;
import server.models.GetGameModel;
import server.models.ResetCommand;
import server.models.UserAttributes;

/**
 * @author scottdaly
 *
 */
public class GameFacade extends CommandCreationFacade implements IGameFacade{

	public GameFacade(IServerModelFacade modelFacade) {
		super(modelFacade);
	}

	@Override
	public CommandResponse getCommands(String json, UserAttributes ua) {
		try {
			GetCommands gc = new GetCommands();
			gc.isValid();
			ICommand c = new Command(gc.getType(), gc, ua, this.facade, false);
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
		try {
			String finalGameModel = "";
			List<ICommandParams> runCommands = new ArrayList<>();
			for (ICommandParams iCommandParams : runCommands) {
				iCommandParams.isValid();
				
				Method method = this.getClass().getMethod(iCommandParams.getType(), new Class[] {ICommandParams.class, UserAttributes.class});
				Object response = (String)method.invoke(iCommandParams, ua);
				CommandResponse cr = (CommandResponse)response;
				if(cr.getResponseCode().equals("200"))
					finalGameModel = cr.getResponse();
				else
					return new CommandResponse(cr.getResponse(), cr.getResponseCode());
				
			}
			
			
			return new CommandResponse(finalGameModel, "200");
			
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
	public CommandResponse reset(String json, UserAttributes ua) {
		ResetCommand params = new ResetCommand();
		return this.genericCommandCreate(params, ua, false);
		
	}

	@Override
	public CommandResponse getGameModel(String json, UserAttributes ua) {
		GetGameModel params = new GetGameModel(json);
		return this.genericCommandCreate(params, ua, false);
	}

}
