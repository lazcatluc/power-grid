package ro.game;

import ro.powergrid.resource.Resource;
import ro.powergrid.resource.ResourceType;

public class Player {

	private int money;

	public boolean canBuyResources() {
		int resourceToBuyQuantity = 0;
		int resourceInitialValue = 1;
		
		Resource resource = new Resource(resourceInitialValue, ResourceType.COAL);

		if (resource.getValue() * resourceToBuyQuantity <= money)
		{
			return true;
		}
		
		return false;
	}
}
