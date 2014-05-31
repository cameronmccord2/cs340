package server.facades;

import server.commands.CommandResponse;
import server.modelFacade.IServerModelFacade;
import server.models.UserAttributes;

public class MovesFacade implements IMovesFacade{
	
	private IServerModelFacade modelFacade;

	public MovesFacade(IServerModelFacade modelFacade) {
		this.modelFacade = modelFacade;
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
