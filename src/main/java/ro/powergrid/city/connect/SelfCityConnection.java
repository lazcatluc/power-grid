package ro.powergrid.city.connect;

import ro.powergrid.city.City;
import ro.powergrid.city.CityDistance;

public class SelfCityConnection implements CityDistance {

	private static final long serialVersionUID = 1839342724941669262L;

	private final City myCity;

	public SelfCityConnection(City myCity) {
		this.myCity = myCity;
	}

	@Override
	public Number getDistance() {
		return 0;
	}

	@Override
	public City getStartCity() {
		return myCity;
	}

	@Override
	public City getEndCity() {
		return myCity;
	}

}
