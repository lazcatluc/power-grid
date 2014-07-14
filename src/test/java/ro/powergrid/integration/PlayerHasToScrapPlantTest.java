package ro.powergrid.integration;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ro.powergrid.plant.PowerPlant;
import ro.powergrid.plant.PowerPlantJSONConfiguration;
import ro.powergrid.resource.ActivePlants;
import ro.powergrid.turn.ScrapPlantTurn;
import ro.powergrid.turn.TurnImpl;

public class PlayerHasToScrapPlantTest {
	private PlayerBuysPlantFromMarketTest playerBuysPlantFromMarketTest;

	@Before
	public void setUp() throws Exception {
		playerBuysPlantFromMarketTest = new PlayerBuysPlantFromMarketTest();
		playerBuysPlantFromMarketTest.setUp();
		for (int i = 0; i < 3; i++) {
			playerBuysPlantFromMarketTest.buyFirstPlant();
			playerBuysPlantFromMarketTest.getTurnProvider().nextTurn();
		}

		playerBuysPlantFromMarketTest.buyFirstPlant();
	}

	@Test
	public void hasToScrapOne() throws Exception {
		assertTrue(playerBuysPlantFromMarketTest.getTurnProvider().getTurn() instanceof ScrapPlantTurn);
	}
	
	@Test
	public void plantSixWaitingForScrap() throws Exception {
		assertEquals(new PowerPlantJSONConfiguration().getPlant(6),
				getActivePlants().getWaitingForScrap());
	}

	public ActivePlants getActivePlants() {
		return playerBuysPlantFromMarketTest.getActivePlants();
	}
	
	@Test
	public void scrapPlantReturnsToNormalTurn() throws Exception {
		getActivePlants().scrapPlant(0);
		
		assertTrue(playerBuysPlantFromMarketTest.getTurnProvider().getTurn() instanceof TurnImpl);
		
	}
	
	@Test
	public void scrapPlantRemovesPlantFromScrapWaitingList() throws Exception {
		getActivePlants().scrapPlant(0);
		
		assertEquals(PowerPlant.NONE, getActivePlants().getWaitingForScrap());
	}
	
	@Test
	public void scrapPlantIsNoLongerWaitingForScrap() throws Exception {
		getActivePlants().scrapPlant(0);
		
		assertFalse(getActivePlants().isPlantWaitingForScrap());
	}
}
