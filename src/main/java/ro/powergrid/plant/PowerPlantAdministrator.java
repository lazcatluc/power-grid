/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ro.powergrid.plant;

import java.io.Serializable;

import javax.faces.bean.ManagedProperty;

import ro.powergrid.turn.Turn;
import ro.powergrid.turn.TurnProvider;

/**
 *
 * @author Catalin
 */
public abstract class PowerPlantAdministrator implements Serializable {
	
	private static final long serialVersionUID = -841220624016824861L;

	public static final int PLANT_STORAGE_FACTOR = 2;
    
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
}
