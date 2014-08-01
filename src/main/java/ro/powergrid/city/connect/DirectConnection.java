package ro.powergrid.city.connect;

import ro.powergrid.city.Distance;

public class DirectConnection implements Distance {

	private static final long serialVersionUID = 1L;

	private final Number distance;
	
	public DirectConnection(Number distance) {
		this.distance = distance;
	}

	@Override
	public Number getDistance() {
		return distance;
	}

	
}
