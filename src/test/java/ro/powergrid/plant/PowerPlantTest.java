package ro.powergrid.plant;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ro.powergrid.resource.ResourceType;

public class PowerPlantTest {
	@Test
	public void adding0ResourcesShouldNotChangeAnything() {
		PowerPlant pp = PowerPlantBuilder.three();
        pp.addEnergyResources(0, ResourceType.OIL);
        
        assertTrue(pp.getEnergyResources().isEmpty());
	}
}
