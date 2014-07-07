package ro.powergrid.plant;

import static org.mockito.Mockito.mock;

import org.junit.Before;

import ro.powergrid.turn.Turn;

public class AdministratorTestAbstract {

	private PowerPlantAdministrator powerPlantAdministrator;
	private Turn turn;

	public AdministratorTestAbstract() {
		super();
	}

	@Before
	public void setUp() throws Exception {
		setPowerPlantAdministrator(new PowerPlantAdministrator());
		setTurn(mock(Turn.class));
		getPowerPlantAdministrator().setTurn(getTurn());
	}

	public PowerPlantAdministrator getPowerPlantAdministrator() {
		return powerPlantAdministrator;
	}

	public void setPowerPlantAdministrator(PowerPlantAdministrator powerPlantAdministrator) {
		this.powerPlantAdministrator = powerPlantAdministrator;
	}

	public Turn getTurn() {
		return turn;
	}

	public void setTurn(Turn turn) {
		this.turn = turn;
	}

}