package ro.powergrid.city.connect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import ro.powergrid.city.City;
import ro.powergrid.city.CityDistance;

public class CityConnection extends Connection implements CityDistance {

	private static final long serialVersionUID = 1L;
	private final City startCity;
	private final City endCity;

	public CityConnection(Collection<? extends DirectCityConnection> distances) {
		super(distances);
		List<? extends DirectCityConnection>  myDistances = new ArrayList<>(distances);
		startCity = myDistances.get(0).getStartCity();
		endCity = myDistances.get(myDistances.size()-1).getEndCity();
	}
	
	public CityConnection(DirectCityConnection... distances) {
		this(Arrays.asList(distances));
	}

	public City getStartCity() {
		return startCity;
	}

	public City getEndCity() {
		return endCity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + getDistance().intValue();
		result = prime * result + ((endCity == null) ? 0 : endCity.hashCode());
		result = prime * result
				+ ((startCity == null) ? 0 : startCity.hashCode());
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
		CityConnection other = (CityConnection) obj;
		if (!getDistance().equals(other.getDistance())) {
			return false;
		}
		if (endCity == null) {
			if (other.endCity != null)
				return false;
		} else if (!endCity.equals(other.endCity))
			return false;
		if (startCity == null) {
			if (other.startCity != null)
				return false;
		} else if (!startCity.equals(other.startCity))
			return false;
		return true;
	}
	
	
}
