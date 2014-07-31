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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((myCity == null) ? 0 : myCity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SelfCityConnection other = (SelfCityConnection) obj;
		if (myCity == null) {
			if (other.myCity != null)
				return false;
		} else if (!myCity.equals(other.myCity))
			return false;
		return true;
	}

}
