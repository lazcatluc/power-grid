package ro.powergrid.integration;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import ro.powergrid.InvalidPhaseActionException;
import ro.powergrid.buy.ActivePlantMarket;
import ro.powergrid.buy.TransactionalPaymentProcessor;
import ro.powergrid.buy.TransactionalPlayerPlantBroker;
import ro.powergrid.plant.PowerPlantBuilder;
import ro.powergrid.player.ActivePlayer;
import ro.powergrid.player.Player;
import ro.powergrid.turn.TurnProvider;

public class PlayerBuysPlantFromMarketTest {
	private StockFireTurnTest activePlantsMother;
	private SortedLayeredMarketplaceTest marketplaceMother;
	private ActivePlayer activePlayer;
	private ActivePlantMarket activePlantsMarket;
	private TurnProvider turnProvider;

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
		turnProvider = activePlantsMother.getTurnProvider();
		activePlayer.setTurnProvider(turnProvider);
	}

	public void buyFirstPlant() throws InvalidPhaseActionException {
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
	
	@Test(expected = InvalidPhaseActionException.class)
	public void cannotBuyPlantOnResourcePhase() throws Exception {
		turnProvider.nextPhase();
		
		buyFirstPlant();
	}
	
	@Test(expected = InvalidPhaseActionException.class)
	public void canOnlyBuyOnePlantPerPhase() throws Exception {
		buyFirstPlant();
		buyFirstPlant();
	}
	
	@Test
	public void canBuyOneMorePlantOnNextTurn() throws Exception {
		buyFirstPlant();
		turnProvider.nextTurn();
		buyFirstPlant();
		
		assertEquals(Arrays.asList(PowerPlantBuilder.three(), PowerPlantBuilder.four()),
				activePlantsMother.getActivePlants().getPlants());
	}
}
