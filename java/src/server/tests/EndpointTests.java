package server.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import client.models.DevelopmentCard;
import client.models.IGame;
import client.models.Proxy;
import client.models.ResourceCard;
import client.models.translator.TRResourceList;
import client.server.AcceptTrade;
import client.server.BuyDevCard;
import client.server.CreateGame;
import client.server.DiscardedCards;
import client.server.FinishedTurn;
import client.server.MaritimeTradeOff;
import client.server.OfferTrade;
import client.server.RoadBuilding;
import client.server.ServerBuildCity;
import client.server.ServerBuildRoad;
import client.server.ServerBuildSettlement;
import client.server.ServerChat;
import client.server.ServerJoinGame;
import client.server.ServerMonopoly;
import client.server.ServerMonument;
import client.server.ServerRobPlayer;
import client.server.ServerRoll;
import client.server.ServerSoldier;
import client.server.ServerYearofPlenty;
import client.server.Spot;
import client.server.User;

public class EndpointTests {
	
	private Proxy proxy;

	@Before
	public void setUp() throws Exception {
		this.proxy = new Proxy();
	}
	
	@Test
	public void test() {
		System.out.println("test started");
		
		//TEST LOGIN ENDPOINT
		assertEquals(200,proxy.postUserLogin(new User("Sam","sam")).getResponseCode());
		
		//TEST REGISTER ENDPOINT
		assertEquals(200,proxy.postUserRegister(new User("Scott","scottiscool")).getResponseCode());
				
		//TEST GET GAMES ENDPOINT
		assertEquals(3, proxy.getGamesList().size());
		
		//TEST CREATE GAME ENDPOINT
		assertEquals(200,proxy.postGamesCreate(new CreateGame(false,false,false,"MINE")).getResponseCode());
		
		//TEST JOIN GAME ENDPOINT
		assertEquals(200,proxy.postGamesJoin(new ServerJoinGame(3,"orange")).getResponseCode());
		
		//TEST SAVE GAME ENDPOINT
		assertEquals(200,proxy.postGamesSave().getResponseCode());
		
		//TEST LOAD GAME ENDPOINT
		assertEquals(200,proxy.postGamesLoad().getResponseCode());
		
		//TEST GET GAME MODEL ENDPOINT
		assertEquals(200,proxy.getGameModelTesting().getResponseCode());
		//don't really need all these below for testing the model, but it's fun
		IGame g = proxy.getGameModel();
		assert(g.getMap().getCities().size() == 0);
		assert(g.getMap().getSettlements().size() == 8);
		assert(g.getPlayers().length == 4);
		
		assert(g.getPlayers()[0].getResourceCards().get(ResourceCard.BRICK) == 0);
		assert(g.getPlayers()[0].getResourceCards().get(ResourceCard.WOOD) == 1);
		assert(g.getPlayers()[0].getResourceCards().get(ResourceCard.SHEEP) == 1);
		assert(g.getPlayers()[0].getResourceCards().get(ResourceCard.WHEAT) == 1);
		assert(g.getPlayers()[0].getResourceCards().get(ResourceCard.ORE) == 0);
		
		assert(g.getPlayers()[0].getDevelopmentCards().get(DevelopmentCard.MONOPOLY) == 0);
		assert(g.getPlayers()[0].getDevelopmentCards().get(DevelopmentCard.MONUMENT) == 0);
		assert(g.getPlayers()[0].getDevelopmentCards().get(DevelopmentCard.ROAD_BUILD) == 0);
		assert(g.getPlayers()[0].getDevelopmentCards().get(DevelopmentCard.SOLDIER) == 0);
		assert(g.getPlayers()[0].getDevelopmentCards().get(DevelopmentCard.YEAR_OF_PLENTY) == 0);
		
		assert(g.getPlayers()[0].getCities().size() == 4);
		assert(g.getPlayers()[0].getSettlements().size() == 3);
		assert(g.getPlayers()[0].getMonuments() == 0);
		assert(g.getPlayers()[0].getSoldiers() == 0);
		assert(g.getPlayers()[0].getRoads().size() == 13);
		assert(g.getPlayers()[0].getVictoryPoints() == 2);
		
		assert(g.getBank().getResourceCards().get(ResourceCard.BRICK) == 23);
		assert(g.getBank().getResourceCards().get(ResourceCard.WOOD) == 21);
		assert(g.getBank().getResourceCards().get(ResourceCard.SHEEP) == 20);
		assert(g.getBank().getResourceCards().get(ResourceCard.WHEAT) == 22);
		assert(g.getBank().getResourceCards().get(ResourceCard.ORE) == 22);

		assert(g.getBank().getDevelopmentCards().get(DevelopmentCard.MONOPOLY) == 2);
		assert(g.getBank().getDevelopmentCards().get(DevelopmentCard.MONUMENT) == 5);
		assert(g.getBank().getDevelopmentCards().get(DevelopmentCard.ROAD_BUILD) == 2);
		assert(g.getBank().getDevelopmentCards().get(DevelopmentCard.SOLDIER) == 14);
		assert(g.getBank().getDevelopmentCards().get(DevelopmentCard.YEAR_OF_PLENTY) == 2);
		
		assert(g.getChat().getLines().size() == 0);
		assert(g.getLog().getLines().size() == 24);
		assert(g.getLog().getLines().get(0).getMessage().equals("Sam built a road"));
		assert(g.getLog().getLines().get(0).getSource().equals("Sam"));
		
		assert(g.getTurnTracker().getCurrentTurn() == 0);
		assert(g.getTurnTracker().getLongestRoad() == -1);
		assert(g.getTurnTracker().getLargestArmy() == -1);
		assert(g.getTurnTracker().getStatus().equals("Rolling"));
		
		assert(g.getWinner() == -1);
		assert(g.getModelVersion() == 0);
		
		//TEST RESET GAME ENDPOINT
		assertEquals(200,proxy.postGameReset().getResponseCode());
		
		//TEST POST COMMANDS ENDPOINT
		
		//TEST GET COMMANDS ENDPOINT
		
		//TEST SEND CHAT ENDPOINT
		ServerChat serverChat = new ServerChat("sendChat",0,"Hi everyone!");
		assertEquals(200,proxy.movesSendChat(serverChat).getResponseCode());
		
		//TEST ROLL NUMBER ENDPOINT
		ServerRoll serverRoll = new ServerRoll("rollNumber",0,5);
		assertEquals(200,proxy.movesRollNumber(serverRoll).getResponseCode());
		
		//TEST ROB PLAYER ENDPOINT
		HexLocation location = new HexLocation(0,0);
		ServerRobPlayer serverRob = new ServerRobPlayer("robPlayer",0,1,location);
		assertEquals(200,proxy.moveRobPlayer(serverRob).getResponseCode());
		
		//TEST FINISHED TURN ENDPOINT
		FinishedTurn ft = new FinishedTurn("finishTurn",0);
		assertEquals(200,proxy.movesFinishTurn(ft).getResponseCode());
		
		//TEST BUY DEV CARD ENDPOINT
		BuyDevCard bdc = new BuyDevCard("buyDevCard",0);
		assertEquals(200,proxy.movesBuyDevCard(bdc).getResponseCode());
		
		//TEST YEAR OF PLENTY ENDPOINT
		ServerYearofPlenty yop = new ServerYearofPlenty("Year_of_Plenty",0,"resource1","resource2");
		assertEquals(200,proxy.movesYear_of_Plenty(yop).getResponseCode());
		
		//TEST ROAD BUILDING ENDPOINT
		Spot spot1 = new Spot(0,0,"sw");
		Spot spot2 = new Spot(1,1,"e");
		RoadBuilding rb = new RoadBuilding("Road_Building",0,spot1,spot2);
		assertEquals(200,proxy.movesRoad_Building(rb).getResponseCode());
		
		//TEST SOLDIER ENDPOINT
		HexLocation location1 = new HexLocation(0,0);
		ServerSoldier ss = new ServerSoldier("Soldier",0,1,location1);
		assertEquals(200,proxy.movesSoldier(ss).getResponseCode());
		
		//TEST MONOPOLY ENDPOINT
		ServerMonopoly sm = new ServerMonopoly("Monopoly","wood",0);
		assertEquals(200,proxy.movesMonopoly(sm).getResponseCode());
		
		//TEST MONUMENT ENDPOINT
		ServerMonument sm1 = new ServerMonument("Monument",0);
		assertEquals(200,proxy.movesMonument(sm1).getResponseCode());
		
		//TEST BUILD ROAD ENDPOINT
		HexLocation location2 = new HexLocation(0,0);
		EdgeLocation el = new EdgeLocation(location2, EdgeDirection.North);
		ServerBuildRoad br = new ServerBuildRoad("buildRoad",0,el,false);
		assertEquals(200,proxy.movesBuildRoad(br).getResponseCode());
		
		//TEST BUILD SETTLEMENT ENDPOINT
		HexLocation location3 = new HexLocation(0,0);
		VertexLocation vl = new VertexLocation(location3,VertexDirection.East);
		ServerBuildSettlement bs = new ServerBuildSettlement("buildSettlement",0,vl,false);
		assertEquals(200,proxy.movesBuildSettlement(bs).getResponseCode());
		
		//TEST BUILD CITY ENDPOINT
		HexLocation location4 = new HexLocation(0,0);
		VertexLocation vl1 = new VertexLocation(location4,VertexDirection.East);
		ServerBuildCity bc = new ServerBuildCity("buildCity",0,vl1);
		assertEquals(200,proxy.movesBuildCity(bc).getResponseCode());
		
		//TEST OFFER TRADE
		TRResourceList rl = new TRResourceList();
		rl.setBrick(0);
		rl.setOre(1);
		rl.setSheep(0);
		rl.setWheat(1);
		rl.setWood(0);
		OfferTrade ot = new OfferTrade(0,1,rl,"offerTrade");
		assertEquals(200,proxy.movesOfferTrade(ot).getResponseCode());
		
		//TEST ACCEPT TRADE ENDPOINT
		AcceptTrade at = new AcceptTrade("acceptTrade",0, true);
		assertEquals(200,proxy.movesAcceptTrade(at).getResponseCode());
		
		//TEST MARITIME TRADE ENDPOINT
		MaritimeTradeOff mt = new MaritimeTradeOff("maritimeTrade",0,3,"wood","wool");
		assertEquals(200,proxy.movesMaritimeTrade(mt).getResponseCode());
		
		//TEST DISCARD CARDS
		DiscardedCards dc = new DiscardedCards("discardCards",0,rl);
		assertEquals(200,proxy.movesDiscardCards(dc).getResponseCode());
		
	}
}
