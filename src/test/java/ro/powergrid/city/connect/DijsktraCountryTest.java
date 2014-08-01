package ro.powergrid.city.connect;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Test;

import ro.powergrid.city.City;
import ro.powergrid.city.CityBuilder;
import ro.powergrid.city.Country;

public class DijsktraCountryTest {

	@Test
	public void distanceZeroIfCityIsOwned() throws Exception {
		DijkstraCountry country = new DijkstraCountry(Collections.emptySet());

		assertEquals(0,
				distance(country, City.NOWHERESVILLE, City.NOWHERESVILLE));
	}

	@Test
	public void distanceOneIfCityIsTheOnlyNeighbor() throws Exception {
		City targetCity = new CityBuilder().withName("Target").build();

		DijkstraCountry country = new DijkstraCountry(
				new SymmetricCitiesBuilder().touching(City.NOWHERESVILLE,
						targetCity).build());

		assertEquals(1, distance(country, targetCity, City.NOWHERESVILLE));
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

	@Test
	public void infiniteDistanceForCityNotInCountry() throws Exception {
		City one = CityBuilder.named("1");

		Country country = new DijkstraCountry(new SymmetricCitiesBuilder()
				.touching(City.NOWHERESVILLE, City.NOWHERESVILLE).build());

		assertTrue(((Double) distance(country, City.NOWHERESVILLE, one))
				.isInfinite());
	}

	@Test
	public void infiniteDistanceForDestinationNotInCountry() throws Exception {
		City one = CityBuilder.named("1");

		Country country = new DijkstraCountry(new SymmetricCitiesBuilder()
				.touching(City.NOWHERESVILLE, City.NOWHERESVILLE).build());

		assertTrue(((Double) distance(country, one, City.NOWHERESVILLE))
				.isInfinite());
	}

	@Test
	public void distanceForThreeAlternateRoutesIsMinimum() throws Exception {
		City start = CityBuilder.named("Start");
		City one = CityBuilder.named("1");
		City two = CityBuilder.named("2");
		City three = CityBuilder.named("3");
		City end = CityBuilder.named("End");

		DijkstraCountry country = new DijkstraCountry(
				new SymmetricCitiesBuilder().touching(start, one)
						.touching(start, two).touching(start, three)
						.touching(one, end)
						.withDirectCityConnection(2, two, end)
						.withDirectCityConnection(3, three, end).build());

		assertEquals(2, distance(country, start, end));
	}

	@Test
	public void findsDistanceWith10000Hops() throws Exception {
		City start = CityBuilder.named("Start");
		City end = CityBuilder.named("End");
		SymmetricCitiesBuilder builder = new SymmetricCitiesBuilder();
		City current = start;

		for (int i = 0; i < 10000; i++) {
			City newCity = CityBuilder.named(String.valueOf(i));
			builder.touching(current, newCity);
			current = newCity;
		}

		DijkstraCountry country = new DijkstraCountry(builder.touching(current,
				end).build());

		assertEquals(10001, distance(country, start, end));
	}

	/**
	 * http://forums.udacity.com/questions/11006445/
	 * what-are-hard-test-cases-for-fast-dijkstra-ps5-1
	 * 
	 * def test(): # shortcuts (a,b,c,d,e,f,g) = ('A', 'B', 'C', 'D', 'E', 'F',
	 * 'G') triples =
	 * ((a,c,3),(c,b,10),(a,b,15),(d,b,9),(a,d,4),(d,f,7),(d,e,3),
	 * (e,g,1),(e,f,5),(f,g,2),(b,f,1)) G = {} for (i,j,k) in triples:
	 * make_link(G, i, j, k)
	 * 
	 * dist = dijkstra(G, a) assert dist[g] == 8 #(a -> d -> e -> g) assert
	 * dist[b] == 11 #(a -> d -> e -> g -> f -> b)
	 * 
	 * @throws Exception
	 */
	@Test
	public void harderTestFromUdacity1() throws Exception {
		City a = CityBuilder.named("a");
		City b = CityBuilder.named("b");
		City c = CityBuilder.named("c");
		City d = CityBuilder.named("d");
		City e = CityBuilder.named("e");
		City f = CityBuilder.named("f");
		City g = CityBuilder.named("g");

		DijkstraCountry country = new DijkstraCountry(
				new SymmetricCitiesBuilder().withDirectCityConnection(3, a, c)
						.withDirectCityConnection(10, c, b)
						.withDirectCityConnection(15, a, b)
						.withDirectCityConnection(9, d, b)
						.withDirectCityConnection(4, a, d)
						.withDirectCityConnection(7, d, f)
						.withDirectCityConnection(3, d, e)
						.withDirectCityConnection(1, e, g)
						.withDirectCityConnection(5, e, f)
						.withDirectCityConnection(2, f, g)
						.withDirectCityConnection(1, b, f).build());

		assertEquals(8, distance(country, a, g));
		assertEquals(11, distance(country, a, b));
	}

	/**
	 * 
	 * http://forums.udacity.com/questions/11006445/
	 * what-are-hard-test-cases-for-fast-dijkstra-ps5-1
	 * 
	 * def test2(): (a,b,c,d,e,f,g) = (0, 1, 2, 3, 4, 5, 6) triples =
	 * ((a,b,2),(a,c,2),(a,d,4),(a,e,2),(a,f,4),(b,g,5),(c,d,4),(d,e,1)) G = {}
	 * for (i,j,k) in triples: make_link(G, i, j, k)
	 * 
	 * assert dijkstra(G, a)[d] == 3 # a -> e -> d return
	 * 
	 * @throws Exception
	 */
	@Test
	public void harderTestFromUdacity2() throws Exception {
		City a = CityBuilder.named("a");
		City b = CityBuilder.named("b");
		City c = CityBuilder.named("c");
		City d = CityBuilder.named("d");
		City e = CityBuilder.named("e");
		City f = CityBuilder.named("f");
		City g = CityBuilder.named("g");

		DijkstraCountry country = new DijkstraCountry(
				new SymmetricCitiesBuilder().withDirectCityConnection(2, a, b)
						.withDirectCityConnection(2, a, c)
						.withDirectCityConnection(4, a, d)
						.withDirectCityConnection(2, a, e)
						.withDirectCityConnection(4, a, f)
						.withDirectCityConnection(5, b, g)
						.withDirectCityConnection(4, c, d)
						.withDirectCityConnection(1, d, e).build());

		assertEquals(3, distance(country, a, d));
	}

	/**
	 * http://forums.udacity.com/questions/11006445/
	 * what-are-hard-test-cases-for-fast-dijkstra-ps5-1
	 * 
	 * 
	 * def test3(): (a,b,c,d) = ('A', 'B', 'C', 'D') triples =
	 * ((a,b,1),(a,c,4),(a,d,4),(b,c,1),(c,d,1)) G = {} for (i,j,k) in triples:
	 * make_link(G, i, j, k)
	 * 
	 * dist = dijkstra(G, a)
	 * 
	 * assert dist[d] == 3 #(a -> b -> c -> d)
	 * 
	 * @throws Exception
	 */
	@Test
	public void harderTestFromUdacity3() throws Exception {
		City a = CityBuilder.named("a");
		City b = CityBuilder.named("b");
		City c = CityBuilder.named("c");
		City d = CityBuilder.named("d");
		
		DijkstraCountry country = new DijkstraCountry(
				new SymmetricCitiesBuilder()
						.withDirectCityConnection(1, a, b)
						.withDirectCityConnection(4, a, c)
						.withDirectCityConnection(4, a, d)
						.withDirectCityConnection(1, b, c)
						.withDirectCityConnection(1, c, d)
							.build());
		
		assertEquals(3, distance(country, a, d));
	}

	protected Number distance(Country country, City source, City destination) {
		return country.getDistance(source, Collections.singleton(destination))
				.getDistance();
	}
}
