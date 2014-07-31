package ro.powergrid.city;

import java.io.Serializable;

public interface Distance extends Serializable, Comparable<Distance> {
	
	Distance ZERO = new Distance() {
		private static final long serialVersionUID = 1L;

		@Override
		public Number getDistance() {
			return 0;
		}
	};
	
	Number getDistance();
	
	default int compareTo(Distance o) {
		return getDistance().intValue() - o.getDistance().intValue();
	}
}
