package ro.powergrid.resource;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.*;

@ManagedBean(name = "resources", eager = true)
@SessionScoped
public class ResourceTypes implements Serializable {

    private ResourceType chosenResourceType;

    public List<ResourceType> list() {
        return getDefaultResourceTypes();
    }

    public List<ResourceType> getDefaultResourceTypes() {
        ResourceType[] allValues = ResourceType.values();
        List<ResourceType> defaultValues = new ArrayList<>(Arrays.asList(allValues));
        defaultValues.remove(ResourceType.NONE);
        Collections.sort(defaultValues, new ResourceTypeComparator());
        return defaultValues;
    }

    public ResourceType getChosenResourceType() {
        return chosenResourceType;
    }

    public void setChosenResourceType(ResourceType chosenResourceType) {
        this.chosenResourceType = chosenResourceType;
    }

    static class ResourceTypeComparator implements Comparator<ResourceType> {
        public int compare(ResourceType o1, ResourceType o2) {
            return o1.name().compareTo(o2.name());
        }
    }
}