package ro.powergrid.turn;

import ro.powergrid.plant.PowerPlant;

public interface Turn {
	Turn getNewTurn();
	Turn getNewPhase();
	boolean hasNewPhase();
	Phase getCurrentPhase();
	boolean hasFired(PowerPlant plant);
	boolean isCanBuyPlant();
	void setCanBuyPlant(boolean canBuyPlant);
	public int getMaximumNumberOfCitiesPowered();
	void setFired(PowerPlant plant);
}
