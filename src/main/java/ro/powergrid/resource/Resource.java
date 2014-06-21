package ro.powergrid.resource;

import java.io.Serializable;

/**
 * Created by adi on 6/21/14.
 */
public class Resource implements Serializable {
    private static final long serialVersionUID = 1l;
    public static Resource NULL = new Resource(0, ResourceType.NULL);
    private int resourceValue;
    private ResourceType resourceType;

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
    
    
}