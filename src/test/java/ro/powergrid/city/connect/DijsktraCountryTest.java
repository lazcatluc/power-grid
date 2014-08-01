package ro.powergrid.city.connect;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;

import ro.powergrid.city.City;
import ro.powergrid.city.CityBuilder;
import ro.powergrid.city.Country;

public class DijsktraCountryTest {

	@Test
	public void distanceZeroIfCityIsOwned() throws Exception {
		DijkstraCountry country = new DijkstraCountry(Collections.emptySet());

		assertEquals(
				0,
				distance(country,City.NOWHERESVILLE,City.NOWHERESVILLE));
	}

	@Test
	public void distanceOneIfCityIsTheOnlyNeighbor() throws Exception {
		City targetCity = new CityBuilder().withName("Target").build();

		DijkstraCountry country = new DijkstraCountry(
				new SymmetricCitiesBuilder().touching(
						City.NOWHERESVILLE, targetCity).build());

		assertEquals(
				1,
				distance(country,targetCity,City.NOWHERESVILLE));
	}

	@Test
	public void distanceTwoForThreeCitiesOnALine() throws Exception {
		City one = CityBuilder.named("1");
		City two = CityBuilder.named("2");
		City three = CityBuilder.named("3");

		DijkstraCountry country = new DijkstraCountry(
				new SymmetricCitiesBuilder().touching(one, two)
						.touching(two, three).build());

		assertEquals(2, distance(country, one, three));
	}
	
	protected Number distance(Country country, City source, City destination) {
		return country.getDistance(source, 
				Collections.singleton(destination)).getDistance();
	}
}
