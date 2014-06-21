package ro.resource;

import org.junit.Test;
import ro.powergrid.resource.ActiveResource;
import ro.powergrid.resource.Resource;
import ro.powergrid.resource.ResourceType;

import static org.junit.Assert.*;
import ro.powergrid.resource.ResourceType;

public class ActiveResourceTests {

    private ActiveResource createActiveResource() {
        ResourceType notImportant = ResourceType.COAL;
        return new ActiveResource(notImportant);
    }

    private void assertNotSet(Resource resource) {
        assertEquals(Resource.NULL.getValue(), resource.getValue());
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