package client.tests;

import client.data.PlayerInfo;
import client.models.CatanMap;
import client.models.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MapTest {

	private CatanMap map;

	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
        System.out.println("Testing Map");
        map = new CatanMap();
	}

    @Test
    public void testMap() {
        PlayerInfo info = new PlayerInfo();
        Player player = new Player(info);
        boolean robber = map.canMoveRobber(player);
        assertFalse( "Robber shouldn't be able to move.", robber );

    }
	
	@After
	public void tearDown() {

	}

}
