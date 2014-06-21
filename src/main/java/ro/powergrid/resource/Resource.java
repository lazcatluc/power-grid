package ro.powergrid.resource;

/**
 * Created by adi on 6/21/14.
 */
public class Resource {
    private int resourceValue;
    private ResourceType resourceType;

    public Resource(int resourceValue,  ResourceType resourceType){
        this.resourceValue= resourceValue;
        this.resourceType = resourceType;
    }

    public int getValue() { return resourceValue; }
    public ResourceType getResourceType() { return resourceType;}
}