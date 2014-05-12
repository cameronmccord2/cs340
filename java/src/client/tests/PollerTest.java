package client.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import client.models.Poller;

/**
 * @author scottdaly
 *
 */
public class PollerTest {
	
	private Poller poller;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		poller = new Poller();
	}

	@Test
	public void test() {
		poller.startTimer();
	}
	
	@After
	public void tearDown() throws Exception {
		poller.endTImer();
	}

}
