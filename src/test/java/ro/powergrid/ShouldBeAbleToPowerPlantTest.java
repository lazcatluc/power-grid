/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ro.powergrid;

import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;


/**
 *
 * @author Catalin
 */
public class ShouldBeAbleToPowerPlantTest {
    
    @Test
    public void powerPlant3CanBePoweredWith2Oil() throws Exception {
        PowerPlant pp = PowerPlantBuilder.three();
        pp.addEnergyResources(2, new OilResource());
        
        assertTrue(pp.canPowerCities());
    }
    
    @Test   
    public void powerPlant3Keeps1OilAfterPoweringCitiesWith3Oil() 
            throws Exception {
        PowerPlant pp = PowerPlantBuilder.three();
        pp.addEnergyResources(3, new OilResource());
        
        new PowerPlantAdministrator().firePlant(pp);
        
        assertEquals(1, pp.getTotalResourcesStored());
    }
}
