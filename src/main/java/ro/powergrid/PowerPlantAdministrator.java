/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ro.powergrid;

/**
 *
 * @author Catalin
 */
public class PowerPlantAdministrator {
    public void firePlant(PowerPlant plant) {
        plant.consumeResources(plant.getNumberOfNecessaryResources());
    }
}
