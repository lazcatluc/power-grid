/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ro.powergrid.resource;

import java.util.Collection;
import ro.powergrid.plant.PowerPlant;

/**
 *
 * @author Catalin
 */
public interface PlantMarketplace {
    Collection<PowerPlant> getBuyablePlants();
    Collection<PowerPlant> getFuturePlants();
    
    void removeBuyablePlant(PowerPlant plant);
}
