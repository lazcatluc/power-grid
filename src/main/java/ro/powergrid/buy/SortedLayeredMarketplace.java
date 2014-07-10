package ro.powergrid.buy;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import ro.powergrid.plant.PowerPlant;
import ro.powergrid.plant.PowerPlantConfiguration;

/**
 * 
 * @author Catalin
 *
 * Marketplace holding the plants in two sorted layers.
 * When one plant is bought, a new one is generated
 * and is added.
 * 
 */
public class SortedLayeredMarketplace implements PlantMarketplace, Serializable	 {

	private static final long serialVersionUID = 1L;
	private static final int PLANTS_PER_LAYER = 4;
	
	private final Set<PowerPlant> buyablePlants = new TreeSet<>();	
	private final Set<PowerPlant> futurePlants = new TreeSet<>();
	private final Set<PowerPlant> allPlants = new LinkedHashSet<>();
	
	@Inject
	private PowerPlantConfiguration powerPlantConfiguration;	
	
	@PostConstruct
	public void init() {
		Set<PowerPlant> allSortedPlants = new TreeSet<>
			(powerPlantConfiguration.getPlants());
		Iterator<PowerPlant> sortedPlantsIt =allSortedPlants.iterator();
		extractPlants(sortedPlantsIt, buyablePlants);
		extractPlants(sortedPlantsIt, futurePlants);
		List<PowerPlant> rest = new ArrayList<>(allSortedPlants);	
		Collections.shuffle(rest);
		allPlants.addAll(rest);
	}

	private void extractPlants(Iterator<PowerPlant> sortedPlantsIt, 
			Set<PowerPlant> toPlants) {
		for (int i = 0; i< PLANTS_PER_LAYER; i++) {
			toPlants.add(sortedPlantsIt.next());
			sortedPlantsIt.remove();
		}
	}
	
	@Override
	public Collection<PowerPlant> getBuyablePlants() {
		return new LinkedHashSet<>(buyablePlants);
	}

	@Override
	public Collection<PowerPlant> getFuturePlants() {
		return new LinkedHashSet<>(futurePlants);
	}

	@Override
	public void removeBuyablePlant(PowerPlant plant) {
		buyablePlants.remove(plant);
		PowerPlant nextPlant = getAllPlants().isEmpty()? PowerPlant.NONE : 
			allPlants.iterator().next();
		allPlants.remove(nextPlant);
		PowerPlant nextFuturePlant = futurePlants.iterator().next();
		if (nextPlant.compareTo(nextFuturePlant) < 0) {
			buyablePlants.add(nextPlant);
		}
		else {
			futurePlants.remove(nextFuturePlant);
			futurePlants.add(nextPlant);
			buyablePlants.add(nextFuturePlant);
		}
	}

	public PowerPlantConfiguration getPowerPlantConfiguration() {
		return powerPlantConfiguration;
	}

	public void setPowerPlantConfiguration(PowerPlantConfiguration powerPlantConfiguration) {
		this.powerPlantConfiguration = powerPlantConfiguration;
	}

	public Set<PowerPlant> getAllPlants() {
		return new LinkedHashSet<>(allPlants);
	}

}
