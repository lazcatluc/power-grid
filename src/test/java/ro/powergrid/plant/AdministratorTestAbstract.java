package ro.powergrid.plant;

import static org.mockito.Mockito.mock;

import org.junit.Before;

import ro.powergrid.turn.Turn;

public abstract class AdministratorTestAbstract<T extends PowerPlantAdministrator> {

	private T powerPlantAdministrator;
	private Turn turn;

	public AdministratorTestAbstract() {
		super();
	}

	protected abstract T makeAdministrator();
	
	@Before
	public void setUp() throws Exception {
		setPowerPlantAdministrator(makeAdministrator());
		setTurn(mock(Turn.class));
		getPowerPlantAdministrator().setTurn(getTurn());
	}

	public T getPowerPlantAdministrator() {
		return powerPlantAdministrator;
	}

	public void setPowerPlantAdministrator(T powerPlantAdministrator) {
		this.powerPlantAdministrator = powerPlantAdministrator;
	}

	public Turn getTurn() {
		return turn;
	}

	public void setTurn(Turn turn) {
		this.turn = turn;
	}

}