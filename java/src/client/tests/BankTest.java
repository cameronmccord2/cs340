package client.tests;

import client.models.Bank;
import client.models.ResourceCard;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shared.definitions.ResourceType;

import static junit.framework.Assert.*;

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
    public void testEmptyBank() {

        boolean drawDevCard = bank.canDrawDevCard();
        assertFalse( "canDrawDevCard returned true in Empty bank", drawDevCard );

        boolean drawResourceCard = bank.canDrawResource(ResourceCard.BRICK);
        assertFalse("canDrawResource(BRICK) returned true in Empty bank", drawResourceCard);

        int numDevCards = bank.countDevCards();
        assertTrue("Development Card count not 0 in Empty bank", (numDevCards == 0));

        int numResCards = bank.countResourceCards(ResourceType.ORE);
        assertTrue("Resource card count not 0 in Empty bank", (numResCards == 0));
    }

    @Test
    public void testInitializedBank() {
        bank.setUpNewBank();

        boolean drawDevCard = bank.canDrawDevCard();
        assertTrue("canDrawDevCard returned false in initialized bank", drawDevCard);

        boolean drawResourceCard = bank.canDrawResource(ResourceCard.BRICK);
        assertTrue("canDrawResource returned false in initialized bank", drawResourceCard);

        int numDevCards = bank.countDevCards();
        assertEquals("Devcard count not correct for initialized bank", 22, numDevCards);

        int numResCards = bank.countResourceCards(ResourceType.ORE);
        assertEquals("Resource Card count not correct for initialized bank", 19, numResCards);

    }
	
	@After
	public void tearDown() {

	}

}
