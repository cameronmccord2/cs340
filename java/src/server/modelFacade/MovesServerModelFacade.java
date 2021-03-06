package server.modelFacade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import server.commands.ICommandParams;
import server.models.FinishTurn;
import server.models.GameList;
import server.models.ServerFacadeResponse;
import server.models.UserAttributes;
import server.models.exceptions.GameModelException;
import server.models.exceptions.InvalidUserAttributesException;
import shared.definitions.HexType;
import shared.locations.DefaultLocation;
import shared.locations.HexLocation;
import shared.locations.ILocation;
import shared.locations.VertexLocation;
import client.models.City;
import client.models.DevelopmentCard;
import client.models.MessageLine;
import client.models.ResourceCard;
import client.models.RoadSegment;
import client.models.Settlement;
import client.models.interfaces.IBank;
import client.models.interfaces.ICatanMap;
import client.models.interfaces.IDevelopmentCard;
import client.models.interfaces.IGame;
import client.models.interfaces.IHex;
import client.models.interfaces.IPiece;
import client.models.interfaces.IPlayer;
import client.models.interfaces.IResourceCard;
import client.models.interfaces.IRoadSegment;
import client.models.interfaces.IRobber;
import client.models.interfaces.InvalidLocationException;
import client.models.translator.TREdgeLocation;
import client.models.translator.TRResourceList;
import client.models.translator.TRRoad;
import client.models.translator.TRTradeOffer;
import client.server.AcceptTrade;
import client.server.BuyDevCard;
import client.server.DiscardedCards;
import client.server.MaritimeTradeOff;
import client.server.OfferTrade;
import client.server.RoadBuilding;
import client.server.ServerBuildCity;
import client.server.ServerBuildRoad;
import client.server.ServerBuildSettlement;
import client.server.ServerChat;
import client.server.ServerMonopoly;
import client.server.ServerMonument;
import client.server.ServerRobPlayer;
import client.server.ServerRoll;
import client.server.ServerSoldier;
import client.server.ServerYearofPlenty;

public class MovesServerModelFacade extends ServerModelFacade implements IMovesServerModelFacade
{
	
	public MovesServerModelFacade(GameList gameList) {
		super(gameList);
	}

	@Override
	public ServerFacadeResponse buyDevCard(ICommandParams params, UserAttributes userAttributes) 
	{

		BuyDevCard devCard = (BuyDevCard) params;

		try {
			IGame game = this.gameList.getGameById(userAttributes.getGameId());
			IPlayer player = game.getPlayerForPlayerIndex(devCard.getPlayerIndex());

			DevelopmentCard draw = game.getBank().drawRandomDevCard();

			Map<IDevelopmentCard, Integer> developmentCards = player.getDevelopmentCards();
			developmentCards.put( draw, developmentCards.get(draw) + 1);
			player.setDevelopmentCards(developmentCards);
			player.deductResources(DevelopmentCard.getResourceCost());
			game.setModelVersion( game.getModelVersion() + 1);

		} catch (InvalidUserAttributesException | GameModelException e) {
			return new ServerFacadeResponse(false, e.getLocalizedMessage());
		}

		return new ServerFacadeResponse(true, null);
	}

	@Override
	public ServerFacadeResponse discardCards(ICommandParams params, UserAttributes userAttributes) {

		DiscardedCards discard = (DiscardedCards)params;
		int discardNum = 0;

		try {
			IGame game = this.gameList.getGameById(userAttributes.getGameId());
			IPlayer player = game.getPlayerForPlayerIndex(discard.getPlayerIndex());

			Map<IResourceCard, Integer> resourceCards = player.getResourceCards();

			discardNum = discard.getDiscardedCards().getWood();
			resourceCards.put(ResourceCard.WOOD, resourceCards.get(ResourceCard.WOOD) - discardNum);

			discardNum = discard.getDiscardedCards().getWheat();
			resourceCards.put(ResourceCard.WHEAT, resourceCards.get(ResourceCard.WHEAT) - discardNum);

			discardNum = discard.getDiscardedCards().getSheep();
			resourceCards.put(ResourceCard.SHEEP, resourceCards.get(ResourceCard.SHEEP) - discardNum);

			discardNum = discard.getDiscardedCards().getOre();
			resourceCards.put(ResourceCard.ORE, resourceCards.get(ResourceCard.ORE) - discardNum);

			discardNum = discard.getDiscardedCards().getBrick();
			resourceCards.put(ResourceCard.BRICK, resourceCards.get(ResourceCard.BRICK) - discardNum);

			player.setResourceCards(resourceCards);
			game.setModelVersion( game.getModelVersion() + 1);

		} catch (InvalidUserAttributesException | GameModelException e) {
			return new ServerFacadeResponse(false, e.getLocalizedMessage());
		}
		IGame game;
		try {
			game = this.gameList.getGameById(userAttributes.getGameId());
			game.getLog().addLine(new MessageLine(userAttributes.getusername(), userAttributes.getusername() + " just discarded."));
		} catch (InvalidUserAttributesException e) {
			e.printStackTrace();
		}
		
		return new ServerFacadeResponse(true, null);
	}

	@Override
	public ServerFacadeResponse finishTurn(ICommandParams params, UserAttributes userAttributes) {
		FinishTurn ft = (FinishTurn)params;
		try {
			IGame game = this.gameList.getGameById(userAttributes.getGameId());
			if(ft.getPlayerIndex() == 3)
				game.getTurnTracker().setCurrentTurn(0);
			else
				game.getTurnTracker().setCurrentTurn(ft.getPlayerIndex() + 1);
			game.getTurnTracker().setStatus("Rolling");
			game.setModelVersion(game.getModelVersion() + 1);
			game.getLog().addLine(new MessageLine(userAttributes.getusername(), userAttributes.getusername() + " finished their turn."));
		} catch (InvalidUserAttributesException e) {
			return new ServerFacadeResponse(false, e.getLocalizedMessage());
		}
		
		return new ServerFacadeResponse(true, null);
	}
	
	@Override
	public ServerFacadeResponse acceptTrade(ICommandParams params, UserAttributes userAttributes) {
		AcceptTrade at = (AcceptTrade)params;
		try {
			IGame game = this.gameList.getGameById(userAttributes.getGameId());
			if(at.isWillAccept()){
				TRResourceList offer = game.getCurrentTrade().getOffer();
				IPlayer sender = game.getPlayerForPlayerIndex(game.getCurrentTrade().getSender());
				IPlayer receiver = game.getPlayerForPlayerIndex(game.getCurrentTrade().getReceiver());
				if(offer.getBrick() < 0)
					sender.incrementResourceByCount(ResourceCard.BRICK, offer.getBrick());
				else
					receiver.incrementResourceByCount(ResourceCard.BRICK, offer.getBrick());
				if(offer.getOre() < 0)
					sender.incrementResourceByCount(ResourceCard.ORE, offer.getOre());
				else
					receiver.incrementResourceByCount(ResourceCard.ORE, offer.getOre());
				if(offer.getWood() < 0)
					sender.incrementResourceByCount(ResourceCard.WOOD, offer.getWood());
				else
					receiver.incrementResourceByCount(ResourceCard.WOOD, offer.getWood());
				if(offer.getSheep() < 0)
					sender.incrementResourceByCount(ResourceCard.SHEEP, offer.getSheep());
				else
					receiver.incrementResourceByCount(ResourceCard.SHEEP, offer.getSheep());
				if(offer.getWheat() < 0)
					sender.incrementResourceByCount(ResourceCard.WHEAT, offer.getWheat());
				else
					receiver.incrementResourceByCount(ResourceCard.WHEAT, offer.getWheat());
				
//				sender.put(ResourceCard.BRICK, sender.get(ResourceCard.BRICK) + offer.getBrick());
//				receiver.put(ResourceCard.BRICK, receiver.get(ResourceCard.BRICK) + offer.getBrick());
//				sender.put(ResourceCard.ORE, sender.get(ResourceCard.ORE) + offer.getBrick());
//				receiver.put(ResourceCard.ORE, receiver.get(ResourceCard.ORE) + offer.getBrick());
//				sender.put(ResourceCard.WOOD, sender.get(ResourceCard.WOOD) + offer.getBrick());
//				receiver.put(ResourceCard.WOOD, receiver.get(ResourceCard.WOOD) + offer.getBrick());
//				sender.put(ResourceCard.SHEEP, sender.get(ResourceCard.SHEEP) + offer.getBrick());
//				receiver.put(ResourceCard.SHEEP, receiver.get(ResourceCard.SHEEP) + offer.getBrick());
//				sender.put(ResourceCard.WHEAT, sender.get(ResourceCard.WHEAT) + offer.getBrick());
//				receiver.put(ResourceCard.WHEAT, receiver.get(ResourceCard.WHEAT) + offer.getBrick());
			}
			game.setCurrentTrade(null);
			game.setModelVersion( game.getModelVersion() + 1);
		} catch (InvalidUserAttributesException | GameModelException e) {
			return new ServerFacadeResponse(false, e.getLocalizedMessage());
		}

		return new ServerFacadeResponse(true, null);
	}

	@Override
	public ServerFacadeResponse maritimeTradeOff(ICommandParams params, UserAttributes userAttributes) {

		MaritimeTradeOff trade = (MaritimeTradeOff) params;
		try {
			IGame game = this.gameList.getGameById(userAttributes.getGameId());
			if(game.getBank().hasEnoughResources(trade.getOutputResource(), 1)){
				game.getBank().decrementResourceByCount(trade.getOutputResource(), 1);
				game.getBank().incrementResourceByCount(trade.getInputResource(), trade.getRatio());
				IPlayer player = game.getPlayerForPlayerIndex(trade.getPlayerIndex());
				player.decrementResourceByCount(trade.getInputResource(), trade.getRatio());
				player.incrementResourceByCount(trade.getOutputResource(), 1);
			}
			game.setModelVersion( game.getModelVersion() + 1);
			
		} catch (InvalidUserAttributesException | GameModelException e) {
			return new ServerFacadeResponse(false, e.getLocalizedMessage());
		}

		return new ServerFacadeResponse(true, null);
	}

	@Override
	public ServerFacadeResponse offerTrade(ICommandParams params, UserAttributes userAttributes) {
		OfferTrade ot = (OfferTrade)params;
		try {
			IGame game = this.gameList.getGameById(userAttributes.getGameId());
			if(game.getCurrentTrade() != null)
				return new ServerFacadeResponse(false, "There is already a trade in progress, this mussn't be allowed to happen: " + game.getCurrentTrade().toString());

			game.setCurrentTrade(new TRTradeOffer(ot));
			game.setModelVersion( game.getModelVersion() + 1);

		} catch (InvalidUserAttributesException e) {
			return new ServerFacadeResponse(false, e.getLocalizedMessage());
		}

		return new ServerFacadeResponse(true, null);
	}

	@Override
	public ServerFacadeResponse roadBuilding(ICommandParams params, UserAttributes userAttributes) {

		RoadBuilding roadData = (RoadBuilding) params;

		try {

			IGame game = this.gameList.getGameById(userAttributes.getGameId());
			IPlayer player = game.getPlayerForPlayerIndex(roadData.getPlayerIndex());
			ICatanMap map = game.getMap();

			// Setup Road 1
			TREdgeLocation loc1 = new TREdgeLocation();
			loc1.setDirection(roadData.getSpot1().getDirection());
			loc1.setX(roadData.getSpot1().getX());
			loc1.setY(roadData.getSpot1().getY());

			TRRoad road1 = new TRRoad();
			road1.setLocation(loc1);
			road1.setOwner(player.getPlayerInfo().getPlayerIndex());

			IRoadSegment segment1 = new RoadSegment(road1, player);


			// Setup Road 2
			TREdgeLocation loc2 = new TREdgeLocation();
			loc2.setDirection(roadData.getSpot2().getDirection());
			loc2.setX(roadData.getSpot2().getX());
			loc2.setY(roadData.getSpot2().getY());

			TRRoad road2 = new TRRoad();
			road2.setLocation(loc2);
			road2.setOwner(player.getPlayerInfo().getPlayerIndex());

			IRoadSegment segment2 = new RoadSegment(road2, player);

			if(map.canPlaceRoad(segment1) && map.canPlaceRoad(segment2)) {
				map.placeRoadSegment(segment1);
				map.placeRoadSegment(segment2);
				game.getLog().addLine(new MessageLine(userAttributes.getusername(), userAttributes.getusername() + " played a Road Building card."));
			}
			else {
				throw new InvalidLocationException();
			}
			game.setModelVersion( game.getModelVersion() + 1);

		} catch (InvalidUserAttributesException | InvalidLocationException | GameModelException e) {
			return new ServerFacadeResponse(false, e.getLocalizedMessage());
		}

		return new ServerFacadeResponse(true, null);
	}

	@Override
	public ServerFacadeResponse buildCity(ICommandParams params, UserAttributes userAttributes) {
		ServerBuildCity cityData = (ServerBuildCity) params;

		try {
			IGame game = this.gameList.getGameById(userAttributes.getGameId());
			IPlayer player = game.getPlayerForPlayerIndex(cityData.getPlayerIndex());

			cityData.getVertexLocation();

			City city = new City(new VertexLocation(cityData.getVertexLocation()), player);

			if(game.getMap().canPlaceCity(city)) {
				game.getMap().placeCity(city);
				player.deductResources(RoadSegment.getResourceCost());
				game.getLog().addLine(new MessageLine(userAttributes.getusername(), userAttributes.getusername() + " built a city."));
			}
			else {
				throw new InvalidLocationException();
			}
			game.setModelVersion( game.getModelVersion() + 1);
			
		} catch (InvalidUserAttributesException | InvalidLocationException | GameModelException e) {
			return new ServerFacadeResponse(false, e.getLocalizedMessage());
		}

		return new ServerFacadeResponse(true, null);
	}

	@Override
	public ServerFacadeResponse buildRoad(ICommandParams params, UserAttributes userAttributes) {

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


			if(game.getMap().canPlaceRoad(segment, true)) {

				game.getMap().placeRoadSegment(segment, true);
				player.deductResources(RoadSegment.getResourceCost());
				game.getLog().addLine(new MessageLine(userAttributes.getusername(), userAttributes.getusername() + " built a road."));

				// If we ever get first second round to work in the client:
//				if(game.getTurnTracker().getCurrentTurn() == 3 && game.getTurnTracker().getStatus().equals("FirstRound")){
//					System.out.println("changing to second");
//					game.getTurnTracker().setCurrentTurn(3);
//					game.getTurnTracker().setStatus("SecondRound");
//				}else if(player.getPlayerInfo().getPlayerIndex() == 0 && game.getTurnTracker().getStatus().equals("SecondRound")){
//					// done
//					System.out.println("done");
//					game.getTurnTracker().setStatus("Playing");
//					game.getTurnTracker().setCurrentTurn(0);
//				}else if(game.getTurnTracker().getStatus().equals("FirstRound")){
//					System.out.println("in first");
//					game.getTurnTracker().setCurrentTurn(player.getPlayerInfo().getPlayerIndex() + 1);
//				}else if(game.getTurnTracker().getStatus().equals("SecondRound")){
//					System.out.println("in second");
//					game.getTurnTracker().setCurrentTurn(player.getPlayerInfo().getPlayerIndex() - 1);
//				}else{
//					System.out.println("NOEN:LKJ:LJK:LKJ");
//				}
			}
			else {
				throw new InvalidLocationException();
			}
			game.setModelVersion( game.getModelVersion() + 1);
			
		} catch (InvalidUserAttributesException | InvalidLocationException | GameModelException e) {
			return new ServerFacadeResponse(false, e.getLocalizedMessage());
		}

		return new ServerFacadeResponse(true, null);
	}

	@Override
	public ServerFacadeResponse buildSettlement(ICommandParams params,
			UserAttributes userAttributes) {

		ServerBuildSettlement settlementData = (ServerBuildSettlement) params;

		try {
			IGame game = this.gameList.getGameById(userAttributes.getGameId());
			IPlayer player = game.getPlayerForPlayerIndex(settlementData.getPlayerIndex());

			settlementData.getVertexLocation();

			Settlement settlement = new Settlement(new VertexLocation(settlementData.getVertexLocation()), player);

			if(game.getMap().canPlaceSettlement(settlement, true)) {
				game.getMap().placeSettlement(settlement, true);
				player.deductResources(Settlement.getResourceCost());
				game.getLog().addLine(new MessageLine(userAttributes.getusername(), userAttributes.getusername() + " built a settlement."));
			}
			else {
				throw new InvalidLocationException();
			}
			game.setModelVersion( game.getModelVersion() + 1);

		} catch (InvalidUserAttributesException | InvalidLocationException | GameModelException e) {
			return new ServerFacadeResponse(false, e.getLocalizedMessage());
		}

		return new ServerFacadeResponse(true, null);
	}

	@Override
	public ServerFacadeResponse sendChat(ICommandParams params, UserAttributes userAttributes) {
		ServerChat sc = (ServerChat)params;
		try {
			IGame game = this.gameList.getGameById(userAttributes.getGameId());
			game.getChat().addLine(new MessageLine(game.getPlayerForPlayerIndex(sc.getPlayerIndex()).getPlayerInfo().getName(), sc.getContent()));

			game.setModelVersion( game.getModelVersion() + 1);
		} catch (InvalidUserAttributesException | GameModelException e) {
			return new ServerFacadeResponse(false, e.getLocalizedMessage());
		}

		return new ServerFacadeResponse(true, null);
	}

	@Override
	public ServerFacadeResponse monopoly(ICommandParams params, UserAttributes userAttributes) {

		ServerMonopoly monopoly = (ServerMonopoly)params;

		try {

			IGame game = this.gameList.getGameById(userAttributes.getGameId());
			IPlayer player = game.getPlayerForPlayerIndex(monopoly.getPlayerIndex());

			int lootCount = 0;

			ResourceCard resource = ResourceCard.valueOf(monopoly.getResource());

			for(IPlayer opponent : game.getPlayers()) {

				// Count number of matched resource
				Map<IResourceCard, Integer> cards = opponent.getResourceCards();
				lootCount += cards.get(resource);

				// Clear chosen resource from opponent's resource card stack
				cards.put(resource, 0);
				opponent.setResourceCards(cards);
			}

			// Give all matche resources to player
			Map<IResourceCard, Integer> playerCards = player.getResourceCards();
			playerCards.put( resource, playerCards.get(resource) + lootCount );
			game.setModelVersion(game.getModelVersion() + 1);
			game.getLog().addLine(new MessageLine(userAttributes.getusername(), userAttributes.getusername() + " played a Monopoly card."));

		} catch (InvalidUserAttributesException | GameModelException e) {
			return new ServerFacadeResponse(false, e.getLocalizedMessage());
		}

		return new ServerFacadeResponse(true, null);
	}

	@Override
	public ServerFacadeResponse monument(ICommandParams params, UserAttributes userAttributes) {

		ServerMonument monument = (ServerMonument)params;

		try {
			IGame game = this.gameList.getGameById(userAttributes.getGameId());
			IPlayer player = game.getPlayerForPlayerIndex(monument.getPlayerIndex());


			Map<IDevelopmentCard,Integer> developmentCards = player.getDevelopmentCards();

			if(developmentCards.get(DevelopmentCard.MONUMENT) > 0)
			{
				// Remove the card from the player's hand
				developmentCards.put( DevelopmentCard.MONUMENT, developmentCards.get(DevelopmentCard.MONUMENT) - 1);

				// Give the player a point for a job well done
				player.setVictoryPoints( player.getVictoryPoints() + 1 );
				game.getLog().addLine(new MessageLine(userAttributes.getusername(), userAttributes.getusername() + " played a Monument card."));
			}
			else
			{
				throw new GameModelException();
			}
			game.setModelVersion( game.getModelVersion() + 1);

		} catch (InvalidUserAttributesException | GameModelException e) {
			return new ServerFacadeResponse(false, e.getLocalizedMessage());
		}

		return new ServerFacadeResponse(true, null);
	}

	@Override
	public ServerFacadeResponse robPlayer(ICommandParams params, UserAttributes userAttributes) {

		ServerRobPlayer rob = (ServerRobPlayer) params;
		try {
			IGame game = this.gameList.getGameById(userAttributes.getGameId());
			IPlayer player = game.getPlayerForPlayerIndex(rob.getPlayerIndex());

			// Move the Robber to the new location
			int x = rob.getLocation().getX();
			int y = rob.getLocation().getY();

			ILocation location = new DefaultLocation();
			location.setHexLocation(new HexLocation(x, y));

			ICatanMap catanMap = game.getMap();
			IRobber robber = catanMap.getRobber();
			robber.setLocation(location);

			// Rob the player
			IPlayer victim = game.getPlayerForPlayerIndex(rob.getVictimIndex());
			ResourceCard drawnCard = victim.drawRandomResourceCard();

			// Add resource to player's hand
			player.incrementResourceByCount(HexType.valueOf(drawnCard.getName()), 1);

			game.setModelVersion( game.getModelVersion() + 1);
			game.getLog().addLine(new MessageLine(victim.getPlayerInfo().getName(), victim.getPlayerInfo().getName() + " was robbed."));

		} catch (InvalidUserAttributesException | GameModelException e) {
			return new ServerFacadeResponse(false, e.getLocalizedMessage());
		}

		return new ServerFacadeResponse(true, null);
	}

	@Override
	public ServerFacadeResponse rollNumber(ICommandParams params, UserAttributes userAttributes) {
		ServerRoll sr = (ServerRoll)params;
		try {

			IGame game = this.gameList.getGameById(userAttributes.getGameId());
			IPlayer player = game.getPlayerForPlayerIndex(game.getTurnTracker().getCurrentTurn());
			game.getLog().addLine(new MessageLine(player.getPlayerInfo().getName(), player.getPlayerInfo().getName() + " just rolled a " + sr.getNumber()));
			Collection<IHex> hexes = game.getMap().getHexes();
			List<IHex> hexesFound = new ArrayList<>();
			for (IHex h : hexes) {
				if(h.getHexNumber() != null && h.getHexNumber() == sr.getNumber()){
					hexesFound.add(h);
				}
			}

			Collection<IPiece> cities = new ArrayList<>();
			int resourcesRequired = 0;
			for (IHex iHex : hexesFound) {
				cities.addAll(game.getMap().getSettlementsAroundHex(iHex.getLocation()));
			}

			for (IPiece p : cities) {
				switch(p.getPieceType()){
				case CITY:
					resourcesRequired += 2;
					break;
				case ROAD:
					break;
				case ROBBER:
					break;
				case SETTLEMENT:
					resourcesRequired += 1;
					break;
				default:
					break;
				
				}
			}

			int countRequired = resourcesRequired;

			// Short Circuit in even of 7 being rolled (hexesFound.size() = 0)
			if(hexesFound.size() > 0 && game.getBank().hasEnoughResources(hexesFound.get(0).getHexType(), countRequired)){

				game.getBank().decrementResourceByCount(hexesFound.get(0).getHexType(), countRequired);

				for (IPiece c : cities) {

					IPlayer p = c.getPlayer();

					int count = 0;
					switch(c.getPieceType()){
					case CITY:
						count = 2;
						break;
					case ROAD:
						break;
					case ROBBER:
						break;
					case SETTLEMENT:
						count = 1;
						break;
					default:
						break;

					}
					p.incrementResourceByCount(hexesFound.get(0).getHexType(), count);
					game.getBank().decrementResourceByCount(hexesFound.get(0).getHexType(), count);

				}
			}

			if(sr.getNumber() == 7){
				game.getTurnTracker().setStatus("Robbing");
			}else
				game.getTurnTracker().setStatus("Playing");


			game.setModelVersion( game.getModelVersion() + 1);

		} catch (InvalidUserAttributesException e) {

			return new ServerFacadeResponse(false, e.getLocalizedMessage());
		} catch (GameModelException e) {

			e.printStackTrace();
			return new ServerFacadeResponse(false, e.getLocalizedMessage());
		}

		return new ServerFacadeResponse(true, null);
	}

	@Override
	public ServerFacadeResponse soldier(ICommandParams params, UserAttributes userAttributes) {

		ServerSoldier soldier = (ServerSoldier)params;

		try {
			IGame game = this.gameList.getGameById(userAttributes.getGameId());
			IPlayer player = game.getPlayerForPlayerIndex(soldier.getPlayerIndex());
			// Move the Robber to the new location
			int x = soldier.getLocation().getX();
			int y = soldier.getLocation().getY();

			ILocation location = new DefaultLocation();
			location.setHexLocation(new HexLocation(x, y));

			ICatanMap catanMap = game.getMap();
			IRobber robber = catanMap.getRobber();
			robber.setLocation(location);

			// Rob the player
			IPlayer victim = game.getPlayerForPlayerIndex(soldier.getVictimIndex());
			ResourceCard drawnCard = victim.drawRandomResourceCard();

			// Add resource to player's hand
			player.incrementResourceByCount(HexType.valueOf(drawnCard.getName()), 1);

			// Remove a soldier card from player's hand
			int numSoldierCards = player.getDevelopmentCards().get(DevelopmentCard.SOLDIER);
			player.getDevelopmentCards().put(DevelopmentCard.SOLDIER, numSoldierCards - 1);

			game.setModelVersion( game.getModelVersion() + 1);
				String playerName = player.getPlayerInfo().getName();
			String victimName = victim.getPlayerInfo().getName();

			game.getLog().addLine(new MessageLine(playerName, playerName + " played a soldier card and robbed " + victimName + "."));

		} catch (InvalidUserAttributesException | GameModelException e) {
			return new ServerFacadeResponse(false, e.getLocalizedMessage());
		}

		return new ServerFacadeResponse(true, null);

	}

	@Override
	public ServerFacadeResponse yearOfPlenty(ICommandParams params,
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
				game.getLog().addLine(new MessageLine(userAttributes.getusername(), userAttributes.getusername() + " played a Year of Plenty card."));
			}
			else
				throw new GameModelException();

			game.setModelVersion( game.getModelVersion() + 1);

		} catch (InvalidUserAttributesException | GameModelException e) {
			return new ServerFacadeResponse(false, e.getLocalizedMessage());
		}

		return new ServerFacadeResponse(true, null);
	}
}
