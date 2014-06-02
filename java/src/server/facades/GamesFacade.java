package server.facades;

import client.server.CreateGame;
import client.server.GameLoad;
import client.server.SaveGame;
import client.server.ServerJoinGame;

import com.google.gson.Gson;

import server.commands.CommandResponse;
import server.modelFacade.IServerModelFacade;
import server.models.ListGames;
import server.models.UserAttributes;

public class GamesFacade extends CommandCreationFacade implements IGamesFacade{
	
	public GamesFacade(IServerModelFacade modelFacade) {
		super(modelFacade);
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

}
