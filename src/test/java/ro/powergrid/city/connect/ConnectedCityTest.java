package ro.powergrid.city.connect;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.function.Function;

import org.junit.Before;
import org.junit.Test;

import ro.powergrid.city.City;
import ro.powergrid.city.CityBuilder;
import ro.powergrid.city.CityDistance;

public class ConnectedCityTest {
	NavigableSet<CityDistance> distances;

	@Before
	public void setUp() {
		distances = new TreeSet<>();
	}

	@Test
	public void ordersConnectionsOnDistance() throws Exception {
		CityDistance one = connection(factory()::withDistance, 1);
		CityDistance two = connection(factory()::withDistance, 2);
		CityDistance three = connection(factory()::withDistance, 3);

		assertAddedRandomlyPreservesOrder(one, two, three);
	}

	@Test
	public void ordersConnectionsOnStartCity() throws Exception {
		CityDistance one = connectionWith(
				factory()::withStartCity, this::namedCity, "1");
		CityDistance two = connectionWith(
				factory()::withStartCity, this::namedCity, "2");
		CityDistance three = connectionWith(
				factory()::withStartCity, this::namedCity, "3");

		assertAddedRandomlyPreservesOrder(one, two, three);
	}

	@Test
	public void ordersConnectionsOnEndCity() throws Exception {
		CityDistance one = connectionWith(
				factory()::withEndCity, this::namedCity, "1");
		CityDistance two = connectionWith(
				factory()::withEndCity, this::namedCity, "2");
		CityDistance three = connectionWith(
				factory()::withEndCity, this::namedCity, "3");

		assertAddedRandomlyPreservesOrder(one, two, three);
	}
	
	public CityDistanceFactory factory() {
		return new CityDistanceFactory();
	}

	protected void assertAddedRandomlyPreservesOrder(CityDistance one,
			CityDistance two, CityDistance three) {
		distances.add(two);
		distances.add(three);
		distances.add(one);

		assertEquals(Arrays.asList(one, two, three),
				new ArrayList<CityDistance>(distances));
	}

	public <T, U> CityDistance connectionWith(
			Function<T, CityDistanceFactory> factoryMethod,
			Function<U, T> argumentMethod, U argument) {
		return factoryMethod.apply(argumentMethod.apply(argument)).make();
	}
	
	public <T> CityDistance connection(
			Function<T, CityDistanceFactory> factoryMethod, T argument) {
		return factoryMethod.apply(argument).make();
	}

	public City namedCity(String oneString) {
		return new CityBuilder().withName(oneString).build();
	}
}
