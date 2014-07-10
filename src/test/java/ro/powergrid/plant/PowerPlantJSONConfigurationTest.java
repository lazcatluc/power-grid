package ro.powergrid.plant;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

import org.junit.Test;

public class PowerPlantJSONConfigurationTest {

	@Test
	public void getsOnePlantFromJson() throws Exception {
		String json="[{\"basePrice\":3,"
					+ "\"necessaryResources\":2,"
					+ "\"numberOfCitiesPowered\":1,"
					+ "\"resourceTypes\":[\"OIL\"]}]";
		
		ByteArrayInputStream bais = new ByteArrayInputStream(json.getBytes());
		PowerPlantJSONConfiguration ppc = new PowerPlantJSONConfiguration();
		assertEquals(Collections.singletonMap(3, PowerPlantBuilder.three()),
				ppc.getPlants(bais));
	}
	
	@Test
	public void getsPlantsFromConfigurationFile() throws Exception {
		InputStream plantsStream = PowerPlantJSONConfigurationTest.class
				.getResourceAsStream("plants.json");
		
		PowerPlantJSONConfiguration ppc = new PowerPlantJSONConfiguration();
		Map<Integer, PowerPlant> plants = ppc.getPlants(plantsStream);
		
		assertEquals(PowerPlantBuilder.thirteen(), plants.get(13));
	}
}
