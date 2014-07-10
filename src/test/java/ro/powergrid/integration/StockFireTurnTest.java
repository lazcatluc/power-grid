package ro.powergrid.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ro.powergrid.plant.PlantFirer;
import ro.powergrid.plant.PlantStocker;
import ro.powergrid.plant.PowerPlant;
import ro.powergrid.plant.PowerPlantConfiguration;
import ro.powergrid.plant.PowerPlantJSONConfiguration;
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
	private PowerPlantConfiguration powerPlantConfiguration;
	
	@Before
	public void setUp() {
		setTurnProvider(new TurnProvider());
		plantFirer = new PlantFirer();
		plantStocker = new PlantStocker();
		plantFirer.setTurnProvider(getTurnProvider());
		plantStocker.setTurnProvider(getTurnProvider());
		setActivePlants(new ActivePlants());
		getActivePlants().setPowerPlantAdministrator(plantStocker);
		resourceTypes = new ResourceTypes();
		getActivePlants().setResourceTypes(resourceTypes);
		powerPlantConfiguration = new PowerPlantJSONConfiguration();
		getActivePlants().setPowerPlantConfiguration(powerPlantConfiguration);

	}
	
	protected void selectResourceType(PowerPlant plant, int position) {
		resourceTypes.setChosenResourceType(
				resourceTypes.getFilteredResourceTypes(plant).get(position));
	}
	
	protected void stockPlant(int position, int howMany) throws Exception {
		stockPlant(position, howMany, 0);
	}
	
	protected void stockPlant(int position, int howMany, int resourcePosition) throws Exception {
		PowerPlant plant = getActivePlants().getPlants().get(position);
		getActivePlants().getResource(position).setAvailableResources(howMany);
		selectResourceType(plant, resourcePosition);
		
		getActivePlants().plantStorageValidator(plant).availableResourcesValidator(
				null, null, String.valueOf(howMany));
		
		getActivePlants().updatePowerPlantResources(position);
	}
	
	protected void firePlant(int position) throws Exception {
		plantFirer.firePlant(getActivePlants().getPlants().get(position));
	}
	
	@Test
	public void stockFireFirstPlant() throws Exception {
		initActivePlants();
		PowerPlant plant = getActivePlants().getPlants().get(0);		
		stockPlant(0, 2);
		stockPlant(0, 2);
		assertFalse(plantStocker.canStockPlant(plant));
		
		getTurnProvider().nextPhase();
		firePlant(0);
		assertEquals(1, getTurnProvider().getTurn().getMaximumNumberOfCitiesPowered());
		assertFalse(plantFirer.canFirePlant(plant));
		assertFalse(plantStocker.canStockPlant(plant));
		
		getTurnProvider().nextTurn();
		getTurnProvider().nextPhase();
		assertFalse(plantFirer.canFirePlant(plant));
		assertTrue(plantStocker.canStockPlant(plant));
		getTurnProvider().nextPhase();
		
		assertTrue(plantFirer.canFirePlant(plant));
		firePlant(0);
	}
	
	@Test
	public void stockFireThirdPlantWithMultipleResourceTypes() throws Exception {
		initActivePlants();
		PowerPlant plant = getActivePlants().getPlants().get(2);
		stockPlant(2, 1);
		assertEquals(ResourceType.COAL, resourceTypes.getChosenResourceType());
		stockPlant(2, 1, 1);
		assertEquals(ResourceType.OIL, resourceTypes.getChosenResourceType());
		assertFalse(plantFirer.canFirePlant(plant));
		assertEquals(2, plant.getEnergyResources().size());
		
		getTurnProvider().nextPhase();
		firePlant(2);
		
		assertTrue(plant.getEnergyResources().isEmpty());
	}
	
	@Test
	public void stockTwoPlantsAndFireThemPowersTwoCities() throws Exception {
		initActivePlants();
		stockPlant(0, 2);
		stockPlant(1, 2);
		getTurnProvider().nextPhase();
		firePlant(0);
		firePlant(1);
		
		assertEquals(2, getTurnProvider().getTurn().getMaximumNumberOfCitiesPowered());
	}

	private void initActivePlants() {
		getActivePlants().addPlant(powerPlantConfiguration.getPlant(3));
		getActivePlants().addPlant(powerPlantConfiguration.getPlant(4));
		getActivePlants().addPlant(powerPlantConfiguration.getPlant(5));
		turnProvider.nextPhase();
	}

	public ActivePlants getActivePlants() {
		return activePlants;
	}

	public void setActivePlants(ActivePlants activePlants) {
		this.activePlants = activePlants;
	}

	public TurnProvider getTurnProvider() {
		return turnProvider;
	}

	public void setTurnProvider(TurnProvider turnProvider) {
		this.turnProvider = turnProvider;
	}
}
