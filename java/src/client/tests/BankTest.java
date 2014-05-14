package client.tests;

import client.models.Bank;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BankTest {

	private Bank bank;

	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
        bank = new Bank();
	}

	@Test
	public void test() {
        boolean draw = bank.canDrawDevCard();
        assert !draw;
	}
	
	@After
	public void tearDown() {

	}

}
