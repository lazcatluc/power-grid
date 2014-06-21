package ro.resource;

import org.junit.Test;
import ro.powergrid.resource.ActiveResource;
import ro.powergrid.resource.Resource;

import static org.junit.Assert.*;
import ro.powergrid.resource.ResourceType;

/**
 * Created by adi on 6/21/14.
 */
public class ActiveResourceTests {

    @Test
    public void whenUpdatePowerPlantResourcesWithoutSettingResourceTheActiveResourceNotSet() {
        ActiveResource activeResource = new ActiveResource(ResourceType.NONE);
        activeResource.updatePowerPlantResources();

        Resource actual = activeResource.getResource();

        assertEquals(Resource.NULL.getValue(), actual.getValue());
        assertEquals(ResourceType.NONE, actual.getResourceType());
    }
}