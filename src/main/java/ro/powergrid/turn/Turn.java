package ro.powergrid.turn;

import ro.powergrid.plant.PowerPlant;

public interface Turn {
	public Turn setNewTurn();
	public Phase setNewPhase();
	
	public Phase getCurrentPhase();
	public boolean hasFired(PowerPlant plant);
	void setFired(PowerPlant plant);
}
