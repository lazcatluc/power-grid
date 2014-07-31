package ro.powergrid.city.connect;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

import ro.powergrid.city.City;

public class ConnectedCity implements Serializable {
	
	private static final long serialVersionUID = 2219907957326078411L;
	
	private final NavigableSet<DirectCityConnection> connections;
	private final City myCity;

	public ConnectedCity(City myCity, Map<Number, City> directConnectionDistances) {
		this.myCity = myCity;
		NavigableSet<DirectCityConnection> myConnections = new TreeSet<>();
		for (Map.Entry<Number, City> entry : directConnectionDistances.entrySet()) {
			myConnections.add(new DirectCityConnection(entry.getKey(), 
					this.myCity, entry.getValue()));
		}
		connections = Collections.unmodifiableNavigableSet(myConnections);
	}

	public NavigableSet<DirectCityConnection> getConnections() {
		return connections;
	}

	public City getMyCity() {
		return myCity;
	}

}
