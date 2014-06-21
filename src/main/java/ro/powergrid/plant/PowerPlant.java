/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.powergrid.plant;

import java.util.ArrayList;
import java.util.Collection;
import ro.powergrid.resource.Resource;
import ro.powergrid.resource.ResourceType;

/**
 *
 * @author Catalin
 */
public class PowerPlant {

    private final int basePrice;
    private final int numberOfNecessaryResources;
    private Collection<Resource> energyResources;

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
    public void addEnergyResources(int howMany, ResourceType resource) {
        getEnergyResources().add(new Resource(howMany, resource));
    }

    public boolean canPowerCities() {
        return getNumberOfNecessaryResources() <= getTotalResourcesStored();
    }

    public int getTotalResourcesStored() {
        return getEnergyResources().stream().mapToInt(Resource::getValue).sum();
    }

    /**
     * @return the numberOfNecessaryResources
     */
    public int getNumberOfNecessaryResources() {
        return numberOfNecessaryResources;
    }

    void consumeResources(int numberOfNecessaryResources) {
        while (!energyResources.isEmpty()) {
            final Resource nextResources = getEnergyResources().iterator().next();
            getEnergyResources().remove(nextResources);
            if (nextResources.getValue() > numberOfNecessaryResources) {
                getEnergyResources().add(new Resource(nextResources.getValue()
                        - numberOfNecessaryResources, nextResources.getResourceType()));
                return;
            }
            numberOfNecessaryResources -= nextResources.getValue();
        }
    }

    /**
     * @return the energyResources
     */
    public Collection<Resource> getEnergyResources() {
        return energyResources;
    }
}
