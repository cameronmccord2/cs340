package client.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import client.models.Proxy;
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
	private String illegalMove = "CommandError: A syntatically correct command was an illegal move: class java.lang.NullPointerException";
	private String illegalMove1 = "CommandError: A syntatically correct command was an illegal move: class java.lang.ArrayIndexOutOfBoundsException";

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
	
		//TEST GET GAMES LIST (Cameron??)
		//assertEquals(200,proxy.getGamesList().getResponseCode());
		
		//TEST CREATE GAME (works)
		CreateGame createGame = new CreateGame(false,false,false,"MINE");
		assertEquals(200,proxy.postGamesCreate(createGame).getResponseCode());
		
		//TEST JOIN GAME (works)
		ServerJoinGame serverJoinGame = new ServerJoinGame(3,"orange");
		assertEquals("Success",proxy.postGamesJoin(serverJoinGame).getJson());
		
		//TEST GET MODEL (Cameron???)
		//assertEquals(200,proxy.getGameModel().getResponseCode());
		
		//TEST SEND CHAT 
		ServerChat serverChat = new ServerChat("sendChat",0,"Hi everyone!");
		assertEquals(200,proxy.movesSendChat(serverChat).getResponseCode());
		
		//TEST ROLL NUMBER (illegal move)
		ServerRoll serverRoll = new ServerRoll("rollNumber",0,5);
		assertEquals(illegalMove,proxy.movesRollNumber(serverRoll).getJson());
		
		//TEST ROB PLAYER (illegal move)
		HexLocation location = new HexLocation(0,0);
		ServerRobPlayer serverRob = new ServerRobPlayer("robPlayer",0,1,location);
		assertEquals(illegalMove,proxy.moveRobPlayer(serverRob).getJson());
		
		//TEST FINISHED TURN (illegal move)
		FinishedTurn ft = new FinishedTurn("finishTurn",0);
		assertEquals(illegalMove,proxy.movesFinishTurn(ft).getJson());
		
		//TEST BUY DEV CARD (works)
		BuyDevCard bdc = new BuyDevCard("buyDevCard",0);
		assertEquals(200,proxy.movesBuyDevCard(bdc).getResponseCode());
		
		//TEST YEAR OF PLENTY (illegal move)
		ServerYearofPlenty yop = new ServerYearofPlenty("Year_of_Plenty",0,"resource1","resource2");
		assertEquals(illegalMove,proxy.movesYear_of_Plenty(yop).getJson());
		
		//TEST ROAD BUILDING (illegal move)
		Spot spot1 = new Spot(0,0,"sw");
		Spot spot2 = new Spot(1,1,"e");
		RoadBuilding rb = new RoadBuilding("Road_Building",0,spot1,spot2);
		assertEquals(illegalMove,proxy.movesRoad_Building(rb).getJson());
		
		//TEST SOLDIER (illegal move)
		HexLocation location1 = new HexLocation(0,0);
		ServerSoldier ss = new ServerSoldier("Soldier",0,1,location1);
		assertEquals(illegalMove,proxy.movesSoldier(ss).getJson());
		
		//TEST MONOPOLY (illegal move)
		ServerMonopoly sm = new ServerMonopoly("Monopoly","wood",0);
		assertEquals(illegalMove,proxy.movesMonopoly(sm).getJson());
		
		//TEST MONUMENT (works)
		ServerMonument sm1 = new ServerMonument("Monument",0);
		assertEquals(200,proxy.movesMonument(sm1).getResponseCode());
		
		//TEST BUILD ROAD (illegal move)
		HexLocation location2 = new HexLocation(0,0);
		EdgeLocation el = new EdgeLocation(location2,EdgeDirection.North);
		ServerBuildRoad br = new ServerBuildRoad("buildRoad",0,el,false);
		assertEquals(illegalMove,proxy.movesBuildRoad(br).getJson());
		
		//TEST BUILD SETTLEMENT (illegal move)
		HexLocation location3 = new HexLocation(0,0);
		VertexLocation vl = new VertexLocation(location3,VertexDirection.East);
		ServerBuildSettlement bs = new ServerBuildSettlement("buildSettlement",0,vl,false);
		assertEquals(illegalMove,proxy.movesBuildSettlement(bs).getJson());
		
		//TEST BUILD CITY
		HexLocation location4 = new HexLocation(0,0);
		VertexLocation vl1 = new VertexLocation(location4,VertexDirection.East);
		ServerBuildCity bc = new ServerBuildCity("buildCity",0,vl1);
		assertEquals(illegalMove,proxy.movesBuildCity(bc).getJson());
		
		//TEST OFFER TRADE
		
		//TEST ACCEPT TRADE (illegal move)
		AcceptTrade at = new AcceptTrade("acceptTrade",0, true);
		assertEquals(illegalMove1,proxy.movesAcceptTrade(at).getJson());
		
		//TEST MARITIME TRADE (illegal move)
		MaritimeTradeOff mt = new MaritimeTradeOff("maritimeTrade",0,3,"wood","wool");
		assertEquals(illegalMove,proxy.movesMaritimeTrade(mt).getJson());
		
		//TEST DISCARD CARDS
		//To Dan:
		//	create discardCards object and compare it to the ServerResponse object handed back
		
		//TEST LOG LEVEL CHANGE (works)
		ServerLogLevel sll = new ServerLogLevel("ALL");
		assertEquals("Success",proxy.utilChangeLogLevel(sll).getJson());
		
		//TEST RESET GAME 
		assertEquals(200,proxy.postGameReset().getResponseCode());
		
	}
}
