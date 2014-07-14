package ro.powergrid.turn;

import java.io.Serializable;

import ro.powergrid.plant.PowerPlant;

public class ScrapPlantTurn implements Turn, Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Turn where we should return after scrapping
	 * plant.
	 */
	private final Turn previousTurn;
	
	public ScrapPlantTurn(Turn previousTurn) {
		this.previousTurn = previousTurn;
	}

	@Override
	public Turn getNewTurn() {
		return this;
	}

	@Override
	public Turn getNewPhase() {
		return this;
	}

	@Override
	public boolean hasNewPhase() {
		return false;
	}

	@Override
	public Phase getCurrentPhase() {
		return Phase.PLANTS;
	}

	@Override
	public boolean hasFired(PowerPlant plant) {
		return getPreviousTurn().hasFired(plant);
	}

	@Override
	public boolean isCanBuyPlant() { 
		return false;
	}

	@Override
	public void setCanBuyPlant(boolean canBuyPlant) {
		getPreviousTurn().setCanBuyPlant(canBuyPlant);
	}

	@Override
	public int getMaximumNumberOfCitiesPowered() {
		return getPreviousTurn().getMaximumNumberOfCitiesPowered();
	}

	@Override
	public void setFired(PowerPlant plant) {
		getPreviousTurn().setFired(plant);
	}

	public Turn getPreviousTurn() {
		return previousTurn;
	}

}
