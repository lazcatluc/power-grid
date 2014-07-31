package ro.powergrid.city;

import static org.junit.Assert.*;

import org.junit.Test;

public class CityConnectionTest {

	@Test
	public void connectionHasInitialCity() throws Exception {
		CityConnection connection = new CityConnection (
				new DirectCityConnection(1, 
						new CityBuilder().withName("First").build(),
						City.NOWHERESVILLE),
				DirectCityConnection.CIRCULAR_NOWHERE		
			);
		
		assertEquals("First", connection.getStartCity().getName());
	}
	
	@Test
	public void connectionHasFinalCity() throws Exception {
		CityConnection connection = new CityConnection (
				DirectCityConnection.CIRCULAR_NOWHERE,
				new DirectCityConnection(1, 
						City.NOWHERESVILLE,
						new CityBuilder().withName("Last").build())
			);
		
		assertEquals("Last", connection.getEndCity().getName());
	}
}
