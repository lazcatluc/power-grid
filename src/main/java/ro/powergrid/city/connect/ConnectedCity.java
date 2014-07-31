package ro.powergrid.city.connect;

import java.io.Serializable;
import java.util.Collections;
import java.util.NavigableSet;
import java.util.TreeSet;

import ro.powergrid.city.City;

public class ConnectedCity implements Serializable {

	private static final long serialVersionUID = 2219907957326078411L;

	private final NavigableSet<DirectCityConnection> connections;
	private final City myCity;

	public ConnectedCity(City myCity,
			NavigableSet<DirectCityConnection> myConnections) {
		this.myCity = myCity;

		connections = Collections.unmodifiableNavigableSet(new TreeSet<>(
				myConnections));
	}

	public NavigableSet<DirectCityConnection> getConnections() {
		return connections;
	}

	public City getMyCity() {
		return myCity;
	}

}
