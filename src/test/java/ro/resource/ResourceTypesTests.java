package ro.resource;

import org.junit.Assert;
import org.junit.Test;
import ro.powergrid.resource.ResourceType;
import ro.powergrid.resource.ResourceTypes;

import java.util.Arrays;
import java.util.List;

public class ResourceTypesTests {

    @Test
    public void shouldListAllAvailableResourcesSortedByName() {
        List<ResourceType> types = new ResourceTypes().list();
        Assert.assertEquals(Arrays.asList(
                ResourceType.COAL,
                ResourceType.GARBAGE,
                ResourceType.OIL,
                ResourceType.URANIUM
        ), types);
    }
}
