package ro.powergrid.plant;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Test;

import ro.powergrid.resource.ResourceType;
import ro.powergrid.turn.Phase;
import ro.powergrid.turn.Turn;

public class StockAdministratorTest extends AdministratorTestAbstract<PlantStocker> {
	
	@Override
	public void setTurn(Turn turn) {
		super.setTurn(turn);
		when(getTurn().getCurrentPhase()).thenReturn(Phase.RESOURCES);
	}
	
	@Test
	public void adding0ResourcesShouldNotChangeAnything() throws Exception {
		PowerPlant pp = PowerPlantBuilder.three();
		getPowerPlantAdministrator().stockPlant(pp, 0, ResourceType.OIL);
        
        assertTrue(pp.getEnergyResources().isEmpty());
	}
	
	@Test
	public void addingResourceMultipleTimesShouldUpdateEnergyResources() throws Exception  {
		PowerPlant pp = PowerPlantBuilder.three();
		getPowerPlantAdministrator().stockPlant(pp, 1, ResourceType.OIL);
		getPowerPlantAdministrator().stockPlant(pp, 1, ResourceType.OIL);
        
        assertEquals(1, pp.getEnergyResources().size());
	}
	
	@Test(expected = IncorrectResourceTypeException.class)
	public void cannotAddIncorrectResourceType() throws Exception {
		PowerPlant pp = PowerPlantBuilder.three();
		getPowerPlantAdministrator().stockPlant(pp, 1, ResourceType.COAL);
	}
	
	@Test(expected = StorageLimitExcedeedException.class)
	public void cannotAddMoreThanDoubleTheNecessaryResources() throws Exception {
		PowerPlant pp = PowerPlantBuilder.three();
		getPowerPlantAdministrator().stockPlant(pp, 5, ResourceType.OIL);
	}
	
	@Test
	public void addsCorrectResourceType() throws Exception {
		PowerPlant pp = PowerPlantBuilder.five();
		getPowerPlantAdministrator().stockPlant(pp, 2, ResourceType.COAL);
		getPowerPlantAdministrator().stockPlant(pp, 2, ResourceType.OIL);
		
		assertEquals(2, pp.getEnergyResources().size());
	}

	@Test(expected = InvalidPhaseActionException.class)
	public void failsToStockPlantOnPowerPhase() throws Exception {
		when(getTurn().getCurrentPhase()).thenReturn(Phase.POWER);
		
		addsCorrectResourceType();
	}
	
	@Test
	public void cannotStockPlantOnPowerPhase() throws Exception {
		when(getTurn().getCurrentPhase()).thenReturn(Phase.POWER);
		
		assertFalse(getPowerPlantAdministrator()
				.canStockPlant(PowerPlantBuilder.three(), 1));
	}

	@Override
	protected PlantStocker makeAdministrator() { 
		return new PlantStocker();
	}
}

