/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ro.powergrid.plant;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import ro.powergrid.resource.Resource;
import ro.powergrid.resource.ResourceType;
import ro.powergrid.turn.Phase;
import ro.powergrid.turn.Turn;

/**
 *
 * @author Catalin
 */
public abstract class PowerPlantAdministrator implements Serializable {
	
	private static final long serialVersionUID = -841220624016824861L;

	public static final int PLANT_STORAGE_FACTOR = 2;
    
    @Inject
    private Turn turn;

	public Turn getTurn() {
		return turn;
	}

	public void setTurn(Turn turn) {
		this.turn = turn;
	}
}
