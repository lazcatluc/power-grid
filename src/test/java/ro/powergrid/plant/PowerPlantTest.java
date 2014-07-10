package ro.powergrid.plant;

import static org.junit.Assert.*;

import org.junit.Test;

public class PowerPlantTest {

	@Test
	public void threeLessThanFour() throws Exception {
		assertEquals(-1, PowerPlantBuilder.three().compareTo(PowerPlantBuilder.four()));
	}
}
