package ro.powergrid.resource;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by adi on 6/21/14.
 */
@ManagedBean(name = "activeResource", eager = true)
@SessionScoped
public class ActiveResource implements Serializable {
    private static final long serialVersionUID = 1l;
    private int availableResources;
    private Resource resource = Resource.NULL;
    
    public void updatePowerPlantResources() {
        ResourceType resourceType = new ResourceType("Oil");
        setResource(new Resource(availableResources, resourceType));
    }

    /**
     * @return the availableResources
     */
    public int getAvailableResources() {
        return availableResources;
    }

    /**
     * @param availableResources the availableResources to set
     */
    public void setAvailableResources(int availableResources) {
        this.availableResources = availableResources;
    }

    /**
     * @return the resource
     */
    public Resource getResource() {
        return resource;
    }

    /**
     * @param resource the resource to set
     */
    public void setResource(Resource resource) {
        this.resource = resource;
    }
}