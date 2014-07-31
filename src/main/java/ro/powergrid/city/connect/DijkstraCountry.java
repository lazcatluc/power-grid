package ro.powergrid.city.connect;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import ro.powergrid.city.City;
import ro.powergrid.city.CityDistance;
import ro.powergrid.city.Country;

public class DijkstraCountry implements Country {

	private static final long serialVersionUID = 1L;

	private final Map<City, ConnectedCity> cityMap;

	public DijkstraCountry(Collection<ConnectedCity> connectedCities) {
		Map<City, ConnectedCity> myCityMap = new HashMap<>();
		for (ConnectedCity connectedCity : connectedCities) {
			myCityMap.put(connectedCity.getMyCity(), connectedCity);
		}
		cityMap = Collections.unmodifiableMap(myCityMap);
	}

	@Override
	public CityDistance getDistance(City city, Collection<City> previousCities) {
		if (previousCities.contains(city)) {
			return new SelfCityConnection(city);
		}
		TreeSet<? extends CityDistance> distances = new TreeSet<>(cityMap.get(
				city).getConnections());
		CityDistance first = distances.first();
		if (previousCities.contains(first.getEndCity())) {
			return first;
		}
		return null;
	}

	@Override
	public Collection<City> getCities() {
		return cityMap.keySet();
	}

}
