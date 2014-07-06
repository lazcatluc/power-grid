/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ro.powergrid.plant;

import javax.faces.bean.ManagedBean;

import ro.powergrid.resource.Resource;
import ro.powergrid.resource.ResourceType;

/**
 *
 * @author Catalin
 */
@ManagedBean(name = "plantAdministrator", eager = true)
public class PowerPlantAdministrator {
	
    public static final int PLANT_STORAGE_FACTOR = 2;

	public void firePlant(PowerPlant plant) {
        plant.consumeResources(plant.getNumberOfNecessaryResources());
    }
    
    public void stockPlant(PowerPlant plant, int howMany, 
    		ResourceType resource) throws IncorrectResourceTypeException, StorageLimitExcedeedException {
    	if (howMany > 0) {
    		if (!plant.acceptsResourceType(resource)) {
    			throw new IncorrectResourceTypeException(resource.toString());
    		}
    		if (!canStockPlant(plant, howMany)) {
    			throw new StorageLimitExcedeedException(String.valueOf(howMany));
    		}
    		plant.getEnergyResources().add(new Resource(howMany, resource));
    	}
    }
    
    public boolean canStockPlant(PowerPlant plant, int howMany) {
		return plant.getTotalResourcesStored() + howMany <= 
			plant.getNumberOfNecessaryResources()*PLANT_STORAGE_FACTOR;

    }
}
