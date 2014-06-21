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
//@Stateless
public class PlayerPlantBroker {
    //@Inject
    private PlantMarketplace plantMarketplace;
    //@Inject
    private PaymentProcessor paymentProcessor;
    
    
    public void transferPlant(PlantOwner plantOwner, PowerPlant powerPlant) {
        plantOwner.add(powerPlant);
        paymentProcessor.charge(plantOwner, powerPlant.getBasePrice());
        plantMarketplace.removeBuyablePlant(powerPlant);
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
