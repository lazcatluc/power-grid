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
		setMarketplace(new SortedLayeredMarketplace());
		configuration = new PowerPlantJSONConfiguration();
		getMarketplace().setPowerPlantConfiguration(configuration);
		getMarketplace().init();
	}
	
	@Test
	public void getsCheapestPlantsFirst() throws Exception {
		assertPlantsEqualTo(new Integer[]{3,4,5,6}, getMarketplace().getBuyablePlants());
	}
	
	@Test
	public void getsNextCheapestPlantsAsFuture() throws Exception {
		assertPlantsEqualTo(new Integer[]{7,8,9,10}, getMarketplace().getFuturePlants());
	}
	
	@Test
	public void whenPlantIsRemovedNextPlantTakesItsPlace() throws Exception {
		getMarketplace().removeBuyablePlant(configuration.getPlant(5));
		
		assertPlantsEqualTo(new Integer[]{3,4,6,7}, getMarketplace().getBuyablePlants());
	}
	
	@Test
	public void keepRemovingFirstPlantUntilWeRunOutKeepsPlantsInOrder() throws Exception {
		PowerPlant next = getMarketplace().getBuyablePlants().iterator().next();
		while (getMarketplace().getFuturePlants().iterator().next() != PowerPlant.NONE) {
			getMarketplace().removeBuyablePlant(next);
			next = getMarketplace().getBuyablePlants().iterator().next();

			assertTrue(new ArrayList<>(getMarketplace().getBuyablePlants())
						.get(getMarketplace().getBuyablePlants().size()-1)
						.compareTo(getMarketplace().getFuturePlants().iterator().next()) < 0);
			
		}
					
	}
	
	@Test
	public void setOfAllPlantsIsNotSorted() throws Exception {
		ArrayList<PowerPlant> sortedList = new ArrayList<>(getMarketplace().getAllPlants());
		Collections.sort(sortedList);
		ArrayList<PowerPlant> unsortedList = new ArrayList<>(getMarketplace().getAllPlants());
		
		assertNotEquals(sortedList, unsortedList);
	}
	
	public void assertPlantsEqualTo(Integer[] expectedPlants, 
			Collection<PowerPlant> plants){
		assertEquals(Arrays.asList(expectedPlants)
				.stream().map(i -> configuration.getPlant(i)).collect(Collectors.toList()),
				new ArrayList<PowerPlant>(plants));
	}

	public SortedLayeredMarketplace getMarketplace() {
		return marketplace;
	}

	public void setMarketplace(SortedLayeredMarketplace marketplace) {
		this.marketplace = marketplace;
	}
}
