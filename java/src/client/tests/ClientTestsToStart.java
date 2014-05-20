package client.tests;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import client.models.Proxy;
import client.server.ServerJoinGame;
import client.server.User;

public class ClientTestsToStart {

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
		
		//TEST JOIN GAME
		ServerJoinGame serverJoinGame = new ServerJoinGame(0,"orange");
		assertEquals("Success",proxy.postGamesJoin(serverJoinGame));	
	}
}
