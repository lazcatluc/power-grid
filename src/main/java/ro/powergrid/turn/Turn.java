package ro.powergrid.turn;

public interface Turn {
	public Turn setNewTurn();
	public Phase setNewPhase();
	
	public Phase getCurrentPhase();
}
