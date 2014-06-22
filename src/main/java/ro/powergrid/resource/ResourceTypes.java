package ro.powergrid.resource;

import ro.powergrid.plant.PowerPlant;

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

    public List<ResourceType> getFilteredResourceTypes(PowerPlant powerPlant){
        ArrayList<ResourceType> filteredResourceTypes = new ArrayList<ResourceType>();
        filteredResourceTypes.addAll(getDefaultResourceTypes());
        filteredResourceTypes.retainAll(powerPlant.getAcceptableResourceTypes());
        return filteredResourceTypes;
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