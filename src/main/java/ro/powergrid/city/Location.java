package ro.powergrid.city;

import java.io.Serializable;

public interface Location extends Serializable {
	Location NOWHERE = new Location(){private static final long serialVersionUID = 1L;};
}
