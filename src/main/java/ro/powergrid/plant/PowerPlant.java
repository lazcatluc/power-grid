/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.powergrid.plant;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashSet;
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
    private final int numberOfCitiesPowered;
    private Collection<Resource> energyResources;
    private Set<ResourceType> acceptableResourceTypes;

    public PowerPlant(int basePrice, int numberOfNecessaryResources,
    		int numberOfCitiesPowered,
            Set<ResourceType> acceptableResourceTypes) {
        this.basePrice = basePrice;
        this.numberOfNecessaryResources = numberOfNecessaryResources;
        this.numberOfCitiesPowered = numberOfCitiesPowered;
        this.energyResources = new UpdateableResourceList();
        this.acceptableResourceTypes = new LinkedHashSet<>(acceptableResourceTypes);
    }

    /**
     *
     * @param howMany
     * @param resource
     */
    void addEnergyResources(int howMany, ResourceType resource) {
		getEnergyResources().add(new Resource(howMany, resource));
    }

    public boolean canPowerCities() {
        return getNumberOfNecessaryResources() <= getTotalResourcesStored();
    }

    public int getTotalResourcesStored() {
        int sum = 0;
        for (Resource resource : getEnergyResources()) {
            sum +=resource.getValue();
        }
        return sum;
    }

    /**
     * @return the numberOfNecessaryResources
     */
    public int getNumberOfNecessaryResources() {
        return numberOfNecessaryResources;
    }

    void consumeResources(int numberOfNecessaryResources) {
    	getEnergyResources().remove(
    			new Resource(numberOfNecessaryResources, ResourceType.NONE));
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

    /**
     * @return the basePrice
     */
    public int getBasePrice() {
        return basePrice;
    }
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + basePrice;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerPlant other = (PowerPlant) obj;
		if (basePrice != other.basePrice)
			return false;
		return true;
	}

	public int getNumberOfCitiesPowered() {
		return numberOfCitiesPowered;
	}    
}
