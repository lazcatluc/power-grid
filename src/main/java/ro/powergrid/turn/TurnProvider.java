package ro.powergrid.turn;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name="turn")
@ApplicationScoped
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

	public void waitForPlayerToScrapPlant() {
		turn = new ScrapPlantTurn(turn);
	}
	
	public void scrappedPlant() {
		turn = ((ScrapPlantTurn)turn).getPreviousTurn();
	}
}
