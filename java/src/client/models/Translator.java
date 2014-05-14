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
		if(cm == null || iGame == null)
			throw new RuntimeException("cm and iGame should never be null");
		
		Game g = (Game)iGame;
		
		
		
		IBank bank = new Bank(cm.getDeck(), cm.getBank());
		g.setBank(bank);
		
		g.setModelVersion(g.getModelVersion() + 1);

		g.setPlayers((IPlayer[]) new Player[cm.getPlayers().length]);
		int index = 0;
		for (TRPlayer p : cm.getPlayers()) {
			
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
				ISettlement newS = new Settlement(new VertexLocation(s.getLocation()), newPlayer, 1);// TODO point value?
				settlements.add(newS);
			}
			newPlayer.setSettlements(settlements);
			assert(settlements.size() == p.getSettlements());
			
			List<ICity> cities = new ArrayList<ICity>();
			for (TRVertexObject c : cm.getMap().getCities()) {
				if((long)c.getOwner() != p.getPlayerID())
					continue;
				ICity newC = new City(new VertexLocation(c.getLocation()), newPlayer, 1);// TODO point value?
				cities.add(newC);
			}
			newPlayer.setCities(cities);
			assert(cities.size() == p.getCities());
			
			List<IRoad> roads = new ArrayList<IRoad>();
			for (TRRoad road : cm.getMap().getRoads()) {
				if((long)road.getOwner() != p.getPlayerID())
					continue;
				IRoad r = new Road(playerInfo.getColor(), newPlayer, false, new EdgeLocation(road.getLocation()));
				roads.add(r);
			}
			newPlayer.setRoads(roads);
			assert(roads.size() == p.getRoads());
			
			
			Map<IResourceCard, Integer> resourceCards = new HashMap<IResourceCard, Integer>();
			TRResourceList resources = p.getResources();
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
		
		ICatanMap map = new CatanMap();
		for (TRHex hex : cm.getMap().getHexes()) {
			map.addHex(new Hex(hex));
		}
		for (TRVertexObject city : cm.getMap().getCities()) {
			map.placeCity(new City(new VertexLocation(city.getLocation()), this.getPlayerWithId(city.getOwner(), g.getPlayers()), 2));
		}
		for (TRPort port : cm.getMap().getPorts()) {
			map.addPort(new Port(port));
		}
		for(TRRoad road : cm.getMap().getRoads()){
			map.placeRoadSegment(new RoadSegment(road, this.getPlayerWithId(road.getOwner(), g.getPlayers())));
		}
		for (TRVertexObject settl : cm.getMap().getSettlements()) {
			map.placeSettlement(new Settlement(new VertexLocation(settl.getLocation()), (Player)this.getPlayerWithId(settl.getOwner(), g.getPlayers()), 1));
		}
		
		IRobber robber = new Robber(cm.getMap().getRobber());
		map.setRobber(robber);
		map.setRadius(cm.getMap().getRadius());
		g.setMap(map);
		
		return g;
	}

	private IPlayer getPlayerWithId(Integer owner, IPlayer[] players) {
		for (IPlayer iPlayer : players) {
			if(iPlayer.getPlayerInfo().getId() == owner)
				return iPlayer;
		}
		return null;
	}
}

















































