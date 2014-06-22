package ro.game;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

import ro.powergrid.resource.Resource;
import ro.powergrid.resource.ResourceType;

public class ActivePlayerTests {
	private ActivePlayer player;
	
    @Test
    public void buyOneResourceTest()
    {
    	player = new ActivePlayer();

    	Resource resource = new Resource(1, ResourceType.COAL);
    	player.buyResources(ResourceType.COAL, 1);
    	Map<Resource, Integer> resources = player.getResources();
    	
    	int quantity = (resources.get(resource)).intValue();
    	
    	assertEquals(1, quantity);
    }
}
