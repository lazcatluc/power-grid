package ro.powergrid.city.connect;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

import ro.powergrid.city.City;
import ro.powergrid.city.CityBuilder;

public class DijsktraCountryTest {

	@Test
	public void distanceZeroIfCityIsOwned() throws Exception {
		DijkstraCountry country = new DijkstraCountry(Collections.emptySet());

		assertEquals(
				0,
				country.getDistance(City.NOWHERESVILLE,
						Collections.singleton(City.NOWHERESVILLE))
						.getDistance());
	}

	@Test
	public void distanceOneIfCityIsTheOnlyNeighbor() throws Exception {
		City expectedCity = new CityBuilder().withName("Expected").build();
		int expectedDistance = 1;
		DijkstraCountry country = new DijkstraCountry(
				new SymmetricConnectedCitiesBuilder().withDirectCityConnection(
						new DirectCityConnection(expectedDistance,
								City.NOWHERESVILLE, expectedCity)).build());

		assertEquals(
				expectedDistance,
				country.getDistance(expectedCity,
						Collections.singleton(City.NOWHERESVILLE))
						.getDistance());
	}
}
