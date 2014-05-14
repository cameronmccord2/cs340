package client.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import client.server.CreateGame;
import client.server.ServerJoinGame;
import client.models.Proxy;
import client.server.User;


public class ProxyTest {
	
	private Proxy proxy;

	@Before
	public void setUp() throws Exception {
		proxy = new Proxy();
	}

	@Test
	public void test() {
		//TEST LOGIN ENDPOINT
		User user1 = new User("Sam","sam");
		assertEquals("Success",proxy.postUserLogin(user1));
//		User user2 = new User("nonsense","nonsense");
//		assertEquals("Failed to login - bad username or password.",proxy.postUserLogin(user2));
//		
//		
//		//TEST REGISTER ENDPOINT
//		User user3 = new User("Scott","scottiscool");
//		assertEquals("Success",proxy.postUserRegister(user3));
//		User user4 = new User("Scott","scottiscool");
//		assertEquals("Failed to register - someone already has that username.",proxy.postUserRegister(user4));
	
		//TEST GET GAMES LIST
		//assertNull(proxy.getGamesList());
		
		//TEST CREATE GAME
//		CreateGame createGame = new CreateGame(false,false,false,"MINE");
//		assertNull(proxy.postGamesCreate(createGame));
		
		//TEST JOIN GAME
		ServerJoinGame serverJoinGame = new ServerJoinGame(0,"orange");
		assertEquals("Success",proxy.postGamesJoin(serverJoinGame));
	}

}
