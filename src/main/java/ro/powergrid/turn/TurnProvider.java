package ro.powergrid.turn;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="turn")
@SessionScoped
public class TurnProvider implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Turn turn = new TurnImpl();
	
	public Turn getTurn() {
		return turn;
	}
	
	public void nextTurn() {
		turn = turn.getNewTurn();
	}
	
	public void nextPhase() {
		turn = turn.getNewPhase();
	}
}
