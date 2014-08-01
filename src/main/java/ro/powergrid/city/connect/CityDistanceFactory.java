package ro.powergrid.city.connect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ro.powergrid.city.City;
import ro.powergrid.city.CityDistance;

public class CityDistanceFactory {
	private City startCity = City.NOWHERESVILLE;
	private City endCity = City.NOWHERESVILLE;
	private List<City> intermediateCities = new ArrayList<>();
	private int allDistances = 1; 
	private List<Number> distances = new ArrayList<>(Arrays.asList(1));

	public CityDistanceFactory withStartCity(City startCity) {
		this.startCity = startCity;
		return this;
	}

	public CityDistanceFactory withEndCity(City endCity) {
		this.endCity = endCity;
		return this;
	}

	public CityDistanceFactory withCity(City intermediate) {
		intermediateCities.add(intermediate);
		distances.add(allDistances);
		return this;
	}

	public CityDistanceFactory withDistance(Number distance) {
		distances.set(distances.size() - 1, distance);
		return this;
	}

	public CityDistance make() {
		if (intermediateCities.isEmpty()) {
			return new DirectCityConnection(distances.get(0), startCity,
					endCity);
		}
		List<DirectCityConnection> directConnections = new ArrayList<>();
		directConnections.add(new DirectCityConnection(distances.get(0),
				startCity, intermediateCities.get(0)));
		for (int i = 1; i < intermediateCities.size(); i++) {
			directConnections.add(new DirectCityConnection(distances.get(i),
					intermediateCities.get(i-1), intermediateCities.get(i)));
		}
		directConnections.add(new DirectCityConnection(distances.get(distances.size()-1),
				intermediateCities.get(intermediateCities.size()-1),endCity));
		return new CityConnection(directConnections);
	}
	
}
