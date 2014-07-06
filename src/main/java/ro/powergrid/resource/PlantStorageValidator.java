package ro.powergrid.resource;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import ro.powergrid.plant.PowerPlant;
import ro.powergrid.plant.PowerPlantAdministrator;

public class PlantStorageValidator {
	private final PowerPlant powerPlant;
	private final PowerPlantAdministrator powerPlantAdministrator;

	public PlantStorageValidator(PowerPlant powerPlant, PowerPlantAdministrator powerPlantAdministrator) {
		this.powerPlant = powerPlant;
		this.powerPlantAdministrator = powerPlantAdministrator;
	}
	
	public void availableResourcesValidator(FacesContext facesContext, 
	    		UIComponent uiComponent, Object params) throws ValidatorException {
    	if (!powerPlantAdministrator.canStockPlant(powerPlant, Integer.parseInt(params.toString()))) {
    		throw new ValidatorException(new FacesMessage());
    	}   
    }
}
