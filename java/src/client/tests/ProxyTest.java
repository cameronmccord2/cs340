package client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import client.models.Proxy;
import client.server.CreateGame;
import client.server.ServerChat;
import client.server.ServerJoinGame;
import client.server.User;


public class ProxyTest {
	
	private Proxy proxy;

	@Before
	public void setUp() throws Exception {
		proxy = new Proxy();
	}

	@Test
	public void test() {
		//TEST LOGIN ENDPOINT (works)
		User user1 = new User("Sam","sam");
		assertEquals("Success",proxy.postUserLogin(user1).getJson());
		
		//TEST REGISTER ENDPOINT (works)
		User user3 = new User("Scott","scottiscool");
		assertEquals("Success",proxy.postUserRegister(user3).getJson());
	
		//TEST GET GAMES LIST
		//change the test to response code (400 or 200)
		//assertNull(proxy.getGamesList());
		
		//TEST CREATE GAME (works)
//		CreateGame createGame = new CreateGame(false,false,false,"MINE");
//		assertNull(proxy.postGamesCreate(createGame));
		
		//TEST JOIN GAME (works)
//		ServerJoinGame serverJoinGame = new ServerJoinGame(3,"orange");
//		assertEquals("Success",proxy.postGamesJoin(serverJoinGame));
		
		//TEST RESET GAME 
		//assertEquals("Success",proxy.postGameReset());
		
		////////////////////////////////////////////////////////////////////////
		//TEST SAVE GAME (don't know how to save)
//		SaveGame saveGame = new SaveGame(0,"???");
//		assertEquals("Success",proxy.postGamesSave(saveGame));
		
		//TEST LOAD GAME (don't know how to load)
		//GameLoad gameLoad = new GameLoad("??");
		//assertEquals(proxy.postGamesLoad(gameLoad));
		
		//TEST GET MODEL
		
		//TEST GET COMMANDS (no idea what this should return)
		//assertEquals("Success",proxy.getGameCommands());
		
//		//TEST ADD AI PLAYER (won't let me add a player)
//		ServerAI serverAI = new ServerAI("Comp Player");
//		assertEquals("Success",proxy.postAddAI(serverAI));
		
		//TEST LIST AI PLAYERS (works)
		//assertEquals("[\"LARGEST_ARMY\"]",proxy.getListAI());
		//	//////////////////////////////////////////////////////////////////////
		//TEST SEND CHAT 
//		ServerChat serverChat = new ServerChat("sendChat",0,"Hi everyone!");
//		assertEquals("",proxy.movesSendChat(serverChat));
		
		//TEST ROLL NUMBER (gives a null pointer exception from the server)
//		ServerRoll serverRoll = new ServerRoll("rollNumber",0,5);
//		assertEquals("",proxy.movesRollNumber(serverRoll));
		
		//TEST ROB PLAYER
//		HexLocation location = new HexLocation(0,0);
//		ServerRobPlayer serverRob = new ServerRobPlayer("robplayer",0,1,location);
//		assertEquals("",proxy.moveRobPlayer(serverRob));
		
		//TEST FINISHED TURN
//		FinishedTurn ft = new FinishedTurn("finishTurn",0);
//		assertEquals("",proxy.movesFinishTurn(ft));
		
		//TEST BUY DEV CARD
//		BuyDevCard bdc = new BuyDevCard("buyDevCard",0);
//		assertEquals("",proxy.movesBuyDevCard(bdc));
		
		//TEST YEAR OF PLENTY
//		ServerYearofPlenty yop = new ServerYearofPlenty("Year_of_Plenty",0,"resource1","resource2");
//		assertEquals("",proxy.movesYear_of_Plenty(yop));
		
		//TEST ROAD BUILDING
//		Spot spot1 = new Spot(0,0,"sw");
//		Spot spot2 = new Spot(1,1,"e");
//		RoadBuilding rb = new RoadBuilding("Road_Building",0,spot1,spot2);
//		assertEquals("",proxy.movesRoad_Building(rb));
		
		//TEST SOLDIER
//		HexLocation location = new HexLocation(0,0);
//		ServerSoldier ss = new ServerSoldier("Soldier",0,1,location);
//		assertEquals("",proxy.movesSoldier(ss));
		
		//TEST MONOPOLY
//		ServerMonopoly sm = new ServerMonopoly("Monopoly","wood",0);
//		assertEquals("",proxy.movesMonopoly(sm));
		
		//TEST MONUMENT
//		ServerMonument sm = new ServerMonument("Monument",0);
//		assertEquals("",proxy.movesMonument(sm));
		
		//TEST BUILD ROAD
//		HexLocation location = new HexLocation(0,0);
//		EdgeLocation el = new EdgeLocation(location,EdgeDirection.North);
//		ServerBuildRoad br = new ServerBuildRoad("buildRoad",0,el,false);
//		assertEquals("",proxy.movesBuildRoad(br));
		
		//TEST BUILD SETTLEMENT
//		HexLocation location = new HexLocation(0,0);
//		VertexLocation vl = new VertexLocation(location,VertexDirection.East);
//		ServerBuildSettlement bs = new ServerBuildSettlement("buildSettlement",0,vl,false);
//		assertEquals("",proxy.movesBuildSettlement(bs));
		
		//TEST BUILD CITY
//		HexLocation location = new HexLocation(0,0);
//		VertexLocation vl = new VertexLocation(location,VertexDirection.East);
//		ServerBuildCity bc = new ServerBuildCity("buildCity",0,vl);
//		assertEquals("",proxy.movesBuildCity(bc));
		
		//TEST OFFER TRADE
		
		//TEST ACCEPT TRADE
//		AcceptTrade at = new AcceptTrade("willAccept",0,true);
//		assertEquals("",proxy.movesAcceptTrade(at));
		
		//TEST MARITIME TRADE
//		MaritimeTradeOff mt = new MaritimeTradeOff("maritimeTrade",0,3,"wood","wool");
//		assertEquals("",proxy.movesMaritimeTrade(mt));
		
		//TEST DISCARD CARDS
		//To Dan:
		//	create discardCards object and compare it to the ServerResponse object handed back
		//Then the above tests just need to be un-commented and change the asser test according to the ServerResponse object
		
		//TEST LOG LEVEL CHANGE (works)
//		ServerLogLevel sll = new ServerLogLevel("WARNING");
//		assertEquals("Success",proxy.utilChangeLogLevel(sll));
		
	}
}
