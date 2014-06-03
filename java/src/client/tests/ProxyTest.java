package client.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import client.models.DevelopmentCard;
import client.models.DummyProxy;
import client.models.IGame;
import client.models.Proxy;
import client.models.ResourceCard;
import client.server.AcceptTrade;
import client.server.BuyDevCard;
import client.server.CreateGame;
import client.server.FinishedTurn;
import client.server.MaritimeTradeOff;
import client.server.RoadBuilding;
import client.server.ServerBuildCity;
import client.server.ServerBuildRoad;
import client.server.ServerBuildSettlement;
import client.server.ServerChat;
import client.server.ServerJoinGame;
import client.server.ServerLogLevel;
import client.server.ServerMonopoly;
import client.server.ServerMonument;
import client.server.ServerRobPlayer;
import client.server.ServerRoll;
import client.server.ServerSoldier;
import client.server.ServerYearofPlenty;
import client.server.Spot;
import client.server.User;


public class ProxyTest {
	
	private Proxy proxy;
	private DummyProxy dummyProxy;
	private String illegalMove = "CommandError: A syntatically correct command was an illegal move: class java.lang.NullPointerException";
	private String illegalMove1 = "CommandError: A syntatically correct command was an illegal move: class java.lang.ArrayIndexOutOfBoundsException";

	@BeforeClass
	public static void setUpBeforeClass()
	{
		
	}
	
	@Before
	public void setUp() throws Exception {
		proxy = new Proxy();
		this.dummyProxy = new DummyProxy();
	}

	@Test
	public void test() {
		//TEST LOGIN ENDPOINT (works)
		User user1 = new User("Sam","sam");
		assertEquals("Success",dummyProxy.postUserLogin(user1).getJson());
		
		//TEST REGISTER ENDPOINT (works)
		User user3 = new User("Scott","scottiscool");
		assertEquals("Success",dummyProxy.postUserRegister(user3).getJson());
	
		//TEST GET GAMES LIST (Cameron??)
		//assertEquals(200,dummyProxy.getGamesList().getResponseCode());
		assertEquals(3, dummyProxy.getGames().size());
		
		//TEST CREATE GAME (works)
		CreateGame createGame = new CreateGame(false,false,false,"MINE");
		assertEquals(200,dummyProxy.postGamesCreate(createGame).getResponseCode());
		
		//TEST JOIN GAME (works)
		ServerJoinGame serverJoinGame = new ServerJoinGame(3,"orange");
		assertEquals("Success",dummyProxy.postGamesJoin(serverJoinGame).getJson());
		
		//TEST GET MODEL (Cameron???)
//		dummyProxy.getGamesList();
		IGame g = dummyProxy.getGameModel();
		assert(g.getMap().getCities().size() == 0);
		assert(g.getMap().getSettlements().size() == 8);
		assert(g.getPlayers().length == 4);
		/*
		 * "resources": {
		"brick": 0,
		"wood": 1,
		"sheep": 1,
		"wheat": 1,
		"ore": 0
	  },
	  "oldDevCards": {
		"yearOfPlenty": 0,
		"monopoly": 0,
		"soldier": 0,
		"roadBuilding": 0,
		"monument": 0
	  },
	  "newDevCards": {
		"yearOfPlenty": 0,
		"monopoly": 0,
		"soldier": 0,
		"roadBuilding": 0,
		"monument": 0
	  },
	  "roads": 13,
	  "cities": 4,
	  "settlements": 3,
	  "soldiers": 0,
	  "victoryPoints": 2,
	  "monuments": 0,
	  "playedDevCard": false,
	  "discarded": false,
	  "playerID": 0,
	  "playerIndex": 0,
	  "name": "Sam",
	  "color": "orange"
		 */
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
		
		/*
		 * "bank": {
	"brick": 23,
	"wood": 21,
	"sheep": 20,
	"wheat": 22,
	"ore": 22
  },
		 */
		
		assert(g.getBank().getResourceCards().get(ResourceCard.BRICK) == 23);
		assert(g.getBank().getResourceCards().get(ResourceCard.WOOD) == 21);
		assert(g.getBank().getResourceCards().get(ResourceCard.SHEEP) == 20);
		assert(g.getBank().getResourceCards().get(ResourceCard.WHEAT) == 22);
		assert(g.getBank().getResourceCards().get(ResourceCard.ORE) == 22);
/*
 * "deck": {
	"yearOfPlenty": 2,
	"monopoly": 2,
	"soldier": 14,
	"roadBuilding": 2,
	"monument": 5
  },
 */
		assert(g.getBank().getDevelopmentCards().get(DevelopmentCard.MONOPOLY) == 2);
		assert(g.getBank().getDevelopmentCards().get(DevelopmentCard.MONUMENT) == 5);
		assert(g.getBank().getDevelopmentCards().get(DevelopmentCard.ROAD_BUILD) == 2);
		assert(g.getBank().getDevelopmentCards().get(DevelopmentCard.SOLDIER) == 14);
		assert(g.getBank().getDevelopmentCards().get(DevelopmentCard.YEAR_OF_PLENTY) == 2);
		
		assert(g.getChat().getLines().size() == 0);
		assert(g.getLog().getLines().size() == 24);
		assert(g.getLog().getLines().get(0).getMessage().equals("Sam built a road"));
		assert(g.getLog().getLines().get(0).getSource().equals("Sam"));
		
		/*
		 * "turnTracker": {
	"status": "Rolling",
	"currentTurn": 0,
	"longestRoad": -1,
	"largestArmy": -1
  },
		 */
		assert(g.getTurnTracker().getCurrentTurn() == 0);
		assert(g.getTurnTracker().getLongestRoad() == -1);
		assert(g.getTurnTracker().getLargestArmy() == -1);
		assert(g.getTurnTracker().getStatus().equals("Rolling"));
		
		assert(g.getWinner() == -1);
		assert(g.getModelVersion() == 0);
		
		//TEST SEND CHAT 
		ServerChat serverChat = new ServerChat("sendChat",0,"Hi everyone!");
		assertEquals(200,dummyProxy.movesSendChat(serverChat).getResponseCode());
		
		//TEST ROLL NUMBER (illegal move)
		ServerRoll serverRoll = new ServerRoll("rollNumber",0,5);
		assertEquals(dummyModel,dummyProxy.movesRollNumber(serverRoll).getJson());
		
		//TEST ROB PLAYER (illegal move)
		HexLocation location = new HexLocation(0,0);
		ServerRobPlayer serverRob = new ServerRobPlayer("robPlayer",0,1,location);
		assertEquals(dummyModel,dummyProxy.moveRobPlayer(serverRob).getJson());
		
		//TEST FINISHED TURN (illegal move)
		FinishedTurn ft = new FinishedTurn("finishTurn",0);
		assertEquals(dummyModel,dummyProxy.movesFinishTurn(ft).getJson());
		
		//TEST BUY DEV CARD (works)
		BuyDevCard bdc = new BuyDevCard("buyDevCard",0);
		assertEquals(200,dummyProxy.movesBuyDevCard(bdc).getResponseCode());
		
		//TEST YEAR OF PLENTY (illegal move)
		ServerYearofPlenty yop = new ServerYearofPlenty("Year_of_Plenty",0,"resource1","resource2");
		assertEquals(dummyModel,dummyProxy.movesYear_of_Plenty(yop).getJson());
		
		//TEST ROAD BUILDING (illegal move)
		Spot spot1 = new Spot(0,0,"sw");
		Spot spot2 = new Spot(1,1,"e");
		RoadBuilding rb = new RoadBuilding("Road_Building",0,spot1,spot2);
		assertEquals(dummyModel,dummyProxy.movesRoad_Building(rb).getJson());
		
		//TEST SOLDIER (illegal move)
		HexLocation location1 = new HexLocation(0,0);
		ServerSoldier ss = new ServerSoldier("Soldier",0,1,location1);
		assertEquals(dummyModel,dummyProxy.movesSoldier(ss).getJson());
		
		//TEST MONOPOLY (illegal move)
		ServerMonopoly sm = new ServerMonopoly("Monopoly","wood",0);
		assertEquals(dummyModel,dummyProxy.movesMonopoly(sm).getJson());
		
		//TEST MONUMENT (works)
		ServerMonument sm1 = new ServerMonument("Monument",0);
		assertEquals(200,dummyProxy.movesMonument(sm1).getResponseCode());
		
		//TEST BUILD ROAD (illegal move)
		HexLocation location2 = new HexLocation(0,0);
		EdgeLocation el = new EdgeLocation(location2, EdgeDirection.North);
		ServerBuildRoad br = new ServerBuildRoad("buildRoad",0,el,false);
		assertEquals(dummyModel,dummyProxy.movesBuildRoad(br).getJson());
		
		//TEST BUILD SETTLEMENT (illegal move)
		HexLocation location3 = new HexLocation(0,0);
		VertexLocation vl = new VertexLocation(location3,VertexDirection.East);
		ServerBuildSettlement bs = new ServerBuildSettlement("buildSettlement",0,vl,false);
		assertEquals(dummyModel,dummyProxy.movesBuildSettlement(bs).getJson());
		
		//TEST BUILD CITY
		HexLocation location4 = new HexLocation(0,0);
		VertexLocation vl1 = new VertexLocation(location4,VertexDirection.East);
		ServerBuildCity bc = new ServerBuildCity("buildCity",0,vl1);
		assertEquals(dummyModel,dummyProxy.movesBuildCity(bc).getJson());
		
		//TEST OFFER TRADE
		
		//TEST ACCEPT TRADE (illegal move)
		AcceptTrade at = new AcceptTrade("acceptTrade",0, true);
		assertEquals(dummyModel,dummyProxy.movesAcceptTrade(at).getJson());
		
		//TEST MARITIME TRADE (illegal move)
		MaritimeTradeOff mt = new MaritimeTradeOff("maritimeTrade",0,3,"wood","wool");
		assertEquals(dummyModel,dummyProxy.movesMaritimeTrade(mt).getJson());
		
		//TEST DISCARD CARDS
		//To Dan:
		//	create discardCards object and compare it to the ServerResponse object handed back
		
		//TEST LOG LEVEL CHANGE (works)
		ServerLogLevel sll = new ServerLogLevel("ALL");
		assertEquals("Success",dummyProxy.utilChangeLogLevel(sll).getJson());
		
		//TEST RESET GAME 
		assertEquals(200,dummyProxy.postGameReset().getResponseCode());
		
	}
	
	private String dummyModel = "{ 'deck': { 'yearOfPlenty': 2, "
			   + "'monopoly': 2, "
			   + "'soldier': 14, "
			   + "'roadBuilding': 2, "
			   + "'monument': 5 }, "
			   
   + "'map': { 'hexes': [ { 'location': { 'x': 0, 'y': -2 } }, "
   					  + "{ 'resource': 'brick', 'location': { 'x': 1, 'y': -2 }, 'number': 4 }, "
   					  + "{ 'resource': 'wood', 'location': { 'x': 2, 'y': -2 }, 'number': 11 }, "
   					  + "{ 'resource': 'brick', 'location': { 'x': -1, 'y': -1 }, 'number': 8 }, "
   					  + "{ 'resource': 'wood', 'location': { 'x': 0, 'y': -1 }, 'number': 3 }, "
   					  + "{ 'resource': 'ore', 'location': { 'x': 1, 'y': -1 }, 'number': 9 }, "
   					  + "{ 'resource': 'sheep', 'location': { 'x': 2, 'y': -1 }, 'number': 12 }, "
   					  + "{ 'resource': 'ore', 'location': { 'x': -2, 'y': 0 }, 'number': 5 }, "
   					  + "{ 'resource': 'sheep', 'location': { 'x': -1, 'y': 0 }, 'number': 10 }, "
   					  + "{ 'resource': 'wheat', 'location': { 'x': 0, 'y': 0 }, 'number': 11 }, "
   					  + "{ 'resource': 'brick', 'location': { 'x': 1, 'y': 0 }, 'number': 5 }, "
   					  + "{ 'resource': 'wheat', 'location': { 'x': 2, 'y': 0 }, 'number': 6 }, "
   					  + "{ 'resource': 'wheat', 'location': { 'x': -2, 'y': 1 }, 'number': 2 }, "
   					  + "{ 'resource': 'sheep', 'location': { 'x': -1, 'y': 1 }, 'number': 9 }, "
   					  + "{ 'resource': 'wood', 'location': { 'x': 0, 'y': 1 }, 'number': 4 }, "
   					  + "{ 'resource': 'sheep', 'location': { 'x': 1, 'y': 1 }, 'number': 10 }, "
   					  + "{ 'resource': 'wood', 'location': { 'x': -2, 'y': 2 }, 'number': 6 }, "
   					  + "{ 'resource': 'ore', 'location': { 'x': -1, 'y': 2 }, 'number': 3 }, "
   					  + "{ 'resource': 'wheat', 'location': { 'x': 0, 'y': 2 }, 'number': 8 } ], "
   					  
			   + "'roads': [ { 'owner': 2, 'location': { 'direction': 'S', 'x': 1, 'y': -1 } }, "
			   			  + "{ 'owner': 3, 'location': { 'direction': 'SW', 'x': 2, 'y': -2 } }, "
			   			  + "{ 'owner': 0, 'location': { 'direction': 'S', 'x': 0, 'y': 1 } }, "
			   			  + "{ 'owner': 1, 'location': { 'direction': 'SW', 'x': -2, 'y': 1 } }, "
			   			  + "{ 'owner': 2, 'location': { 'direction': 'S', 'x': 0, 'y': 0 } }, "
			   			  + "{ 'owner': 0, 'location': { 'direction': 'SW', 'x': 2, 'y': 0 } }, "
			   			  + "{ 'owner': 1, 'location': { 'direction': 'S', 'x': -1, 'y': -1 } }, "
			   			  + "{ 'owner': 3, 'location': { 'direction': 'SW', 'x': -1, 'y': 1 } } ], "
			   			  
  			   + "'cities': [], "
  			   
  			   + "'settlements': [ { 'owner': 3, 'location': { 'direction': 'SE', 'x': 1, 'y': -2 } }, "
  			   					+ "{ 'owner': 2, 'location': { 'direction': 'SW', 'x': 0, 'y': 0 } }, "
  			   					+ "{ 'owner': 2, 'location': { 'direction': 'SW', 'x': 1, 'y': -1 } }, "
  			   					+ "{ 'owner': 1, 'location': { 'direction': 'SW', 'x': -1, 'y': -1 } }, "
  			   					+ "{ 'owner': 0, 'location': { 'direction': 'SE', 'x': 0, 'y': 1 } }, "
  			   					+ "{ 'owner': 1, 'location': { 'direction': 'SW', 'x': -2, 'y': 1 } }, "
  			   					+ "{ 'owner': 0, 'location': { 'direction': 'SW', 'x': 2, 'y': 0 } }, "
  			   					+ "{ 'owner': 3, 'location': { 'direction': 'SW', 'x': -1, 'y': 1 } } ], "
  			   					
				   + "'radius': 3, "
				   
				   + "'ports': [ { 'ratio': 2, 'resource': 'wheat', 'direction': 'S', 'location': { 'x': -1, 'y': -2 } }, "
				   			  + "{ 'ratio': 3, 'direction': 'SW', 'location': { 'x': 3, 'y': -3 } }, "
				   			  + "{ 'ratio': 2, 'resource': 'ore', 'direction': 'S', 'location': { 'x': 1, 'y': -3 } }, "
				   			  + "{ 'ratio': 3, 'direction': 'SE', 'location': { 'x': -3, 'y': 0 } }, "
				   			  + "{ 'ratio': 2, 'resource': 'wood', 'direction': 'NE', 'location': { 'x': -3, 'y': 2 } }, "
				   			  + "{ 'ratio': 2, 'resource': 'brick', 'direction': 'NE', 'location': { 'x': -2, 'y': 3 } }, "
				   			  + "{ 'ratio': 3, 'direction': 'NW', 'location': { 'x': 2, 'y': 1 } }, "
				   			  + "{ 'ratio': 2, 'resource': 'sheep', 'direction': 'NW', 'location': { 'x': 3, 'y': -1 } }, "
				   			  + "{ 'ratio': 3, 'direction': 'N', 'location': { 'x': 0, 'y': 3 } } ], "
				   			  
   			   + "'robber': { 'x': 0, 'y': -2 } }, "
   			   
   			   + "'players': [ { 'resources': { 'brick': 0, 'wood': 1, 'sheep': 1, 'wheat': 1, 'ore': 0 }, "
   			   				  + "'oldDevCards': { 'yearOfPlenty': 0, 'monopoly': 0, 'soldier': 0, 'roadBuilding': 0, 'monument': 0 }, "
   			   				  + "'newDevCards': { 'yearOfPlenty': 0, 'monopoly': 0, 'soldier': 0, 'roadBuilding': 0, 'monument': 0 }, "
   			   				  + "'roads': 13, "
   			   				  + "'cities': 4, "
   			   				  + "'settlements': 3, "
   			   				  + "'soldiers': 0, "
   			   				  + "'victoryPoints': 2, "
   			   				  + "'monuments': 0, "
   			   				  + "'playedDevCard': false, "
   			   				  + "'discarded': false, "
   			   				  + "'playerID': 0, "
   			   				  + "'playerIndex': 0, "
   			   				  + "'name': 'Sam', "
   			   				  + "'color': 'orange' }, "
   			   				+ "{ 'resources': { 'brick': 1, 'wood': 0, 'sheep': 1, 'wheat': 0, 'ore': 1 }, "
   			   				  + "'oldDevCards': { 'yearOfPlenty': 0, 'monopoly': 0, 'soldier': 0, 'roadBuilding': 0, 'monument': 0 }, "
   			   				  + "'newDevCards': { 'yearOfPlenty': 0, 'monopoly': 0, 'soldier': 0, 'roadBuilding': 0, 'monument': 0 }, "
   			   				  + "'roads': 13, "
   			   				  + "'cities': 4, "
   			   				  + "'settlements': 3, "
   			   				  + "'soldiers': 0, "
   			   				  + "'victoryPoints': 2, "
   			   				  + "'monuments': 0, "
   			   				  + "'playedDevCard': false, "
   			   				  + "'discarded': false, "
   			   				  + "'playerID': 1, "
   			   				  + "'playerIndex': 1, "
   			   				  + "'name': 'Brooke', "
   			   				  + "'color': 'blue' }, "
   			   				+ "{ 'resources': { 'brick': 0, 'wood': 1, 'sheep': 1, 'wheat': 1, 'ore': 0 }, "
   			   				  + "'oldDevCards': { 'yearOfPlenty': 0, 'monopoly': 0, 'soldier': 0, 'roadBuilding': 0, 'monument': 0 }, "
   			   				  + "'newDevCards': { 'yearOfPlenty': 0, 'monopoly': 0, 'soldier': 0, 'roadBuilding': 0, 'monument': 0 }, "
   			   				  + "'roads': 13, "
   			   				  + "'cities': 4, "
   			   				  + "'settlements': 3, "
   			   				  + "'soldiers': 0, "
   			   				  + "'victoryPoints': 2, "
   			   				  + "'monuments': 0, "
   			   				  + "'playedDevCard': false, "
   			   				  + "'discarded': false, "
   			   				  + "'playerID': 10, "
   			   				  + "'playerIndex': 2, "
   			   				  + "'name': 'Pete', "
   			   				  + "'color': 'red' }, "
   			   				+ "{ 'resources': { 'brick': 0, 'wood': 1, 'sheep': 1, 'wheat': 0, 'ore': 1 }, "
   			   				  + "'oldDevCards': { 'yearOfPlenty': 0, 'monopoly': 0, 'soldier': 0, 'roadBuilding': 0, 'monument': 0 }, "
   			   				  + "'newDevCards': { 'yearOfPlenty': 0, 'monopoly': 0, 'soldier': 0, 'roadBuilding': 0, 'monument': 0 }, "
   			   				  + "'roads': 13, "
   			   				  + "'cities': 4, "
   			   				  + "'settlements': 3, "
   			   				  + "'soldiers': 0, "
   			   				  + "'victoryPoints': 2, "
   			   				  + "'monuments': 0, "
   			   				  + "'playedDevCard': false, "
   			   				  + "'discarded': false, "
   			   				  + "'playerID': 11, "
   			   				  + "'playerIndex': 3, "
   			   				  + "'name': 'Mark', "
   			   				  + "'color': 'green' } ], "
   			   				  
				   + "'log': { 'lines': [ { 'source': 'Sam', 'message': 'Sam built a road' }, "
				   					   + "{ 'source': 'Sam', 'message': 'Sam built a settlement' }, "
				   					   + "{ 'source': 'Sam', 'message': 'Sams turn just ended' }, "
				   					   + "{ 'source': 'Brooke', 'message': 'Brooke built a road' }, "
				   					   + "{ 'source': 'Brooke', 'message': 'Brooke built a settlement' }, "
				   					   + "{ 'source': 'Brooke', 'message': 'Brookes turn just ended' }, "
				   					   + "{ 'source': 'Pete', 'message': 'Pete built a road' }, "
				   					   + "{ 'source': 'Pete', 'message': 'Pete built a settlement' }, "
				   					   + "{ 'source': 'Pete', 'message': 'Petes turn just ended' }, "
				   					   + "{ 'source': 'Mark', 'message': 'Mark built a road' }, "
				   					   + "{ 'source': 'Mark', 'message': 'Mark built a settlement' }, "
				   					   + "{ 'source': 'Mark', 'message': 'Marks turn just ended' }, "
				   					   + "{ 'source': 'Mark', 'message': 'Mark built a road' }, "
				   					   + "{ 'source': 'Mark', 'message': 'Mark built a settlement' }, "
				   					   + "{ 'source': 'Mark', 'message': 'Marks turn just ended' }, "
				   					   + "{ 'source': 'Pete', 'message': 'Pete built a road' }, "
				   					   + "{ 'source': 'Pete', 'message': 'Pete built a settlement' }, "
				   					   + "{ 'source': 'Pete', 'message': 'Petes turn just ended' }, "
				   					   + "{ 'source': 'Brooke', 'message': 'Brooke built a road' }, "
				   					   + "{ 'source': 'Brooke', 'message': 'Brooke built a settlement' }, "
				   					   + "{ 'source': 'Brooke', 'message': 'Brookes turn just ended' }, "
				   					   + "{ 'source': 'Sam', 'message': 'Sam built a road' }, "
				   					   + "{ 'source': 'Sam', 'message': 'Sam built a settlement' }, "
				   					   + "{ 'source': 'Sam', 'message': 'Sams turn just ended' } ] }, "
				   					   
				   + "'chat': { 'lines': [] }, "
				   
				   + "'bank': { 'brick': 23, 'wood': 21, 'sheep': 20, 'wheat': 22, 'ore': 22 }, "
				   
				   + "'turnTracker': { 'status': 'Rolling', 'currentTurn': 0, 'longestRoad': -1, 'largestArmy': -1 }, "
				   
				   + "'winner': -1, "
				   
				   + "'version': 0 }";
}
//
//private IProxy proxy;
//private String illegalMove = "CommandError: A syntatically correct command was an illegal move: class java.lang.NullPointerException";
//private String illegalMove1 = "CommandError: A syntatically correct command was an illegal move: class java.lang.ArrayIndexOutOfBoundsException";
//
//@Before
//public void setUp() throws Exception {
//	proxy = new DummyProxy();
//}
//
//@Test
//public void testUserOperations() {
//	//TEST LOGIN ENDPOINT (works)
//	User user1 = new User("Sam","sam");
//	assertEquals("Success",proxy.postUserLogin(user1).getJson());
//	
//	//TEST REGISTER ENDPOINT (works)
//	User user3 = new User("Scott","scottiscool");
//	assertEquals("Success",proxy.postUserRegister(user3).getJson());
//}
//
//@Test
//public void testGamesOperations() {
//	//TEST GET GAMES LIST (Cameron??)
//	//assertEquals(200,proxy.getGamesList().getResponseCode());
//	
//	//TEST CREATE GAME (works)
//	CreateGame createGame = new CreateGame(false,false,false,"MINE");
//	assertEquals(200,proxy.postGamesCreate(createGame).getResponseCode());
//	
//	//TEST JOIN GAME (works)
//	ServerJoinGame serverJoinGame = new ServerJoinGame(3,"orange");
//	assertEquals("Success",proxy.postGamesJoin(serverJoinGame).getJson());
//}
//
//@Test
//public void testGameOperations() {
//	//TEST GET MODEL (Cameron???)
//	//assertEquals(200,proxy.getGameModel().getResponseCode());
//
//	//TEST RESET GAME
//	assertEquals(200,proxy.postGameReset().getResponseCode());
//}
//
//@Test
//public void testMovesOperations() {
//
//	//TEST SEND CHAT 
//	ServerChat serverChat = new ServerChat("sendChat",0,"Hi everyone!");
//	assertEquals(200,proxy.movesSendChat(serverChat).getResponseCode());
//	
//	//TEST ROLL NUMBER (illegal move)
//	ServerRoll serverRoll = new ServerRoll("rollNumber",0,5);
//	assertEquals(illegalMove,proxy.movesRollNumber(serverRoll).getJson());
//	
//	//TEST ROB PLAYER (illegal move)
//	HexLocation location = new HexLocation(0,0);
//	ServerRobPlayer serverRob = new ServerRobPlayer("robPlayer",0,1,location);
//	assertEquals(illegalMove,proxy.moveRobPlayer(serverRob).getJson());
//	
//	//TEST FINISHED TURN (illegal move)
//	FinishedTurn ft = new FinishedTurn("finishTurn",0);
//	assertEquals(illegalMove,proxy.movesFinishTurn(ft).getJson());
//	
//	//TEST BUY DEV CARD (works)
//	BuyDevCard bdc = new BuyDevCard("buyDevCard",0);
//	assertEquals(200,proxy.movesBuyDevCard(bdc).getResponseCode());
//	
//	//TEST YEAR OF PLENTY (illegal move)
//	ServerYearofPlenty yop = new ServerYearofPlenty("Year_of_Plenty",0,"resource1","resource2");
//	assertEquals(illegalMove,proxy.movesYear_of_Plenty(yop).getJson());
//	
//	//TEST ROAD BUILDING (illegal move)
//	Spot spot1 = new Spot(0,0,"sw");
//	Spot spot2 = new Spot(1,1,"e");
//	RoadBuilding rb = new RoadBuilding("Road_Building",0,spot1,spot2);
//	assertEquals(illegalMove,proxy.movesRoad_Building(rb).getJson());
//	
//	//TEST SOLDIER (illegal move)
//	HexLocation location1 = new HexLocation(0,0);
//	ServerSoldier ss = new ServerSoldier("Soldier",0,1,location1);
//	assertEquals(illegalMove,proxy.movesSoldier(ss).getJson());
//	
//	//TEST MONOPOLY (illegal move)
//	ServerMonopoly sm = new ServerMonopoly("Monopoly","wood",0);
//	assertEquals(illegalMove,proxy.movesMonopoly(sm).getJson());
//	
//	//TEST MONUMENT (works)
//	ServerMonument sm1 = new ServerMonument("Monument",0);
//	assertEquals(200,proxy.movesMonument(sm1).getResponseCode());
//	
//	//TEST BUILD ROAD (illegal move)
//	HexLocation location2 = new HexLocation(0,0);
//	EdgeLocation el = new EdgeLocation(location2,EdgeDirection.North);
//	ServerBuildRoad br = new ServerBuildRoad("buildRoad",0,el,false);
//	assertEquals(illegalMove,proxy.movesBuildRoad(br).getJson());
//	
//	//TEST BUILD SETTLEMENT (illegal move)
//	HexLocation location3 = new HexLocation(0,0);
//	VertexLocation vl = new VertexLocation(location3,VertexDirection.East);
//	ServerBuildSettlement bs = new ServerBuildSettlement("buildSettlement",0,vl,false);
//	assertEquals(illegalMove,proxy.movesBuildSettlement(bs).getJson());
//	
//	//TEST BUILD CITY
//	HexLocation location4 = new HexLocation(0,0);
//	VertexLocation vl1 = new VertexLocation(location4,VertexDirection.East);
//	ServerBuildCity bc = new ServerBuildCity("buildCity",0,vl1);
//	assertEquals(illegalMove,proxy.movesBuildCity(bc).getJson());
//	
//	//TEST OFFER TRADE
//	
//	//TEST ACCEPT TRADE (illegal move)
//	AcceptTrade at = new AcceptTrade("acceptTrade",0, true);
//	assertEquals(illegalMove1,proxy.movesAcceptTrade(at).getJson());
//	
//	//TEST MARITIME TRADE (illegal move)
//	MaritimeTradeOff mt = new MaritimeTradeOff("maritimeTrade",0,3,"wood","wool");
//	assertEquals(illegalMove,proxy.movesMaritimeTrade(mt).getJson());
//	
//	//TEST DISCARD CARDS
//	//To Dan:
//	//	create discardCards object and compare it to the ServerResponse object handed back
//}
//
//@Test
//public void testUtilOperations() {
//	//TEST LOG LEVEL CHANGE (works)
//	ServerLogLevel sll = new ServerLogLevel("ALL");
//	assertEquals("Success",proxy.utilChangeLogLevel(sll).getJson());
//
//	
//}
