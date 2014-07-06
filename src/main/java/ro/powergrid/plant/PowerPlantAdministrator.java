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
	
    public void firePlant(PowerPlant plant) {
        plant.consumeResources(plant.getNumberOfNecessaryResources());
    }
    
    public void stockPlant(PowerPlant plant, int howMany, 
    		ResourceType resource) throws IncorrectResourceTypeException {
    	if (howMany > 0) {
    		if (!plant.acceptsResourceType(resource)) {
    			throw new IncorrectResourceTypeException();
    		}
    		plant.getEnergyResources().add(new Resource(howMany, resource));
    	}
    }
}
