package server.modelFacade;

import java.util.Collection;
import java.util.List;

import client.models.IGame;
import client.models.IHex;
import client.models.IPiece;
import client.models.MessageLine;
import client.models.UserManager;
import client.models.translator.TRTradeOffer;
import client.server.OfferTrade;
import client.server.ServerChat;
import client.server.ServerRoll;
import client.server.User;
import server.commands.ICommandParams;
import server.models.FinishTurn;
import server.models.GameList;
import server.models.UserAttributes;
import server.models.UserList;
import server.models.exceptions.GameModelException;
import server.models.exceptions.InvalidUserAttributesException;

public class ServerModelFacade implements IServerModelFacade {

	private UserManager userManager;
	private GameList gameList;
	
	public ServerModelFacade(){
		super();
		this.userManager = new UserManager();
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
	public String offerTrade(ICommandParams params, UserAttributes userAttributes) {
		OfferTrade ot = (OfferTrade)params;
		try {
			IGame game = this.gameList.getGameById(userAttributes.getGameId());
			if(game.getCurrentTrade() != null)
				return "There is already a trade in progress, this mussn't be allowed to happen: " + game.getCurrentTrade().toString();
			
			game.setCurrentTrade(new TRTradeOffer(ot));
			
		} catch (InvalidUserAttributesException e) {
			return e.getLocalizedMessage();
		}
		return "Success";
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
		ServerChat sc = (ServerChat)params;
		try {
			IGame game = this.gameList.getGameById(userAttributes.getGameId());
			game.getChat().addLine(new MessageLine(game.getPlayerForPlayerIndex(sc.getPlayerIndex()).getPlayerInfo().getName(), sc.getContent()));
			
		} catch (InvalidUserAttributesException | GameModelException e) {
			return e.getLocalizedMessage();
		}
		return "Success";
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
		try {
			IGame game = this.gameList.getGameById(userAttributes.getGameId());
			Collection<IHex> hexes = game.getMap().getHexes();
			IHex hex = null;
			for (IHex h : hexes) {
				if(h.getHexNumber() == sr.getNumber()){
					hex = h;
					break;
				}
			}
			if(hex == null)
				return "Cannot find hex by the number: " + sr.getNumber() + ", hexes: " + hexes.toString();
			
			Collection<IPiece> cities = game.getMap().getSettlementsAroundHex(hex.getLocation());
			for (IPiece c : cities) {
				c.getPlayer().rolledResource(hex.getHexType());
			}
			
		} catch (InvalidUserAttributesException e) {
			return e.getLocalizedMessage();
		}
		return "Success";
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
	public String login(ICommandParams params, UserAttributes ua) {
		User loggingInUser = (User)params;
		
		return null;
	}

	@Override
	public String register(ICommandParams params, UserAttributes ua) {
		User newUser = (User)params;
		return userManager.register(newUser);
	}

	public GameList getGameList() {
		return gameList;
	}

	public void setGameList(GameList gameList) {
		this.gameList = gameList;
	}
 
	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	
}
