/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ro.powergrid.resource;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import ro.powergrid.plant.IncorrectResourceTypeException;
import ro.powergrid.plant.InvalidPhaseActionException;
import ro.powergrid.plant.PlantStocker;
import ro.powergrid.plant.PowerPlant;
import ro.powergrid.plant.PowerPlantAdministrator;
import ro.powergrid.plant.PowerPlantBuilder;
import ro.powergrid.plant.StorageLimitExcedeedException;

/**
 *
 * @author Catalin
 */
@ManagedBean(name = "activePlants", eager = true)
@SessionScoped
public class ActivePlants implements Serializable {
    private static final long serialVersionUID = 2l;
    
    private List<PowerPlant> plants = new ArrayList<>();
    private List<ActiveResource<?>> activeResource = new ArrayList<>();
    
    @Inject
    private PlantStocker powerPlantAdministrator;
    
    @ManagedProperty(value="#{resources}")
    private ResourceTypes resourceTypes;
    
    public PlantStorageValidator plantStorageValidator(PowerPlant powerPlant) {
    	return new PlantStorageValidator(powerPlant, powerPlantAdministrator);
    }
    
    public ActivePlants() {
        plants.add(PowerPlantBuilder.three()); 
        plants.add(PowerPlantBuilder.four());
        plants.add(PowerPlantBuilder.five());
        for (PowerPlant plant : plants) {
            activeResource.add(new ActiveResource<ResourceType>(
                plant.getAcceptableResourceTypes().iterator().next()));
        };
    }
    
    public ActiveResource<?> getResource(int position) {
        return activeResource.get(position);
    }
    
    public void updatePowerPlantResources(int position) throws IncorrectResourceTypeException, 
    		StorageLimitExcedeedException, InvalidPhaseActionException {
        ActiveResource<?> resource = getResource(position);
		resource.updatePowerPlantResources();
        PowerPlant powerPlant = getPlants().get(position);
		powerPlantAdministrator.stockPlant(
				powerPlant, resource.getAvailableResources(), 
				resourceTypes.getChosenResourceType());
    }

    /**
     * @return the activeResource
     */
    public List<ActiveResource<?>> getActiveResource() {
        return activeResource;
    }

    /**
     * @param activeResource the activeResource to set
     */
    public void setActiveResource(List<ActiveResource<?>> activeResource) {
        this.activeResource = activeResource;
    }

    /**
     * @return the plants
     */
    public List<PowerPlant> getPlants() {
        return plants;
    }

	public PlantStocker getPowerPlantAdministrator() {
		return powerPlantAdministrator;
	}

	public void setPowerPlantAdministrator(PlantStocker powerPlantAdministrator) {
		this.powerPlantAdministrator = powerPlantAdministrator;
	}

	public ResourceTypes getResourceTypes() {
		return resourceTypes;
	}

	public void setResourceTypes(ResourceTypes resourceTypes) {
		this.resourceTypes = resourceTypes;
	}
}
