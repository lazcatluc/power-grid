package ro.powergrid.integration;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import ro.powergrid.buy.ActivePlantMarket;
import ro.powergrid.buy.TransactionalPaymentProcessor;
import ro.powergrid.buy.TransactionalPlayerPlantBroker;
import ro.powergrid.plant.PowerPlantBuilder;
import ro.powergrid.player.ActivePlayer;
import ro.powergrid.player.Player;

public class PlayerBuysPlantFromMarketTest {
	private StockFireTurnTest activePlantsMother;
	private SortedLayeredMarketplaceTest marketplaceMother;
	private ActivePlayer activePlayer;
	private ActivePlantMarket activePlantsMarket;

	@Before	
	public void setUp() {
		activePlantsMother = new StockFireTurnTest();
		activePlantsMother.setUp();
		marketplaceMother = new SortedLayeredMarketplaceTest();
		marketplaceMother.setUp();
		activePlantsMarket = new ActivePlantMarket();
		activePlantsMarket.setPlantMarketplace(marketplaceMother.getMarketplace());
		
		activePlayer = new ActivePlayer();
		activePlayer.setActivePlants(activePlantsMother.getActivePlants());
		activePlayer.setCurrentPlayer(new Player());
		activePlayer.setActivePlantMarket(activePlantsMarket);
		TransactionalPlayerPlantBroker playerPlantBroker = 
				new TransactionalPlayerPlantBroker();
		playerPlantBroker.setPaymentProcessor(new TransactionalPaymentProcessor());
		activePlayer.setPlayerPlantBroker(playerPlantBroker);
	}

	public void buyFirstPlant() {
		activePlayer.buyPlant(activePlantsMarket.getBuyablePlants().get(0));
	}
		
	
	@Test
	public void playerBuysPlantUpdatesMarket() throws Exception {
		buyFirstPlant();
		
		marketplaceMother.assertPlantsEqualTo(new Integer[]{4,5,6,7}, 
				activePlantsMarket.getBuyablePlants());
	}

	@Test
	public void playerBuysPlantUpdatesActivePlants() throws Exception {
		buyFirstPlant();
		
		assertEquals(Collections.singletonList(PowerPlantBuilder.three()),
				activePlantsMother.getActivePlants().getPlants());
	}
	
	@Test
	public void playerBuysMorePlantsPreservesOrder() throws Exception {
		buyFirstPlant();
		
		assertEquals(Collections.singletonList(PowerPlantBuilder.three()),
				activePlantsMother.getActivePlants().getPlants());
	}
}
