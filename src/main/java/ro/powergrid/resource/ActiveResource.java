package ro.powergrid.resource;

import ro.powergrid.faces.IFaces;

import java.io.Serializable;

/**
 * Created by adi on 6/21/14.
 */
public class ActiveResource<T extends ResourceType> implements Serializable {
    private static final long serialVersionUID = 1l;
    private int availableResources;
    private Resource resource = Resource.NULL;
    private final T resourceType;

    public ActiveResource(T resourceType, IFaces faces) {
        this.resourceType = resourceType;
    }

    public void updatePowerPlantResources() {
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