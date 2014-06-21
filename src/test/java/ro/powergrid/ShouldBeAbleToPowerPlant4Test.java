/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ro.powergrid;

import ro.powergrid.plant.PowerPlant;
import ro.powergrid.plant.PowerPlantAdministrator;
import ro.powergrid.plant.PowerPlantBuilder;
import ro.powergrid.resource.Oil;
import static org.junit.Assert.*;
import org.junit.Test;


/**
 *
 * @author Catalin
 */
public class ShouldBeAbleToPowerPlant4Test {
    
	@Test
    public void powerPlant4CanBePoweredWith2Oil() throws Exception {
        PowerPlant pp = PowerPlantBuilder.four();
        pp.addEnergyResources(2, new Oil());
        
        assertTrue(pp.canPowerCities());
    }
    
    @Test
    public void powerPlant4CanBePoweredWith3Oil() throws Exception {
        PowerPlant pp = PowerPlantBuilder.four();
        pp.addEnergyResources(3, new Oil());
        
        assertTrue(pp.canPowerCities());
    }
    
}
