package ro.powergrid.turn;

import java.io.Serializable;

import static ro.powergrid.turn.Phase.*;

public class TurnImpl implements Turn, Serializable, Cloneable {
	
	private static final long serialVersionUID = 1L;
	
	private static final Phase[] ORDERED_PHASES = {PLANTS, RESOURCES, CITIES, POWER}; 
	
	private final int turnId;
	private int phaseId;
	
	public TurnImpl() {
		turnId=0;
	}
	
	public TurnImpl(int turnId) {
		this.turnId=turnId;
	}

	@Override
	public TurnImpl setNewTurn() {
		return new TurnImpl(turnId+1);
	}

	@Override
	public Phase setNewPhase() {
		phaseId++;
		return getCurrentPhase();
	}

	@Override
	public Phase getCurrentPhase() {
		return ORDERED_PHASES[phaseId];
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + turnId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TurnImpl other = (TurnImpl) obj;
		if (turnId != other.turnId)
			return false;
		return true;
	}

	

}
