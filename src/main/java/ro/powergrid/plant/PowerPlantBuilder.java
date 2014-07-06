/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ro.powergrid.plant;

import java.util.HashSet;
import java.util.Set;
import ro.powergrid.resource.ResourceType;

/**
 *
 * @author Catalin
 */
public class PowerPlantBuilder {
    private int basePrice;
    private int necessaryResources;
    private Set<ResourceType> resourceTypes = new HashSet<>();
    
    public PowerPlantBuilder withBasePrice(int basePrice) {
        this.basePrice = basePrice;
        return this;
    }
    
    public PowerPlantBuilder withResourceType(ResourceType type) {
        resourceTypes.add(type);
        return this;
    }
    
    public PowerPlantBuilder withNecessaryResources(int necessaryResources) {
        this.necessaryResources = necessaryResources;
        return this;
    }
    
    public PowerPlant build() {
        return new PowerPlant(basePrice, necessaryResources, resourceTypes);
    }
    
    public static PowerPlant three() {
        return new PowerPlantBuilder().withBasePrice(3)
                .withNecessaryResources(2).withResourceType(ResourceType.OIL).build();
    }
    
    public static PowerPlant four() {
        return new PowerPlantBuilder().withBasePrice(4)
                .withNecessaryResources(2).withResourceType(ResourceType.COAL).build();
    }
    
    public static PowerPlant five() {
        return new PowerPlantBuilder().withBasePrice(5)
                .withNecessaryResources(2)
                .withResourceType(ResourceType.COAL)
                .withResourceType(ResourceType.OIL).build();
    }
}
