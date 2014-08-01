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

	public CityConnection(Collection<? extends CityDistance> distances) {
		super(distances);
		List<? extends CityDistance>  myDistances = new ArrayList<>(distances);
		startCity = myDistances.get(0).getStartCity();
		endCity = myDistances.get(myDistances.size()-1).getEndCity();
	}
	
	public CityConnection(CityDistance... distances) {
		this(Arrays.asList(distances));
	}

	public City getStartCity() {
		return startCity;
	}

	public City getEndCity() {
		return endCity;
	}

}
