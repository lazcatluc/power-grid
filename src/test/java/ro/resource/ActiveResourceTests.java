package ro.resource;

import org.junit.Test;
import ro.powergrid.resource.ActiveResource;
import ro.powergrid.resource.Resource;
import ro.powergrid.resource.ResourceType;

import static org.junit.Assert.*;
import ro.powergrid.resource.ResourceType;

/**
 * Created by adi on 6/21/14.
 */
public class ActiveResourceTests {

    private void assertNotSet(Resource resource) {
        assertEquals(Resource.NULL.getValue(), resource.getValue());
    }

    private ActiveResource createActiveResource() {
        ResourceType notImportant = ResourceType.COAL;
        return new ActiveResource(notImportant);
    }

    @Test
    public void whenUpdatePowerPlantResources_WithoutSettingResource_TheActiveResourceIsNotSet(){
        ActiveResource activeResource = createActiveResource();

        activeResource.updatePowerPlantResources();

        Resource resource = activeResource.getResource();
        assertNotSet(resource);
    }

    // TODO: Add a test that the resource type gets inherited
}