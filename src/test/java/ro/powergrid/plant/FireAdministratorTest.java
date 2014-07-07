package ro.powergrid.plant;

import static org.mockito.Mockito.*;

import org.junit.Test;

import ro.powergrid.turn.Phase;
import ro.powergrid.turn.Turn;

public class FireAdministratorTest extends AdministratorTestAbstract {

	@Override
	public void setTurn(Turn turn) {
		super.setTurn(turn);
		when(getTurn().getCurrentPhase()).thenReturn(Phase.POWER);
	}

	@Test
	public void canFirePlantOnPowerPhase() throws Exception {		
		getPowerPlantAdministrator().firePlant(PowerPlantBuilder.thirteen());
	}
	
	@Test(expected = InvalidPhaseActionException.class)
	public void cannotFirePlantOnResourcePhase() throws Exception {
		when(getTurn().getCurrentPhase()).thenReturn(Phase.RESOURCES);
		
		getPowerPlantAdministrator().firePlant(PowerPlantBuilder.thirteen());
	}

	@Test(expected = InvalidPhaseActionException.class)
	public void cannotFirePlantIfAlreadFiredOnTurn() throws Exception {
		PowerPlant plant = PowerPlantBuilder.thirteen();
		when(getTurn().hasFired(plant)).thenReturn(Boolean.TRUE);
		
		getPowerPlantAdministrator().firePlant(plant);
	}
	
	@Test
	public void whenFiringTurnRemembersPlant() throws Exception {
		canFirePlantOnPowerPhase();
		
		verify(getTurn(), times(1)).setFired(PowerPlantBuilder.thirteen());
	}
}
