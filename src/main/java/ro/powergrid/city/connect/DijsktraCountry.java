package ro.powergrid.city.connect;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import ro.powergrid.city.City;
import ro.powergrid.city.CityDistance;
import ro.powergrid.city.Country;

public class DijsktraCountry implements Country {

	private static final long serialVersionUID = 1L;
	
	private final Map<City, ConnectedCity> cityMap;
	
	public DijsktraCountry(Collection<ConnectedCity> connectedCities) {
		Map<City, ConnectedCity> myCityMap = new HashMap<>();
		for (ConnectedCity connectedCity : connectedCities) {
			myCityMap.put(connectedCity.getMyCity(), connectedCity);
		}
		cityMap = Collections.unmodifiableMap(myCityMap);
	}
	
	@Override
	public CityDistance getDistance(City city, Collection<City> previousCities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<City> getCities() {
		return cityMap.keySet();
	}

}
