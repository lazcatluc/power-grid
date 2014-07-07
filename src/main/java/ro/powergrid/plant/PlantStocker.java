package ro.powergrid.plant;

import javax.faces.bean.ManagedBean;

import ro.powergrid.resource.Resource;
import ro.powergrid.resource.ResourceType;
import ro.powergrid.turn.Phase;

@ManagedBean(name = "plantStocker", eager = true)
public class PlantStocker extends PowerPlantAdministrator {

	private static final long serialVersionUID = 1L;

	public void stockPlant(PowerPlant plant, int howMany, ResourceType resource)
			throws IncorrectResourceTypeException,
			StorageLimitExcedeedException, InvalidPhaseActionException {
		if (getTurn().getCurrentPhase() != Phase.RESOURCES) {
			throw new InvalidPhaseActionException(getTurn().getCurrentPhase()
					.toString());
		}
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
		return getTurn().getCurrentPhase() == Phase.RESOURCES
				&& plant.getTotalResourcesStored() + howMany <= plant
						.getNumberOfNecessaryResources() * PLANT_STORAGE_FACTOR;

	}
}
