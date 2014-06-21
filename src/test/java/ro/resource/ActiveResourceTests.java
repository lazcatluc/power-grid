package ro.resource;

import org.junit.Assert;
import org.junit.Test;
import ro.powergrid.faces.IFaces;
import ro.powergrid.resource.ActiveResource;
import ro.powergrid.resource.Resource;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by adi on 6/21/14.
 */
public class ActiveResourceTests {

    @Test
    public void whenUpdatePowerPlantResourcesWithoutSettingResourceTheActiveResourceNotSet(){
        IFaces faces = mock(IFaces.class);
        when(faces.getValue(any())).thenReturn("1");
        ActiveResource activeResource = new ActiveResource(null, faces);
        activeResource.updatePowerPlantResources();

        Resource actual = activeResource.getResource();

        assertEquals(Resource.NULL.getValue(), actual.getValue());
        assertEquals(null, actual.getResourceType());
    }
}