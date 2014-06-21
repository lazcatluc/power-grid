package ro.powergrid.resource;

import java.util.*;

public class ResourceTypes {

    public List<ResourceType> list() {
        return getDefaultResourceTypes();
    }

    private List<ResourceType> getDefaultResourceTypes() {
        ResourceType[] allValues = ResourceType.values();
        List<ResourceType> defaultValues = new ArrayList<>(Arrays.asList(allValues));
        defaultValues.remove(ResourceType.NONE);
        Collections.sort(defaultValues, new ResourceTypeComparator());
        return defaultValues;
    }

    static class ResourceTypeComparator implements Comparator<ResourceType> {
        public int compare(ResourceType o1, ResourceType o2) {
            return o1.name().compareTo(o2.name());
        }
    }
}
