/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ro.powergrid.resource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Catalin
 */

@ManagedBean(name = "activePlants", eager = true)
@SessionScoped
public class ActivePlants implements Serializable {
    private static final long serialVersionUID = 1l;
    
    private List<ActiveResource> activeResource = new ArrayList<>();
    
    public ActivePlants() {
        activeResource.add(new ActiveResource(new ResourceType("Oil")));
        activeResource.add(new ActiveResource(new ResourceType("Coal")));
    }
    
    public ActiveResource getResource(int position) {
        return activeResource.get(position);
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
}
