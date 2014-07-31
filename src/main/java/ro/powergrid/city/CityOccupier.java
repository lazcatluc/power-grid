package ro.powergrid.city;

import java.util.Collection;

public interface CityOccupier {
    Collection<City> getCities();
    
    void add(City city);
}
