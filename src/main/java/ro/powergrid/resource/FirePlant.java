package ro.powergrid.resource;

import ro.powergrid.plant.PowerPlant;
import ro.powergrid.plant.PowerPlantAdministrator;
import ro.powergrid.plant.PowerPlantBuilder;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.Map;

/**
 * Created by alin on 6/21/14.
 */
@ManagedBean(name = "firePlant", eager = true)
public class FirePlant {

    private PowerPlant plantThree = PowerPlantBuilder.three();

    public String firePlantThree(){
        new PowerPlantAdministrator().firePlant(plantThree);
        return "index";
    }

    public PowerPlant getPlantThree() {
        return plantThree;
    }

    public void setPlantThree(PowerPlant plantThree) {
        this.plantThree = plantThree;
    }
}