package ro.powergrid.city;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

public interface Country extends Serializable {
	
	Country NOWHERELAND = new Country() {
		private static final long serialVersionUID = 1L;

		@Override
		public Distance getDistance(City city, City anotherCity) {
			return Distance.ZERO;
		}

		@Override
		public Collection<City> getCities() {
			return Collections.emptySet();
		}
	};
	
	Distance getDistance(City city, City anotherCity);
	Collection<City> getCities();
}
