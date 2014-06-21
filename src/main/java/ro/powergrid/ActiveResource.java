package ro.powergrid;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.Map;

/**
 * Created by adi on 6/21/14.
 */
@ManagedBean(name = "activeResource", eager = true)
public class ActiveResource {
    public String updatePowerPlantResources(){
        FacesContext fc = FacesContext.getCurrentInstance();

        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String valueAsString = params.get("availableResources");
        int resourceValue = Integer.parseInt(valueAsString);
        ResourceType resourceType = new ResourceType("Oil");
        Resource resource = new Resource(resourceValue, resourceType);

        return "index";
    }
}