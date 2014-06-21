/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.powergrid.plant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import ro.powergrid.resource.Resource;
import ro.powergrid.resource.ResourceType;

/**
 *
 * @author Catalin
 */
public class PowerPlant implements Serializable {
    private static final long serialVersionUID = 1l;

    private final int basePrice;
    private final int numberOfNecessaryResources;
    private Collection<Resource> energyResources;
    private Set<ResourceType> acceptableResourceTypes;

    public PowerPlant(int basePrice, int numberOfNecessaryResources, 
            Set<ResourceType> acceptableResourceTypes) {
        this.basePrice = basePrice;
        this.numberOfNecessaryResources = numberOfNecessaryResources;
        this.energyResources = new ArrayList<>();
        this.acceptableResourceTypes = acceptableResourceTypes;
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

    /**
     * @return the acceptableResourceTypes
     */
    public Collection<ResourceType> getAcceptableResourceTypes() {
        return acceptableResourceTypes;
    }

    public boolean acceptsResourceType(ResourceType type) {
        return acceptableResourceTypes.contains(type);
    }
}
