/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ro.powergrid;

import ro.powergrid.plant.PowerPlant;
import ro.powergrid.plant.PowerPlantAdministrator;
import ro.powergrid.plant.PowerPlantBuilder;
import static org.junit.Assert.*;
import org.junit.Test;
import ro.powergrid.resource.ResourceType;


/**
 *
 * @author Catalin
 */
public class ShouldBeAbleToPowerPlant4Test {
    
	@Test
    public void powerPlant4CanBePoweredWith2Oil() throws Exception {
        PowerPlant pp = PowerPlantBuilder.four();
        pp.addEnergyResources(2, ResourceType.OIL);
        
        assertTrue(pp.canPowerCities());
    }
    
    @Test
    public void powerPlant4CanBePoweredWith3Oil() throws Exception {
        PowerPlant pp = PowerPlantBuilder.four();
        pp.addEnergyResources(3, ResourceType.OIL);
        
        assertTrue(pp.canPowerCities());
    }
    
}
