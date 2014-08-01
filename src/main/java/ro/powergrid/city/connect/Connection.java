package ro.powergrid.city.connect;

import java.util.Arrays;
import java.util.Collection;

import ro.powergrid.city.Distance;

public class Connection implements Distance {

	private static final long serialVersionUID = 1L;
	private static final double EPSILON = 0.0001d;
	
	private final Number distance;
	
	public Connection(Collection<? extends Distance> distances) {
		double d = 0;
		for (Distance distance : distances) {
			d += distance.getDistance().doubleValue();
		}
		long rounded = Math.round(d);
		if (Math.abs(rounded - d) < EPSILON) {
			if (rounded <= Integer.MAX_VALUE && rounded >= Integer.MIN_VALUE) {
				distance = (int) rounded;
			}
			else {
				distance = rounded;
			}
		}
		else {
			distance = d;
		}
	}
	
	public Connection(Distance... distances) {
		this(Arrays.asList(distances));
	}

	@Override
	public Number getDistance() {
		return distance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + distance.intValue();
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
		Connection other = (Connection) obj;
		if (distance != other.distance)
			return false;
		return true;
	}

	
}
