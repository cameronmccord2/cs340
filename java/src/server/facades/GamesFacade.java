package server.facades;

import server.commands.CommandResponse;
import server.modelFacade.IServerModelFacade;
import server.models.UserAttributes;

public class GamesFacade implements IGamesFacade{
	
	private IServerModelFacade modelFacade;

	public GamesFacade(IServerModelFacade modelFacade) {
		this.modelFacade = modelFacade;
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

}
