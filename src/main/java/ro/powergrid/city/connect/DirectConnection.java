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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((distance == null) ? 0 : distance.hashCode());
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
		DirectConnection other = (DirectConnection) obj;
		if (distance == null) {
			if (other.distance != null)
				return false;
		} else if (!distance.equals(other.distance))
			return false;
		return true;
	}
	
}
