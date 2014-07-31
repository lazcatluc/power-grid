package ro.powergrid.city;

import java.util.Arrays;
import java.util.Collection;

public class Connection implements Distance {

	private static final long serialVersionUID = 1L;
	
	private final int distance;
	
	public Connection(Collection<Distance> distances) {
		int d = 0;
		for (Distance distance : distances) {
			d += distance.getDistance().intValue();
		}
		distance = d;
	}
	
	public Connection(Distance... distances) {
		this(Arrays.asList(distances));
	}

	@Override
	public Number getDistance() {
		return distance;
	}

}
