package ro.powergrid.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ro.powergrid.plant.PlantFirer;
import ro.powergrid.plant.PlantStocker;
import ro.powergrid.plant.PowerPlant;
import ro.powergrid.resource.ActivePlants;
import ro.powergrid.resource.ResourceType;
import ro.powergrid.resource.ResourceTypes;
import ro.powergrid.turn.TurnProvider;

public class StockFireTurnTest {
	private PlantStocker plantStocker;
	private PlantFirer plantFirer;
	private TurnProvider turnProvider;
	private ActivePlants activePlants;
	private ResourceTypes resourceTypes;
	
	@Before
	public void setUp() {
		turnProvider = new TurnProvider();
		plantFirer = new PlantFirer();
		plantStocker = new PlantStocker();
		plantFirer.setTurnProvider(turnProvider);
		plantStocker.setTurnProvider(turnProvider);
		activePlants = new ActivePlants();
		activePlants.setPowerPlantAdministrator(plantStocker);
		resourceTypes = new ResourceTypes();
		activePlants.setResourceTypes(resourceTypes);
	}
	
	protected void selectResourceType(PowerPlant plant, int position) {
		resourceTypes.setChosenResourceType(
				resourceTypes.getFilteredResourceTypes(plant).get(position));
	}
	
	protected void stockPlant(int position, int howMany) throws Exception {
		stockPlant(position, howMany, 0);
	}
	
	protected void stockPlant(int position, int howMany, int resourcePosition) throws Exception {
		PowerPlant plant = activePlants.getPlants().get(position);
		activePlants.getResource(position).setAvailableResources(howMany);
		selectResourceType(plant, resourcePosition);
		
		activePlants.plantStorageValidator(plant).availableResourcesValidator(
				null, null, String.valueOf(howMany));
		
		activePlants.updatePowerPlantResources(position);
	}
	
	protected void firePlant(int position) throws Exception {
		plantFirer.firePlant(activePlants.getPlants().get(position));
	}
	
	@Test
	public void stockFireFirstPlant() throws Exception {
		PowerPlant plant = activePlants.getPlants().get(0);		
		stockPlant(0, 2);
		stockPlant(0, 2);
		assertFalse(plantStocker.canStockPlant(plant));
		
		turnProvider.nextPhase();
		firePlant(0);
		assertFalse(plantFirer.canFirePlant(plant));
		assertFalse(plantStocker.canStockPlant(plant));
		
		turnProvider.nextTurn();
		assertFalse(plantFirer.canFirePlant(plant));
		assertTrue(plantStocker.canStockPlant(plant));
		turnProvider.nextPhase();
		
		assertTrue(plantFirer.canFirePlant(plant));
		firePlant(0);
	}
	
	@Test
	public void stockFireThirdPlantWithMultipleResourceTypes() throws Exception {
		PowerPlant plant = activePlants.getPlants().get(2);
		stockPlant(2, 1);
		assertEquals(ResourceType.COAL, resourceTypes.getChosenResourceType());
		stockPlant(2, 1, 1);
		assertEquals(ResourceType.OIL, resourceTypes.getChosenResourceType());
		assertFalse(plantFirer.canFirePlant(plant));
		assertEquals(2, plant.getEnergyResources().size());
		
		turnProvider.nextPhase();
		firePlant(2);
		
		assertTrue(plant.getEnergyResources().isEmpty());
	}
}
