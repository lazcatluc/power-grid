package ro.powergrid.plant;

import java.util.Collection;

public interface PowerPlantConfiguration {

	Collection<PowerPlant> getPlants();
	PowerPlant getPlant(Integer basePrice);

}