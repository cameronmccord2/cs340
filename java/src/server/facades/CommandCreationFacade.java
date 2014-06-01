package server.facades;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import client.server.CreateGame;
import client.server.GameLoad;
import client.server.SaveGame;
import client.server.ServerJoinGame;

import com.google.gson.Gson;

import server.commands.Command;
import server.commands.CommandResponse;
import server.commands.ICommand;
import server.commands.ICommandParams;
import server.commands.exceptions.CommandParamNotValidException;
import server.modelFacade.IServerModelFacade;
import server.models.GetCommands;
import server.models.GetGameModel;
import server.models.ListGames;
import server.models.Login;
import server.models.Register;
import server.models.ResetCommand;
import server.models.UserAttributes;

public class CommandCreationFacade implements IUserFacade, IGamesFacade, IGameFacade, IMovesFacade {

	protected IServerModelFacade facade;
	
	
	public CommandCreationFacade(IServerModelFacade modelFacade) {
		this.facade = modelFacade;
	}
	
	private CommandResponse genericCommandCreate(ICommandParams params, UserAttributes ua, boolean keepInHistory) {
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

	@Override
	public CommandResponse listGames(String json, UserAttributes ua) {
		ListGames params = new ListGames();
		return this.genericCommandCreate(params, ua, false);
	}

	@Override
	public CommandResponse createGame(String json, UserAttributes ua) {
		Gson gson = new Gson();
		CreateGame params = gson.fromJson(json, CreateGame.class);
		return this.genericCommandCreate(params, ua, false);
	}

	@Override
	public CommandResponse joinGame(String json, UserAttributes ua) {
		Gson gson = new Gson();
		ServerJoinGame params = gson.fromJson(json, ServerJoinGame.class);
		return this.genericCommandCreate(params, ua, false);
	}

	@Override
	public CommandResponse loadGame(String json, UserAttributes ua) {
		Gson gson = new Gson();
		GameLoad params = gson.fromJson(json, GameLoad.class);
		return this.genericCommandCreate(params, ua, false);
	}

	@Override
	public CommandResponse saveGame(String json, UserAttributes ua) {
		Gson gson = new Gson();
		SaveGame params = gson.fromJson(json, SaveGame.class);
		return this.genericCommandCreate(params, ua, false);
	}

	@Override
	public CommandResponse login(String json, UserAttributes ua) {
		Gson gson = new Gson();
		Login params = gson.fromJson(json, Login.class);
		return this.genericCommandCreate(params, ua, false);
	}

	@Override
	public CommandResponse register(String json, UserAttributes ua) {
		Gson gson = new Gson();
		Register params = gson.fromJson(json, Register.class);
		return this.genericCommandCreate(params, ua, false);
	}

	@Override
	public CommandResponse buyDevCard(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResponse yearOfPlenty(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResponse soldier(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResponse monopoly(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResponse monument(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResponse offerTrade(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResponse acceptTrade(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResponse maritimeTrade(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResponse discardCards(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResponse rollNumber(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResponse sendChat(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResponse finishTurn(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResponse robPlayer(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResponse roadBuilding(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResponse buildRoad(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResponse buildSettlement(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommandResponse buildCity(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}
}
