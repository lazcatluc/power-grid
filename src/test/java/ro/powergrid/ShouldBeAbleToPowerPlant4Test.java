/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ro.powergrid;

import ro.powergrid.plant.PowerPlant;
import ro.powergrid.plant.PowerPlantAdministrator;
import ro.powergrid.plant.PowerPlantBuilder;
import ro.powergrid.resource.Coal;
import ro.powergrid.resource.Oil;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Adelina
 */
public class ShouldBeAbleToPowerPlant4Test {
	private PowerPlant pp;
	
    @Before
	public void setUp() throws Exception
    {
        pp = PowerPlantBuilder.four();
    }

    @Test
    public void powerPlant4NoPoweredWith0Coal() throws Exception {
    	pp.addEnergyResources(0, new Coal());
    	
    	assertFalse(pp.canPowerCities());
    }
    
    @Test
    public void powerPlant4NoPoweredWith1Coal() throws Exception {
    	pp.addEnergyResources(1, new Coal());
    	
    	assertFalse(pp.canPowerCities());
    }

	@Test
    public void powerPlant4CanBePoweredWith2Oil() throws Exception {
        pp.addEnergyResources(2, new Coal());
        
        assertTrue(pp.canPowerCities());
    }
    
    @Test
    public void powerPlant4CanBePoweredWith3Oil() throws Exception {
        pp.addEnergyResources(3, new Coal());
        
        assertTrue(pp.canPowerCities());
    }
    
    @Test
    public void powerPlant4CanBePoweredWith4Oil() throws Exception {
        pp.addEnergyResources(4, new Coal());
        
        assertTrue(pp.canPowerCities());
    }
}
