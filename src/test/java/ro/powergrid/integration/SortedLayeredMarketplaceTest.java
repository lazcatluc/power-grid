package ro.powergrid.integration;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import ro.powergrid.buy.SortedLayeredMarketplace;
import ro.powergrid.plant.PowerPlant;
import ro.powergrid.plant.PowerPlantConfiguration;
import ro.powergrid.plant.PowerPlantJSONConfiguration;

public class SortedLayeredMarketplaceTest {
	private SortedLayeredMarketplace marketplace;
	private PowerPlantConfiguration configuration;
	
	@Before
	public void setUp() {
		marketplace = new SortedLayeredMarketplace();
		configuration = new PowerPlantJSONConfiguration();
		marketplace.setPowerPlantConfiguration(configuration);
		marketplace.init();
	}
	
	@Test
	public void getsCheapestPlantsFirst() throws Exception {
		assertPlantsEqualTo(new Integer[]{3,4,5,6}, marketplace.getBuyablePlants());
	}
	
	@Test
	public void getsNextCheapestPlantsAsFuture() throws Exception {
		assertPlantsEqualTo(new Integer[]{7,8,9,10}, marketplace.getFuturePlants());
	}
	
	@Test
	public void whenPlantIsRemovedNextPlantTakesItsPlace() throws Exception {
		marketplace.removeBuyablePlant(configuration.getPlant(5));
		
		assertPlantsEqualTo(new Integer[]{3,4,6,7}, marketplace.getBuyablePlants());
	}
	
	@Test
	public void keepRemovingFirstPlantUntilWeRunOutKeepsPlantsInOrder() throws Exception {
		PowerPlant next = marketplace.getBuyablePlants().iterator().next();
		while (marketplace.getFuturePlants().iterator().next() != PowerPlant.NONE) {
			marketplace.removeBuyablePlant(next);
			next = marketplace.getBuyablePlants().iterator().next();

			assertTrue(new ArrayList<>(marketplace.getBuyablePlants())
						.get(marketplace.getBuyablePlants().size()-1)
						.compareTo(marketplace.getFuturePlants().iterator().next()) < 0);
			
		}
					
	}
	
	@Test
	public void setOfAllPlantsIsNotSorted() throws Exception {
		ArrayList<PowerPlant> sortedList = new ArrayList<>(marketplace.getAllPlants());
		Collections.sort(sortedList);
		ArrayList<PowerPlant> unsortedList = new ArrayList<>(marketplace.getAllPlants());
		
		assertNotEquals(sortedList, unsortedList);
	}
	
	protected void assertPlantsEqualTo(Integer[] expectedPlants, 
			Collection<PowerPlant> plants){
		assertEquals(Arrays.asList(expectedPlants)
				.stream().map(i -> configuration.getPlant(i)).collect(Collectors.toList()),
				new ArrayList<PowerPlant>(plants));
	}
}
