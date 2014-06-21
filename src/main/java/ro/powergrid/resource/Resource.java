package ro.powergrid.resource;

import java.io.Serializable;

/**
 * Created by adi on 6/21/14.
 */
public class Resource implements Serializable {
    private static final long serialVersionUID = 1l;
    public static Resource NULL = new Resource(0, ResourceType.NONE);
    private final int resourceValue;
    private final ResourceType resourceType;

    public Resource(int resourceValue,  ResourceType resourceType){
        this.resourceValue= resourceValue;
        this.resourceType = resourceType;
    }

    public int getValue() { return resourceValue; }
    public ResourceType getResourceType() { return resourceType;}

    @Override
    public String toString() {
        return resourceValue + " " + resourceType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resource resource = (Resource) o;

        if (resourceValue != resource.resourceValue) return false;
        if (resourceType != null ? !resourceType.equals(resource.resourceType) : resource.resourceType != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = resourceValue;
        result = 31 * result + (resourceType != null ? resourceType.hashCode() : 0);
        return result;
    }
}