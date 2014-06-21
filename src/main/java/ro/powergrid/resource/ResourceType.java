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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceType that = (ResourceType) o;

        if (!name.equals(that.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
