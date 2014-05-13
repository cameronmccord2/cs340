package client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import client.models.CreateGame;
import client.models.Proxy;
import client.models.User;

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
		
		assertNull(proxy.postGamesJoin("{id:2,color: orange}"));
		
	}
}
