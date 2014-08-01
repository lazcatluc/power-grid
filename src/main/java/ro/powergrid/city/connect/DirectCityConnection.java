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

	public City getStartCity() {
		return startCity;
	}

	public City getEndCity() {
		return endCity;
	}


}
