/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ro.powergrid.plant;

import static org.mockito.Mockito.*;
import ro.powergrid.plant.PowerPlant;
import ro.powergrid.plant.PowerPlantAdministrator;
import ro.powergrid.plant.PowerPlantBuilder;
import static org.junit.Assert.*;

import org.junit.Test;

import ro.powergrid.resource.ResourceType;
import ro.powergrid.turn.Phase;
import ro.powergrid.turn.Turn;


/**
 *
 * @author Catalin
 */
public class ShouldBeAbleToPowerPlantTest {
	
	private PowerPlantAdministrator getAdministrator() {
		PowerPlantAdministrator ppa = new PowerPlantAdministrator();
		Turn turn = mock(Turn.class);
		ppa.setTurn(turn);
		when(turn.getCurrentPhase()).thenReturn(Phase.POWER);
		return ppa;
	}
    
    @Test
    public void powerPlant3CanBePoweredWith2Oil() throws Exception {
        PowerPlant pp = PowerPlantBuilder.three();
        pp.addEnergyResources(2, ResourceType.OIL);
        
        assertTrue(pp.canPowerCities());
    }
    
    @Test
    public void newPowerPlant3HasNoEnergyResources() throws Exception {
        PowerPlant pp = PowerPlantBuilder.three();
        
        assertTrue(pp.getEnergyResources().isEmpty());
    }
    
    @Test
    public void newPowerPlant3Has1ResourceTypeAfterAddingResources() throws Exception {
        PowerPlant pp = PowerPlantBuilder.three();
        pp.addEnergyResources(3, ResourceType.OIL);
        
        assertEquals(1, pp.getEnergyResources().size());
    }
    
    @Test   
    public void powerPlant3Keeps1OilAfterPoweringCitiesWith3Oil() 
            throws Exception {
        PowerPlant pp = PowerPlantBuilder.three();
        pp.addEnergyResources(3, ResourceType.OIL);
        
        getAdministrator().firePlant(pp);
        
        assertEquals(1, pp.getEnergyResources().size());
        assertEquals(1, pp.getTotalResourcesStored());
    }
    
    @Test
    public void havingZeroOilOnPlant3CannotPowerCities() throws Exception {
        PowerPlant pp = PowerPlantBuilder.three();
        
        assertFalse(pp.canPowerCities());
    }
    
    @Test
    public void havingOneOilOnPlant3CannotPowerCities() throws Exception {
        PowerPlant pp = PowerPlantBuilder.three();
        pp.addEnergyResources(1, ResourceType.OIL);
        
        assertFalse(pp.canPowerCities());
    }
}
