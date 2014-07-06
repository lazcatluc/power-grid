package ro.powergrid.plant;

import static org.junit.Assert.*;

import org.junit.Test;

import ro.powergrid.resource.ResourceType;

public class PowerPlantAdministratorTest {
	
	private PowerPlantAdministrator administrator = new PowerPlantAdministrator();
	
	@Test
	public void adding0ResourcesShouldNotChangeAnything() throws Exception {
		PowerPlant pp = PowerPlantBuilder.three();
		administrator.stockPlant(pp, 0, ResourceType.OIL);
        
        assertTrue(pp.getEnergyResources().isEmpty());
	}
	
	@Test
	public void addingResourceMultipleTimesShouldUpdateEnergyResources() throws Exception  {
		PowerPlant pp = PowerPlantBuilder.three();
		administrator.stockPlant(pp, 1, ResourceType.OIL);
		administrator.stockPlant(pp, 1, ResourceType.OIL);
        
        assertEquals(1, pp.getEnergyResources().size());
	}
	
	@Test(expected = IncorrectResourceTypeException.class)
	public void cannotAddIncorrectResourceType() throws Exception {
		PowerPlant pp = PowerPlantBuilder.three();
		administrator.stockPlant(pp, 1, ResourceType.COAL);
	}
	
	@Test(expected = StorageLimitExcedeedException.class)
	public void cannotAddMoreThanDoubleTheNecessaryResources() throws Exception {
		PowerPlant pp = PowerPlantBuilder.three();
		administrator.stockPlant(pp, 5, ResourceType.OIL);
	}
	
	@Test
	public void addsCorrectResourceType() throws Exception {
		PowerPlant pp = PowerPlantBuilder.five();
		administrator.stockPlant(pp, 2, ResourceType.COAL);
		administrator.stockPlant(pp, 2, ResourceType.OIL);
		
		assertEquals(2, pp.getEnergyResources().size());
	}
}
