/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ro.powergrid.plant;

import ro.powergrid.resource.Energy;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Catalin
 */
public class PowerPlant {
    
    private final int basePrice;
    private final int numberOfNecessaryResources;
    private Collection<Energy> energyResources;
    
    public PowerPlant(int basePrice, int numberOfNecessaryResources) {
        this.basePrice = basePrice;
        this.numberOfNecessaryResources = numberOfNecessaryResources;
        this.energyResources = new ArrayList<>();
    }
    
    /**
     *
     * @param howMany
     * @param resource
     */
    public void addEnergyResources(int howMany, Energy resource) {
        for (int i = 0; i < howMany; i++) {
            energyResources.add(resource);
        }
    }
    
    public boolean canPowerCities() {
        return getNumberOfNecessaryResources() <= energyResources.size();
    }

    public int getTotalResourcesStored() {
        return energyResources.size();
    }

    /**
     * @return the numberOfNecessaryResources
     */
    public int getNumberOfNecessaryResources() {
        return numberOfNecessaryResources;
    }

    void consumeResources(int numberOfNecessaryResources) {
        for (int i = 0; i< numberOfNecessaryResources; i++) {
            energyResources.remove(energyResources.iterator().next());
        }
    }
}
