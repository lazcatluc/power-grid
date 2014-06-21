package ro.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class BuyResourceTests {
	private Player player;
	
    @Test
    public void buyOneResourceTest()
    {
    	player = new Player();
    
    	assertTrue(player.canBuyResources());
    }
}
