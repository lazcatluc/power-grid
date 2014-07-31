package ro.powergrid.city;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

public interface Country extends Serializable {
	
	Country NOWHERELAND = new Country() {
		private static final long serialVersionUID = 1L;

		@Override
		public CityDistance getDistance(City city, Collection<City> previousCities) {
			return new CityDistance() {
				private static final long serialVersionUID = 1L;

				@Override
				public Number getDistance() {
					return Double.POSITIVE_INFINITY;
				}

				@Override
				public City getStartCity() {
					return city;
				}

				@Override
				public City getEndCity() {
					return City.NOWHERESVILLE;
				}
			};
		}

		@Override
		public Collection<City> getCities() {
			return Collections.emptySet();
		}
	};
	
	CityDistance getDistance(City city, Collection<City> previousCities);
	Collection<City> getCities();
}
