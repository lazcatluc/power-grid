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

import ro.powergrid.IncorrectResourceTypeException;
import ro.powergrid.InvalidPhaseActionException;
import ro.powergrid.plant.PlantStocker;
import ro.powergrid.plant.PowerPlant;
import ro.powergrid.plant.PowerPlantConfiguration;
import ro.powergrid.plant.StorageLimitExcedeedException;

/**
 *
 * @author Catalin
 */
@ManagedBean(name = "activePlants", eager = true)
@SessionScoped
public class ActivePlants implements Serializable {
    private static final long serialVersionUID = 3l;
    
    private List<PowerPlant> plants = new ArrayList<>();
    private List<ActiveResource<?>> activeResource = new ArrayList<>();
    
    @ManagedProperty(value="#{plantStocker}")
    private PlantStocker powerPlantAdministrator;
    
    @ManagedProperty(value="#{resources}")
    private ResourceTypes resourceTypes;
    
    @Inject 
    private PowerPlantConfiguration powerPlantConfiguration;
    
    public PlantStorageValidator plantStorageValidator(PowerPlant powerPlant) {
    	return new PlantStorageValidator(powerPlant, powerPlantAdministrator);
    }
    
    public void addPlant(PowerPlant plant) {
    	plants.add(plant);
    	activeResource.add(new ActiveResource<ResourceType>(
                plant.getAcceptableResourceTypes().iterator().next()));
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

	public PowerPlantConfiguration getPowerPlantConfiguration() {
		return powerPlantConfiguration;
	}

	public void setPowerPlantConfiguration(PowerPlantConfiguration powerPlantConfiguration) {
		this.powerPlantConfiguration = powerPlantConfiguration;
	}
}
