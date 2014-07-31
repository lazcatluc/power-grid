package ro.powergrid.city;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class City implements Serializable {
	
	public static final City NOWHERESVILLE = 
			new City("NOWHERESVILLE", Country.NOWHERELAND, Location.NOWHERE);

	private static final long serialVersionUID = 1L;
	
	private final String name;
	private final Country country;
	private final Location location;
	private final List<CityOccupier> occupiers = new ArrayList<>();
	
	public City(String name, Country country, Location location) {
		this.name = name;
		this.country = country;
		this.location = location;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		City other = (City) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public String getName() {
		return name;
	}
	
}
