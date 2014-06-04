package server.modelFacade;

import java.util.Collection;
import java.util.Map;

import javax.naming.OperationNotSupportedException;

import client.models.*;
import client.server.*;
import client.server.User;
import server.commands.ICommandParams;
import server.models.FinishTurn;
import server.models.GameList;
import server.models.Login;
import server.models.Register;
import server.models.UserAttributes;
import server.models.exceptions.GameModelException;
import server.models.exceptions.InvalidUserAttributesException;
import client.models.IGame;
import client.models.IHex;
import client.models.IPiece;
import client.models.MessageLine;
import client.models.UserManager;
import client.models.translator.TRTradeOffer;
import client.server.OfferTrade;
import client.server.ServerChat;
import client.server.ServerRoll;
import client.models.translator.TREdgeLocation;
import client.models.translator.TRRoad;
import client.models.translator.TRTradeOffer;
import shared.locations.VertexLocation;

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
	public String getCommands(ICommandParams params, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String runCommands(ICommandParams params, UserAttributes ua) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String reset(ICommandParams params, UserAttributes ua) {
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
	public String buyDevCard(ICommandParams params, UserAttributes userAttributes) {
			BuyDevCard devCard = (BuyDevCard) params;

		try {
			IGame game = this.gameList.getGameById(userAttributes.getGameId());
			IPlayer player = game.getPlayerForPlayerIndex(devCard.getPlayerIndex());

			DevelopmentCard draw = game.getBank().drawRandomDevCard();

			Map<IDevelopmentCard, Integer> developmentCards = player.getDevelopmentCards();
			developmentCards.put( draw, developmentCards.get(draw) + 1);
			player.setDevelopmentCards(developmentCards);

		} catch (InvalidUserAttributesException | GameModelException e) {
			return e.getLocalizedMessage();
		}

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
		ServerBuildCity cityData = (ServerBuildCity) params;

		try {
			IGame game = this.gameList.getGameById(userAttributes.getGameId());
			IPlayer player = game.getPlayerForPlayerIndex(cityData.getPlayerIndex());

			cityData.getVertexLocation();

			City city = new City(new VertexLocation(cityData.getVertexLocation()), player);

			if(game.getMap().canPlaceCity(city))
				game.getMap().placeCity(city);
			else
				throw new InvalidLocationException();

		} catch (InvalidUserAttributesException | InvalidLocationException | GameModelException e) {
			return e.getLocalizedMessage();
		}
		return "Success";
	}

	@Override
	public String buildRoad(ICommandParams params, UserAttributes userAttributes) {

		ServerBuildRoad roadData = (ServerBuildRoad) params;

		try {

			IGame game = this.gameList.getGameById(userAttributes.getGameId());
			IPlayer player = game.getPlayerForPlayerIndex(roadData.getPlayerIndex());

			TREdgeLocation loc = new TREdgeLocation();
			loc.setDirection(roadData.getRoadLocation().getDirection());
			loc.setX(roadData.getRoadLocation().getX());
			loc.setY(roadData.getRoadLocation().getY());

			TRRoad road = new TRRoad();
			road.setLocation(loc);
			road.setOwner(player.getPlayerInfo().getPlayerIndex());

			IRoadSegment segment = new RoadSegment(road, player);

			if(game.getMap().canPlaceRoad(segment))
				game.getMap().placeRoadSegment(segment);
			else
				throw new InvalidLocationException();

		} catch (InvalidUserAttributesException | InvalidLocationException | GameModelException e) {
			return e.getLocalizedMessage();
		}
		return "Success";
	}

	@Override
	public String buildSettlement(ICommandParams params,
			UserAttributes userAttributes) {

		ServerBuildSettlement settlementData = (ServerBuildSettlement) params;

		try {
			IGame game = this.gameList.getGameById(userAttributes.getGameId());
			IPlayer player = game.getPlayerForPlayerIndex(settlementData.getPlayerIndex());

			settlementData.getVertexLocation();

			Settlement settlement = new Settlement(new VertexLocation(settlementData.getVertexLocation()), player);

			if(game.getMap().canPlaceSettlement(settlement))
				game.getMap().placeSettlement(settlement);
			else
				throw new InvalidLocationException();

		} catch (InvalidUserAttributesException | InvalidLocationException | GameModelException e) {
			return e.getLocalizedMessage();
		}
		return "Success";
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

		ServerMonopoly monopoly = (ServerMonopoly)params;

		try {

			IGame game = this.gameList.getGameById(userAttributes.getGameId());
			IPlayer player = game.getPlayerForPlayerIndex(monopoly.getPlayerIndex());

			int lootCount = 0;

			ResourceCard resource = ResourceCard.valueOf(monopoly.getResource());

			for(IPlayer opponent : game.getPlayers()) {
				Map<IResourceCard, Integer> cards = opponent.getResourceCards();
				lootCount += cards.get(resource);

				// Clear chosen resource from opponent's resource card stack
				cards.put(resource, 0);
				opponent.setResourceCards(cards);
			}

			Map<IResourceCard, Integer> playerCards = player.getResourceCards();
			playerCards.put( resource, playerCards.get(resource) + lootCount );

		} catch (InvalidUserAttributesException | GameModelException e) {
			return e.getLocalizedMessage();
		}
		return "Success";
	}

	@Override
	public String monument(ICommandParams params, UserAttributes userAttributes) {

		ServerMonument monument = (ServerMonument)params;

		try {
			IGame game = this.gameList.getGameById(userAttributes.getGameId());
			IPlayer player = game.getPlayerForPlayerIndex(monument.getPlayerIndex());

			player.setVictoryPoints( player.getVictoryPoints() + 1 );

			Map<IDevelopmentCard,Integer> developmentCards = player.getDevelopmentCards();

			if(developmentCards.get(DevelopmentCard.MONUMENT) <= 0)
				throw new GameModelException();
			else
				developmentCards.put( DevelopmentCard.MONUMENT, developmentCards.get(DevelopmentCard.MONUMENT) - 1);

		} catch (InvalidUserAttributesException | GameModelException e) {
			return e.getLocalizedMessage();
		}

		return "Success";
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
			
			int countRequired = cities.size();
			if(game.getBank().hasEnoughResources(hex.getHexType(), countRequired)){
				game.getBank().decrementResourceByCount(hex.getHexType(), countRequired);
				for (IPiece c : cities) {
					c.getPlayer().decrementResourceByCount(hex.getHexType(), 1);
				}
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

		ServerYearofPlenty yop = (ServerYearofPlenty)params;

		try {
			IGame game = this.gameList.getGameById(userAttributes.getGameId());
			IPlayer player = game.getPlayerForPlayerIndex(yop.getPlayerIndex());

			IBank bank = game.getBank();

			ResourceCard resource1 = ResourceCard.valueOf(yop.getResource1());
			ResourceCard resource2 = ResourceCard.valueOf(yop.getResource2());

			// Remove the two resources from the Bank and pass them to player, if possible
			Map<IResourceCard, Integer> resourceCards = bank.getResourceCards();
			if(resourceCards.get(resource1) > 0 && resourceCards.get(resource2) > 0) {

				resourceCards.put( resource1, resourceCards.get(resource1) - 1 );
				resourceCards.put( resource2, resourceCards.get(resource2) - 1 );

				bank.setResourceCards(resourceCards);

				Map<IResourceCard, Integer> playerCards = player.getResourceCards();
				playerCards.put( resource1, playerCards.get(resource1) + 1 );
				playerCards.put( resource2, playerCards.get(resource2) + 1 );

				player.setResourceCards(playerCards);
			}
			else
				throw new GameModelException();


		} catch (InvalidUserAttributesException | GameModelException e) {
			return e.getLocalizedMessage();
		}

		return "Success";
	}

	@Override
	public String login(ICommandParams params, UserAttributes ua) {
		Login loggingInUser = (Login)params;
		return userManager.login(loggingInUser);
	}

	@Override
	public String register(ICommandParams params, UserAttributes ua) {
		Register newUser = (Register)params;
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

	@Override
	public String listAI(ICommandParams params, UserAttributes ua) throws OperationNotSupportedException
	{
		throw new OperationNotSupportedException();
	}

	@Override
	public String addAI(ICommandParams params, UserAttributes ua) throws OperationNotSupportedException
	{
		throw new OperationNotSupportedException();
	}

	
}
