package ro.powergrid.turn;

import ro.powergrid.plant.PowerPlant;

public interface Turn {
	public Turn getNewTurn();
	public Turn getNewPhase();
	public boolean hasNewPhase();
	public Phase getCurrentPhase();
	public boolean hasFired(PowerPlant plant);
	public int getMaximumNumberOfCitiesPowered();
	void setFired(PowerPlant plant);
}
