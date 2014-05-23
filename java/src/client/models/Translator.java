/**
 *
 */
package client.models;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shared.definitions.CatanColor;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;
import client.data.PlayerInfo;
import client.models.translator.ClientModel;
import client.models.translator.TRDevCardList;
import client.models.translator.TRHex;
import client.models.translator.TRMessageLine;
import client.models.translator.TRPlayer;
import client.models.translator.TRPort;
import client.models.translator.TRResourceList;
import client.models.translator.TRRoad;
import client.models.translator.TRVertexObject;

/**
 * This class receives either JSON or Java objects and converts it to the other format
 * @author scottdaly
 *
 */
public class Translator {

	public Translator() {}

	public IGame convertClientModelToGame(ClientModel cm, IGame iGame) throws InvalidLocationException{
		if(cm == null)
			throw new RuntimeException("cm  should never be null");
		if(iGame == null)
			throw new RuntimeException("iGame  should never be null");

		Game g = (Game)iGame;

		g.setLog(new MessageList());
		for (TRMessageLine line : cm.getLog().getLines()) {
			g.addLog(new MessageLine(line));
		}

		g.setChat(new MessageList());
		for (TRMessageLine line : cm.getChat().getLines()) {
			g.addChat(new MessageLine(line));
		}

		g.setCurrentTrade(cm.getTradeOffer());

		g.setTurnTracker(new TurnTracker(cm.getTurnTracker()));

		g.setModelVersion(cm.getVersion());

		g.setWinner(cm.getWinner());

		IBank bank = new Bank(cm.getDeck(), cm.getBank());
		g.setBank(bank);

		//check to see how many players are actually in the players list for the game
		int count = 0;
		for(int i = 0; i < cm.getPlayers().length; i++){
			if(cm.getPlayers()[i] != null)
				count++;
		}
		g.setPlayers((IPlayer[]) new Player[count]);
		int index = 0;
		for (TRPlayer p : cm.getPlayers()) {
			if(p != null){
				PlayerInfo playerInfo = new PlayerInfo();
				playerInfo.setId(p.getPlayerID());
				playerInfo.setPlayerIndex(p.getPlayerIndex());
				playerInfo.setName(p.getName());
				playerInfo.setColor(CatanColor.getColorForName(p.getColor()));

				Player newPlayer = new Player(playerInfo);

				newPlayer.setSoldiers(p.getSoldiers());
				newPlayer.setVictoryPoints(p.getVictoryPoints());
				newPlayer.setMonuments(p.getMonuments());
				newPlayer.setPlayedDevCard(p.isPlayedDevCard());
				newPlayer.setDiscarded(p.isDiscarded());

				List<ISettlement> settlements = new ArrayList<ISettlement>();
				for (TRVertexObject s : cm.getMap().getSettlements()) {
					if((long)s.getOwner() != p.getPlayerID())
						continue;
					ISettlement newS = new Settlement(new VertexLocation(s.getLocation()), newPlayer);
					settlements.add(newS);
				}
				newPlayer.setSettlements(settlements);
	//			assert(settlements.size() == p.getSettlements());

				List<ICity> cities = new ArrayList<ICity>();
				for (TRVertexObject c : cm.getMap().getCities()) {
					if((long)c.getOwner() != p.getPlayerID())
						continue;
					ICity newC = new City(new VertexLocation(c.getLocation()), newPlayer);
					cities.add(newC);
				}
				newPlayer.setCities(cities);
	//			assert(cities.size() == p.getCities());

//				List<IRoad> roads = new ArrayList<IRoad>();
//				for (TRRoad road : cm.getMap().getRoads()) {
//					if((long)road.getOwner() != p.getPlayerID())
//						continue;
//					IRoad r = new Road(playerInfo.getColor(), newPlayer, false, new EdgeLocation(road.getLocation()));
//					roads.add(r);
//				}
//				newPlayer.setRoads(roads);

				List<IRoadSegment> roads = new ArrayList<>();
				for (TRRoad road : cm.getMap().getRoads()) {
					if((long)road.getOwner() != p.getPlayerID())
						continue;
					IRoadSegment r = new RoadSegment(road, newPlayer);
					roads.add(r);
				}
				newPlayer.setRoads(roads);

	//			assert(roads.size() == p.getRoads());


				Map<IResourceCard, Integer> resourceCards = new HashMap<IResourceCard, Integer>();
				TRResourceList resources = p.getResources();
	//			resourceCards.put(ResourceCard.BRICK, 10 + index);
	//			resourceCards.put(ResourceCard.ORE, 10 + index);
	//			resourceCards.put(ResourceCard.SHEEP, 10 + index);
	//			resourceCards.put(ResourceCard.WHEAT, 10 + index);
	//			resourceCards.put(ResourceCard.WOOD, 10 + index);
				resourceCards.put(ResourceCard.BRICK, resources.getBrick());
				resourceCards.put(ResourceCard.ORE, resources.getOre());
				resourceCards.put(ResourceCard.SHEEP, resources.getSheep());
				resourceCards.put(ResourceCard.WHEAT, resources.getWheat());
				resourceCards.put(ResourceCard.WOOD, resources.getWood());
				newPlayer.setResourceCards(resourceCards);

				Map<IDevelopmentCard, Integer> developmentCards = new HashMap<IDevelopmentCard, Integer>();
				TRDevCardList newDevCards = p.getNewDevCards();
	//			TRDevCardList oldDevCards = p.getOldDevCards();
				developmentCards.put(DevelopmentCard.MONOPOLY, newDevCards.getMonopoly());
				developmentCards.put(DevelopmentCard.MONUMENT, newDevCards.getMonument());
				developmentCards.put(DevelopmentCard.ROAD_BUILD, newDevCards.getRoadBuilding());
				developmentCards.put(DevelopmentCard.SOLDIER, newDevCards.getSoldier());
				developmentCards.put(DevelopmentCard.YEAR_OF_PLENTY, newDevCards.getYearOfPlenty());
				newPlayer.setDevelopmentCards(developmentCards);

				g.getPlayers()[index] = newPlayer;
				index++;
			}

		}

		ICatanMap map = new CatanMap();
		for (TRHex hex : cm.getMap().getHexes()) {
			map.addHex(new Hex(hex));
		}
		for (TRPort port : cm.getMap().getPorts()) {
			map.addPort(new Port(port));
		}
		for (TRVertexObject settl : cm.getMap().getSettlements()) {
			IPlayer player = this.getPlayerWithId(settl.getOwner(), g.getPlayers());
			map.placeInitialSettlement(new Settlement(new VertexLocation(settl.getLocation()),
			                                          player));
		}
		for (TRVertexObject city : cm.getMap().getCities()) {
			map.placeInitialCity(new City(new VertexLocation(city.getLocation()), this.getPlayerWithId(city.getOwner(), g.getPlayers())));
		}
		for(TRRoad road : cm.getMap().getRoads()){
			map.placeInitialRoadSegment(new RoadSegment(road, this.getPlayerWithId(road.getOwner(), g.getPlayers())));
		}

		IRobber robber = new Robber(cm.getMap().getRobber());
		map.setRobber(robber);
		map.setRadius(cm.getMap().getRadius());
		g.setMap(map);

		return g;
	}

	private IPlayer getPlayerWithId(Integer owner, IPlayer[] players) {
		for (IPlayer iPlayer : players) {
			if(iPlayer.getPlayerInfo().getPlayerIndex() == owner.intValue())
			{
				return iPlayer;
			}
		}
		return null;
	}
}

















































