/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ro.powergrid.resource;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import ro.powergrid.plant.PowerPlant;
import ro.powergrid.plant.PowerPlantBuilder;

/**
 *
 * @author Catalin
 */
@ManagedBean(name = "activePlants", eager = true)
@SessionScoped
public class ActivePlants implements Serializable {
    private static final long serialVersionUID = 2l;
    
    private List<PowerPlant> plants = new ArrayList<>();
    private List<ActiveResource> activeResource = new ArrayList<>();
    
    public ActivePlants() {
        plants.add(PowerPlantBuilder.three()); 
        plants.add(PowerPlantBuilder.four());
        for (PowerPlant plant : plants) {
            activeResource.add(new ActiveResource(
                plant.getAcceptableResourceTypes().iterator().next()));
        };
    }
    
    public ActiveResource getResource(int position) {
        return activeResource.get(position);
    }
    
    public void updatePowerPlantResources(int position) {
        getResource(position).updatePowerPlantResources();
        getPlants().get(position).addEnergyResources(
                getResource(position).getAvailableResources(), 
                getPlants().get(position).getAcceptableResourceTypes()
                        .iterator().next());
    }

    /**
     * @return the activeResource
     */
    public List<ActiveResource> getActiveResource() {
        return activeResource;
    }

    /**
     * @param activeResource the activeResource to set
     */
    public void setActiveResource(List<ActiveResource> activeResource) {
        this.activeResource = activeResource;
    }

    /**
     * @return the plants
     */
    public List<PowerPlant> getPlants() {
        Logger.getLogger(ActivePlants.class.getName()).log(Level.INFO, "Plants: {0}", new Object[]{plants});
        Logger.getLogger(ActivePlants.class.getName()).log(Level.INFO, "Active: {0}", new Object[]{activeResource});
        return plants;
    }
}
