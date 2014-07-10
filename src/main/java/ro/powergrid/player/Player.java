package ro.powergrid.player;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashSet;

import ro.powergrid.plant.PowerPlant;

public class Player implements PlantOwner, Serializable {

	private static final long serialVersionUID = 5597584633027488966L;

	private final Collection<PowerPlant> plants = new LinkedHashSet<>();
	
	@Override
	public Collection<PowerPlant> getPlants() {
		return new LinkedHashSet<>(plants);
	}

	@Override
	public void add(PowerPlant plant) {
		plants.add(plant);
	}

}
