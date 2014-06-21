package ro.game;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ro.powergrid.resource.Resource;
import ro.powergrid.resource.ResourceType;
import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

@ManagedBean(name = "activePlayer", eager = true)
@SessionScoped
public class ActivePlayer implements Serializable {
    private static final long serialVersionUID = 1l;

	private int money;
	private Map<Resource, Integer> resourcesMap = new HashMap<Resource, Integer>();
	
	public void buyResources(ResourceType resourceType, int quantity) {
		int initialResourceAmount = 1;
		Resource resource = new Resource(initialResourceAmount, resourceType);
		
		if (resourcesMap.containsKey(resourceType))
		{
			int initialQuantity = (resourcesMap.get(resource)).intValue();
			resourcesMap.put(resource, initialQuantity + quantity);
		}
		else
		{
			resourcesMap.put(resource, quantity);
		}
	}
	
	public Map<Resource, Integer> getResources()
	{
		return resourcesMap;
	}
}
