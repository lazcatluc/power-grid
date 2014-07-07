package ro.powergrid.resource;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import ro.powergrid.plant.PlantStocker;
import ro.powergrid.plant.PowerPlant;
import ro.powergrid.plant.PowerPlantAdministrator;

public class PlantStorageValidator {
	private final PowerPlant powerPlant;
	private final PlantStocker plantStocker;

	public PlantStorageValidator(PowerPlant powerPlant, PlantStocker plantStocker) {
		this.powerPlant = powerPlant;
		this.plantStocker = plantStocker;
	}
	
	public void availableResourcesValidator(FacesContext facesContext, 
	    		UIComponent uiComponent, Object params) throws ValidatorException {
    	if (!plantStocker.canStockPlant(powerPlant, Integer.parseInt(params.toString()))) {
    		throw new ValidatorException(new FacesMessage());
    	}   
    }
}
