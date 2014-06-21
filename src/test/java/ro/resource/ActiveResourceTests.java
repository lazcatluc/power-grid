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

    @Test
    public void whenUpdatePowerPlantResources_ItsResourceIsInherited(){
        ResourceType initialResourceType = ResourceType.COAL;
        int initialResourceAmount = 13;
        ActiveResource activeResource = createActiveResourceWith(initialResourceType, initialResourceAmount);

        activeResource.updatePowerPlantResources();

        Resource resource = activeResource.getResource();
        assertEquals(initialResourceType, resource.getResourceType());
        assertEquals(initialResourceAmount, resource.getValue());
    }

    private ActiveResource createActiveResourceWith(ResourceType initialResourceType, int initianResourceAmount) {
        ActiveResource activeResource = new ActiveResource(initialResourceType);
        activeResource.setAvailableResources(initianResourceAmount);
        return activeResource;
    }


    // TODO: Add a test that the resource type gets inherited
}  // TODO: when updating a resource with setting a resource, the new is used