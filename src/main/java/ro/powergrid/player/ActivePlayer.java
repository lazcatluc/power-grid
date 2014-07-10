package ro.powergrid.player;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import ro.powergrid.InvalidPhaseActionException;
import ro.powergrid.buy.ActivePlantMarket;
import ro.powergrid.buy.PlayerPlantBroker;
import ro.powergrid.plant.PowerPlant;
import ro.powergrid.resource.ActivePlants;
import ro.powergrid.resource.Resource;
import ro.powergrid.resource.ResourceType;
import ro.powergrid.turn.Phase;
import ro.powergrid.turn.Turn;
import ro.powergrid.turn.TurnProvider;

@ManagedBean(name = "activePlayer", eager = true)
@SessionScoped
public class ActivePlayer implements Serializable {
	
    private static final long serialVersionUID = 1l;
	private Map<Resource, Integer> resourcesMap = new HashMap<Resource, Integer>();
	private ResourceType resourceToBuyType;
	private int resourceToBuyQuantity;
	
	@EJB
	private PlayerPlantBroker playerPlantBroker;
	
	@ManagedProperty(value="#{plantMarket}")
	private ActivePlantMarket activePlantMarket;
	
	@ManagedProperty(value="#{activePlants}")
	private ActivePlants activePlants;
	
	@ManagedProperty(value="#{turn}")
    private TurnProvider turnProvider;

	public Turn getTurn() {
		return turnProvider.getTurn();
	}
	
	public TurnProvider getTurnProvider() {
		return turnProvider;
	}

	public void setTurnProvider(TurnProvider turnProvider) {
		this.turnProvider = turnProvider;
	}
	
	@Inject
	private Player currentPlayer;
	
	public boolean canBuyPlants() {
		return getTurn().getCurrentPhase() == Phase.PLANTS && 
				getTurn().isCanBuyPlant();
	}
	
	public void buyPlant(PowerPlant powerPlant) throws InvalidPhaseActionException {
		if (!canBuyPlants()) {
			throw new InvalidPhaseActionException(
					getTurn().getCurrentPhase().toString());
		}
		playerPlantBroker.transferPlant(currentPlayer, powerPlant, 
			activePlantMarket.getPlantMarketplace());
		activePlants.addPlant(powerPlant);
		getTurn().setCanBuyPlant(false);
	}
	
	public void buyResources(ResourceType resourceToBuyType, int resourceToBuyAmount) {
		int initialResourceAmount = 1;
		Resource resource = new Resource(initialResourceAmount, resourceToBuyType);
		
		if (resourcesMap.containsKey(resource))
		{
            Integer resourceAmount = resourcesMap.get(resource);
            int initialAmount = resourceAmount.intValue();
            int currentAmount = initialAmount + resourceToBuyAmount;
            resourcesMap.put(resource, currentAmount);
		}
		else
		{
			resourcesMap.put(resource, resourceToBuyAmount);
		}
	}
	
	public Map<Resource, Integer> getResources()
	{
		return resourcesMap;
	}

    public int getResourcesOfType(ResourceType resourceType){
        int amountDoesntMatter = 1;
        return (getResources().get(new Resource(amountDoesntMatter, resourceType))).intValue();
    }
	
	public void setResourceToBuyType(ResourceType resourceType)
	{
		resourceToBuyType = resourceType;
	}
	
	public ResourceType getResourceToBuyType() {
		return resourceToBuyType;
	}

	public int getResourceToBuyQuantity() {
		return resourceToBuyQuantity;
	}

	public void setResourceToBuyQuantity(int quantity)
	{
		resourceToBuyQuantity = quantity;
	}

	public PlayerPlantBroker getPlayerPlantBroker() {
		return playerPlantBroker;
	}

	public void setPlayerPlantBroker(PlayerPlantBroker playerPlantBroker) {
		this.playerPlantBroker = playerPlantBroker;
	}

	public ActivePlantMarket getActivePlantMarket() {
		return activePlantMarket;
	}

	public void setActivePlantMarket(ActivePlantMarket activePlantMarket) {
		this.activePlantMarket = activePlantMarket;
	}

	public ActivePlants getActivePlants() {
		return activePlants;
	}

	public void setActivePlants(ActivePlants activePlants) {
		this.activePlants = activePlants;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}	
}