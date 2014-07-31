package ro.powergrid.city;

import java.io.Serializable;

public interface Distance extends Serializable, Comparable<Distance> {
	Number getDistance();
	
	default int compareTo(Distance o) {
		return getDistance().intValue() - o.getDistance().intValue();
	}
}
