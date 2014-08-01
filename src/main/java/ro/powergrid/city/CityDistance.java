package ro.powergrid.city;


public interface CityDistance extends Distance {
	City getStartCity();
	City getEndCity();
	
	@Override
	default int compareTo(Distance o) {
		int actualDistance = getDistance().intValue() - o.getDistance().intValue();
		if (actualDistance != 0) {
			return actualDistance;
		}
		if (!(o instanceof CityDistance)) {
			return -1;
		}
		CityDistance other = (CityDistance) o;
		int startCityCompare = this.getStartCity().getName()
				.compareTo(other.getStartCity().getName());
		if (startCityCompare != 0) {
			return startCityCompare;
		}
		return this.getEndCity().getName()
				.compareTo(other.getEndCity().getName());
	}
}
