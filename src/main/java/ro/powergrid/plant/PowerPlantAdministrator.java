/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ro.powergrid.plant;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author Catalin
 */
@ManagedBean(name = "firePlant", eager = true)
public class PowerPlantAdministrator {
    public void firePlant(PowerPlant plant) {
        plant.consumeResources(plant.getNumberOfNecessaryResources());
    }
}
