package ro.powergrid.buy;

import ro.powergrid.plant.PowerPlant;
import ro.powergrid.player.PlantOwner;

public interface PlayerPlantBroker {
	void transferPlant(PlantOwner plantOwner, PowerPlant powerPlant, 
			PlantMarketplace plantMarketplace);
}