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

}
