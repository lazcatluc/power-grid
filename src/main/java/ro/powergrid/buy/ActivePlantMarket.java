package ro.powergrid.buy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import ro.powergrid.plant.PowerPlant;

@ManagedBean(name="plantMarket")
@ApplicationScoped
public class ActivePlantMarket implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private PlantMarketplace plantMarketplace;
	
	public List<PowerPlant> getBuyablePlants() {
		List<PowerPlant> buyable = new ArrayList<>(plantMarketplace.getBuyablePlants());
		buyable.remove(PowerPlant.NONE);
		return buyable;
	}
	
	public List<PowerPlant> getFuturePlants() {
		List<PowerPlant> future = new ArrayList<>(plantMarketplace.getFuturePlants());
		future.remove(PowerPlant.NONE);
		return future;
	}

	public PlantMarketplace getPlantMarketplace() {
		return plantMarketplace;
	}

	public void setPlantMarketplace(PlantMarketplace plantMarketplace) {
		this.plantMarketplace = plantMarketplace;
	}

}
