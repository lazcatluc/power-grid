/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ro.powergrid.buy;

import ro.powergrid.plant.PowerPlant;
import ro.powergrid.resource.PaymentProcessor;
import ro.powergrid.resource.PlantMarketplace;
import ro.powergrid.resource.PlantOwner;

/**
 *
 * @author Catalin
 */
public class PlayerPlantBroker {
    private PlantOwner plantOwner;
    private PlantMarketplace plantMarketplace;
    private PaymentProcessor paymentProcessor;
    private PowerPlant powerPlant;
    
    public void transferPlant() {
        plantOwner.add(powerPlant);
        paymentProcessor.charge(plantOwner, powerPlant.getBasePrice());
        plantMarketplace.removeBuyablePlant(powerPlant);
    }

    /**
     * @return the plantOwner
     */
    public PlantOwner getPlantOwner() {
        return plantOwner;
    }

    /**
     * @param plantOwner the plantOwner to set
     */
    public void setPlantOwner(PlantOwner plantOwner) {
        this.plantOwner = plantOwner;
    }

    /**
     * @return the powerPlant
     */
    public PowerPlant getPowerPlant() {
        return powerPlant;
    }

    /**
     * @param powerPlant the powerPlant to set
     */
    public void setPowerPlant(PowerPlant powerPlant) {
        this.powerPlant = powerPlant;
    }

    /**
     * @return the plantMarketplace
     */
    public PlantMarketplace getPlantMarketplace() {
        return plantMarketplace;
    }

    /**
     * @param plantMarketplace the plantMarketplace to set
     */
    public void setPlantMarketplace(PlantMarketplace plantMarketplace) {
        this.plantMarketplace = plantMarketplace;
    }
}
