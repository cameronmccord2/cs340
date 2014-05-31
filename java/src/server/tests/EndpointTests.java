package server.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import client.models.Proxy;
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
		System.out.println("Login: " + proxy.postUserRegister(new User("Sam","sam")).getResponseCode());
		//skip for now until we have cookie
		//assertEquals(200,proxy.postUserLogin(new User("Sam","sam")).getResponseCode());
		
		//TEST REGISTER ENDPOINT
		System.out.println("Register: " + proxy.postUserRegister(new User("Scott","scottiscool")).getResponseCode());
		assertEquals(200,proxy.postUserRegister(new User("Scott","scottiscool")).getResponseCode());
				
		//TEST GET GAMES ENDPOINT
		//assertEquals(3, proxy.getGames().size());
		
	}
	
	
}
