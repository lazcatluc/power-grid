package ro.powergrid.plant;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import ro.powergrid.turn.Phase;
import ro.powergrid.turn.Turn;

public class AdministratorPhaseTest {

	private PowerPlantAdministrator powerPlantAdministrator;
	private Turn turn;
	
	@Before
	public void setUp() throws Exception {
		powerPlantAdministrator = new PowerPlantAdministrator();
		turn = mock(Turn.class);
		powerPlantAdministrator.setTurn(turn);
	}
	
	@Test
	public void canFirePlantOnPowerPhase() throws Exception {
		when(turn.getCurrentPhase()).thenReturn(Phase.POWER);
		
		powerPlantAdministrator.firePlant(PowerPlantBuilder.thirteen());
	}
	
	@Test(expected = InvalidPhaseActionException.class)
	public void cannotFirePlantOnResourcePhase() throws Exception {
		when(turn.getCurrentPhase()).thenReturn(Phase.RESOURCES);
		
		powerPlantAdministrator.firePlant(PowerPlantBuilder.thirteen());
	}

	@Test(expected = InvalidPhaseActionException.class)
	public void cannotFirePlantIfAlreadFiredOnTurn() throws Exception {
		when(turn.getCurrentPhase()).thenReturn(Phase.POWER);
		PowerPlant plant = PowerPlantBuilder.thirteen();
		when(turn.hasFired(plant)).thenReturn(Boolean.TRUE);
		
		powerPlantAdministrator.firePlant(plant);
	}
	
	@Test
	public void whenFiringTurnRemembersPlant() throws Exception {
		canFirePlantOnPowerPhase();
		
		verify(turn, times(1)).setFired(PowerPlantBuilder.thirteen());
	}
}
