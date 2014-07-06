/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ro.powergrid.plant;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ro.powergrid.plant.PowerPlant;
import ro.powergrid.plant.PowerPlantBuilder;
import ro.powergrid.resource.ResourceType;

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
    	pp.addEnergyResources(0, ResourceType.COAL);
    	
    	assertFalse(pp.canPowerCities());
    }
    
    @Test
    public void powerPlant4NoPoweredWith1Coal() throws Exception {
    	pp.addEnergyResources(1, ResourceType.COAL);
    	
    	assertFalse(pp.canPowerCities());
    }

	@Test
    public void powerPlant4CanBePoweredWith2Coal() throws Exception {
        pp.addEnergyResources(2, ResourceType.COAL);
        
        assertTrue(pp.canPowerCities());
    }
    
    @Test
    public void powerPlant4CanBePoweredWith3Coal() throws Exception {
        pp.addEnergyResources(3, ResourceType.COAL);
        
        assertTrue(pp.canPowerCities());
    }
    
    @Test
    public void powerPlant4CanBePoweredWith4Coal() throws Exception {
        pp.addEnergyResources(4, ResourceType.COAL);
        
        assertTrue(pp.canPowerCities());
    }
}
