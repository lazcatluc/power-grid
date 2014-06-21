package ro.powergrid.resource;

import java.io.Serializable;

/**
 * Created by adi on 6/21/14.
 */
public class ResourceType implements Serializable {
    private static final long serialVersionUID = 1l;
    public static ResourceType NULL = new ResourceType("null");
    private String name;
    public ResourceType(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
    
    
}
