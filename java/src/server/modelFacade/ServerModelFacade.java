package server.modelFacade;

import client.models.IGame;
import client.server.ServerRoll;
import server.commands.ICommandParams;
import server.models.FinishTurn;
import server.models.GameList;
import server.models.UserAttributes;
import server.models.UserList;
import server.models.exceptions.InvalidUserAttributesException;

public class ServerModelFacade implements IServerModelFacade {

	private UserList userList;
	private GameList gameList;
	
	public ServerModelFacade(){
		super();
		this.userList = new UserList();
		this.gameList = new GameList();
	}
	
	@Override
	public String getJsonGameModelString(UserAttributes userAttributes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCommands(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String runCommands(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String reset(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String listGames(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createGame(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String joinGame(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String loadGame(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveGame(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String acceptTrade(ICommandParams params,
			UserAttributes userAttributes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buyDevCard(ICommandParams params,
			UserAttributes userAttributes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String discardCards(ICommandParams params,
			UserAttributes userAttributes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String finishTurn(ICommandParams params, UserAttributes userAttributes) {
		FinishTurn ft = (FinishTurn)params;
		try {
			IGame game = this.gameList.getGameById(userAttributes.getGameId());
			if(ft.getPlayerIndex() == 3)
				game.getTurnTracker().setCurrentTurn(0);
			else
				game.getTurnTracker().setCurrentTurn(ft.getPlayerIndex() + 1);
		} catch (InvalidUserAttributesException e) {
			return e.getLocalizedMessage();
		}
		return "Success";
	}

	@Override
	public String maritimeTradeOff(ICommandParams params,
			UserAttributes userAttributes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String offerTrade(ICommandParams params,
			UserAttributes userAttributes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String roadBuilding(ICommandParams params,
			UserAttributes userAttributes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildCity(ICommandParams params, UserAttributes userAttributes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildRoad(ICommandParams params, UserAttributes userAttributes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildSettlement(ICommandParams params,
			UserAttributes userAttributes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sendChat(ICommandParams params, UserAttributes userAttributes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String monopoly(ICommandParams params, UserAttributes userAttributes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String monument(ICommandParams params, UserAttributes userAttributes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String robPlayer(ICommandParams params, UserAttributes userAttributes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String roll(ICommandParams params, UserAttributes userAttributes) {
		ServerRoll sr = (ServerRoll)params;
		
	}

	@Override
	public String soldier(ICommandParams params, UserAttributes userAttributes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String yearOfPlenty(ICommandParams params,
			UserAttributes userAttributes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String login(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String register(String json, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserList getUserList() {
		return userList;
	}

	public void setUserList(UserList userList) {
		this.userList = userList;
	}

	public GameList getGameList() {
		return gameList;
	}

	public void setGameList(GameList gameList) {
		this.gameList = gameList;
	}

}
