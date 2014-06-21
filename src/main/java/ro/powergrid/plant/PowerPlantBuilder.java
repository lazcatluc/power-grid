/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ro.powergrid.plant;

/**
 *
 * @author Catalin
 */
public class PowerPlantBuilder {
    private int basePrice;
    private int necessaryResources;
    
    public PowerPlantBuilder withBasePrice(int basePrice) {
        this.basePrice = basePrice;
        return this;
    }
    
    public PowerPlantBuilder withNecessaryResources(int necessaryResources) {
        this.necessaryResources = necessaryResources;
        return this;
    }
    
    public PowerPlant build() {
        return new PowerPlant(basePrice, necessaryResources);
    }
    
    public static PowerPlant three() {
        return new PowerPlantBuilder().withBasePrice(3)
                .withNecessaryResources(2).build();
    }
}
