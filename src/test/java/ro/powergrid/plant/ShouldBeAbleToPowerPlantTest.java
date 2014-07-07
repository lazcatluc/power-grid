/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ro.powergrid.plant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;

import ro.powergrid.resource.ResourceType;
import ro.powergrid.turn.Phase;
import ro.powergrid.turn.Turn;


/**
 *
 * @author Catalin
 */
public class ShouldBeAbleToPowerPlantTest extends AdministratorTestAbstract<PlantFirer> {
	
	@Override
	public void setTurn(Turn turn) {
		super.setTurn(turn);
		when(getTurn().getCurrentPhase()).thenReturn(Phase.POWER);
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
        
        getPowerPlantAdministrator().firePlant(pp);
        
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

	@Override
	protected PlantFirer makeAdministrator() {
		return new PlantFirer();
	}
}
