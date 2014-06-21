package ro.resource;

import org.junit.Assert;
import org.junit.Test;
import ro.powergrid.resource.Resource;
import ro.powergrid.resource.ResourceType;

public class ResourceTests {
    @Test
    public void whenDisplayedItShowsItsState(){
        Resource resource = new Resource(13, new ResourceType("Oil"));

        Assert.assertEquals("13 Oil", resource.toString());
    }
}
