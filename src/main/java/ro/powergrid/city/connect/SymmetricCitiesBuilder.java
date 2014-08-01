package ro.powergrid.city.connect;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

import ro.powergrid.city.City;

public class SymmetricCitiesBuilder {
	private Map<City, NavigableSet<DirectCityConnection>> connections = new HashMap<>();

	public SymmetricCitiesBuilder withDirectCityConnection(
			DirectCityConnection connection) {

		initializedCityConnections(connection.getStartCity()).add(connection);
		initializedCityConnections(connection.getEndCity()).add(
				reverse(connection));

		return this;
	}

	public SymmetricCitiesBuilder withDirectCityConnection(Number distance,
			City startCity, City endCity) {
		return withDirectCityConnection(new DirectCityConnection(distance, startCity, endCity));
	}
	
	public SymmetricCitiesBuilder touching(
			City startCity, City endCity) {
		return withDirectCityConnection(new DirectCityConnection(1, startCity, endCity));
	}

	public DirectCityConnection reverse(DirectCityConnection original) {
		return new DirectCityConnection(original.getDistance(),
				original.getEndCity(), original.getStartCity());
	}

	public NavigableSet<DirectCityConnection> initializedCityConnections(
			City first) {
		NavigableSet<DirectCityConnection> cityConnections = connections
				.get(first);
		if (cityConnections == null) {
			cityConnections = new TreeSet<>();
			connections.put(first, cityConnections);
		}
		return cityConnections;
	}

	public Collection<ConnectedCity> build() {
		Collection<ConnectedCity> connectedCities = new HashSet<>();
		for (Map.Entry<City, NavigableSet<DirectCityConnection>> entry : connections
				.entrySet()) {
			connectedCities.add(new ConnectedCity(entry.getKey(), entry
					.getValue()));
		}
		return connectedCities;
	}
}
