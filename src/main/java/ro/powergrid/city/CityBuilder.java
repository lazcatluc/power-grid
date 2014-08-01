package ro.powergrid.city;

public class CityBuilder {

	private String name="NOWHERESVILLE";
	private Country country = Country.NOWHERELAND;
	private Location location = Location.NOWHERE;
	
	public CityBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public CityBuilder withCountry(Country country) {
		this.country = country;
		return this;
	}
	
	public CityBuilder withLocation(Location location) {
		this.location = location;
		return this;
	}
	
	public City build() {
		return new City(name, country, location);
	}

	public static City named(String name) {
		return new CityBuilder().withName(name).build();
	}
}
