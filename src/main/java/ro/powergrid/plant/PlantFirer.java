package ro.powergrid.plant;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ro.powergrid.turn.Phase;

@ManagedBean(name = "plantFirer", eager = true)
@SessionScoped
public class PlantFirer extends PowerPlantAdministrator {

	private static final long serialVersionUID = 1L;

	public boolean canFirePlant(PowerPlant plant) {
    	return getTurn().getCurrentPhase()==Phase.POWER &&
    			!getTurn().hasFired(plant) &&
    			plant.canPowerCities();
    }

	public void firePlant(PowerPlant plant) throws InvalidPhaseActionException {
		Phase currentPhase = getTurn().getCurrentPhase();
		if (currentPhase != Phase.POWER) {
			throw new InvalidPhaseActionException(currentPhase.toString());
		}
		if (getTurn().hasFired(plant)) {
			throw new InvalidPhaseActionException(plant.toString());
		}
        plant.consumeResources(plant.getNumberOfNecessaryResources());
        getTurn().setFired(plant);
    }
    
}
