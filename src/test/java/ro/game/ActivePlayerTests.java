package ro.game;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import ro.powergrid.resource.Resource;
import ro.powergrid.resource.ResourceType;

public class ActivePlayerTests {
	private ActivePlayer player;

    @Before
    public void setup() {
        player = new ActivePlayer();
    }


    private Resource buildResourceFor(ResourceType resourceType) {
        return new Resource(1, resourceType);
    }

    @Test
    public void buyOneResourceAddsTheBoughtQuantity()
    {
        player.buyResources(ResourceType.COAL, 1);
        Map<Resource, Integer> resources = player.getResources();

        int quantity = (resources.get(buildResourceFor(ResourceType.COAL))).intValue();

        assertEquals(1, quantity);

    }

    @Test
    public void buyTwoResourcesFromTheSameTypeWillIncreaseThatTypeAmount(){
        player.buyResources(ResourceType.COAL, 1);
        player.buyResources(ResourceType.COAL, 3);

        Map<Resource, Integer> resources = player.getResources();
        int quantity = (resources.get(buildResourceFor(ResourceType.COAL))).intValue();

        assertEquals(4, quantity);
    }
}