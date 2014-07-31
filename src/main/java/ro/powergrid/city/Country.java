package ro.powergrid.city;

import java.io.Serializable;
import java.util.Collection;

public interface Country extends Serializable {
	Distance getDistance(City city, City anotherCity);
	Collection<City> getCities();
}
