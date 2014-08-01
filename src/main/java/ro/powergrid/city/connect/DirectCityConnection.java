package ro.powergrid.city.connect;

import ro.powergrid.city.City;
import ro.powergrid.city.CityDistance;

public class DirectCityConnection extends DirectConnection implements
		CityDistance {

	public static final DirectCityConnection CIRCULAR_NOWHERE = new DirectCityConnection(
			0, City.NOWHERESVILLE, City.NOWHERESVILLE);

	private static final long serialVersionUID = -5695836692684415449L;

	private final City startCity;
	private final City endCity;

	public DirectCityConnection(Number distance, City oneCity, City anotherCity) {
		super(distance);
		this.startCity = oneCity;
		this.endCity = anotherCity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((endCity == null) ? 0 : endCity.hashCode());
		result = prime * result
				+ ((startCity == null) ? 0 : startCity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		DirectCityConnection other = (DirectCityConnection) obj;
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

	public City getStartCity() {
		return startCity;
	}

	public City getEndCity() {
		return endCity;
	}


}
