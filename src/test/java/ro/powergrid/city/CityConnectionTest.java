package ro.powergrid.city;

import static org.junit.Assert.*;

import org.junit.Test;

public class CityConnectionTest {

	@Test
	public void connectionHasInitialCity() throws Exception {
		String expectedName = "First";
		CityDistance connection = intermediateCityFactory()
			.withStartCity(namedCity(expectedName))
			.make();
		
		assertEquals(expectedName, connection.getStartCity().getName());
	}
	
	@Test
	public void connectionHasFinalCity() throws Exception {
		String expectedName = "Last";
		CityDistance connection = intermediateCityFactory()
			.withEndCity(namedCity(expectedName))
			.make();
		
		assertEquals(expectedName, connection.getEndCity().getName());
	}

	public City namedCity(String expectedName) {
		return new CityBuilder().withName(expectedName).build();
	}

	public CityDistanceFactory intermediateCityFactory() {
		return new CityDistanceFactory().withCity(City.NOWHERESVILLE);
	}
}
