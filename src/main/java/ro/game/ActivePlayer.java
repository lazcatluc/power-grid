package ro.game;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ro.powergrid.resource.Resource;
import ro.powergrid.resource.ResourceType;

@ManagedBean(name = "activePlayer", eager = true)
@SessionScoped
public class ActivePlayer implements Serializable {
    private static final long serialVersionUID = 1l;

	private int money;
	private Map<Resource, Integer> resourcesMap = new HashMap<Resource, Integer>();
	
	private ResourceType resourceToBuyType;
	private int resourceToBuyQuantity;
	
	public void buyResources(ResourceType resourceToBuyType, int resourceToBuyQuantity) {
		int initialResourceAmount = 1;
		Resource resource = new Resource(initialResourceAmount, resourceToBuyType);
		
		if (resourcesMap.containsKey(resource))
		{
            Integer resourceAmount = resourcesMap.get(resource);
            int initialQuantity = resourceAmount.intValue();
			resourcesMap.put(resource, initialQuantity + resourceToBuyQuantity);
		}
		else
		{
			resourcesMap.put(resource, resourceToBuyQuantity);
		}
	}
	
	public Map<Resource, Integer> getResources()
	{
		return resourcesMap;
	}

    public int getResourcesOfType(ResourceType resourceType){
        return (getResources().get(new Resource(1, resourceType))).intValue();
    }
	
	public void setResourceToBuyType(ResourceType resourceType)
	{
		resourceToBuyType = resourceType;
	}
	
	public ResourceType getResourceToBuyType() {
		return resourceToBuyType;
	}

	public int getResourceToBuyQuantity() {
		return resourceToBuyQuantity;
	}

	public void setResourceToBuyQuantity(int quantity)
	{
		resourceToBuyQuantity = quantity;
	}
}
