package ro.powergrid.resource;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.Map;
import ro.powergrid.faces.IFaces;

/**
 * Created by adi on 6/21/14.
 */
@ManagedBean(name = "activeResource", eager = true)
public class ActiveResource {
    private IFaces faces;
    public ActiveResource(IFaces faces){
        this.faces = faces;
    }

    public String updatePowerPlantResources(){
        String valueAsString = faces.getValue("availableResources");
        int resourceValue = Integer.parseInt(valueAsString);
        ResourceType resourceType = new ResourceType("Oil");
        Resource resource = new Resource(resourceValue, resourceType);

        return "index";
    }
}