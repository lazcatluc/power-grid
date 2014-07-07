package ro.powergrid.turn;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import ro.powergrid.plant.PowerPlant;
import static ro.powergrid.turn.Phase.*;

public class TurnImpl implements Turn, Serializable, Cloneable {
	
	private static final long serialVersionUID = 3L;
	
	private static final Phase[] ORDERED_PHASES = {/*PLANTS,*/ RESOURCES,/* CITIES,*/ POWER}; 
	
	private final int turnId;
	private final int phaseId;
	private int maximumNumberOfCitiesPowered;
	private final Set<PowerPlant> plantsFired = new HashSet<PowerPlant>();
	
	public TurnImpl() {
		turnId=0;
		phaseId=0;
	}
	
	public TurnImpl(int turnId) {
		this.turnId=turnId;
		phaseId=0;
	}
	
	public TurnImpl(int turnId, int phaseId) {
		this.turnId=turnId;
		this.phaseId=phaseId;
	}

	@Override
	public TurnImpl getNewTurn() {
		return new TurnImpl(turnId+1);
	}

	@Override
	public TurnImpl getNewPhase() {
		return new TurnImpl(turnId, phaseId+1);		
	}

	@Override
	public Phase getCurrentPhase() {
		return ORDERED_PHASES[phaseId];
	}

	@Override
	public boolean hasFired(PowerPlant plant) {
		return plantsFired.contains(plant);
	}

	@Override
	public void setFired(PowerPlant plant) {
		plantsFired.add(plant);
		maximumNumberOfCitiesPowered += plant.getNumberOfCitiesPowered();
	}

	@Override
	public boolean hasNewPhase() {
		return phaseId < ORDERED_PHASES.length-1;
	}

	@Override
	public int getMaximumNumberOfCitiesPowered() {
		return maximumNumberOfCitiesPowered;
	}

}
