package client.tests;

import org.junit.Before;
import org.junit.Test;

import client.data.PlayerInfo;
import client.models.Proxy;


public class ProxyTest {
	
	private Proxy proxy;

	@Before
	public void setUp() throws Exception {
		proxy = new Proxy();
	}

	@Test
	public void test() {
		PlayerInfo player1 = new PlayerInfo();
		proxy.postUserLogin(player1);
	}

}
