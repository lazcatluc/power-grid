package ro.powergrid.plant;

import static org.junit.Assert.*;

import org.junit.Test;

import ro.powergrid.resource.ResourceType;

public class PowerPlantTest {
	@Test
	public void adding0ResourcesShouldNotChangeAnything() {
		PowerPlant pp = PowerPlantBuilder.three();
        pp.addEnergyResources(0, ResourceType.OIL);
        
        assertTrue(pp.getEnergyResources().isEmpty());
	}
	
	@Test
	public void addingLastResourceMultipleTimesShouldUpdateEnergyResources() {
		PowerPlant pp = PowerPlantBuilder.three();
        pp.addEnergyResources(1, ResourceType.OIL);
        pp.addEnergyResources(1, ResourceType.OIL);
        
        assertEquals(1, pp.getEnergyResources().size());
	}
}
