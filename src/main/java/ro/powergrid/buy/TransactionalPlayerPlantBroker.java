/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ro.powergrid.buy;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import ro.powergrid.plant.PowerPlant;
import ro.powergrid.player.PlantOwner;

/**
 *
 * @author Catalin
 */
@Local
@Stateless
public class TransactionalPlayerPlantBroker implements PlayerPlantBroker, Serializable {    
    
	private static final long serialVersionUID = 542104236219151109L;

	@EJB
	private PaymentProcessor paymentProcessor;
	
	public void transferPlant(PlantOwner plantOwner, PowerPlant powerPlant, 
			PlantMarketplace plantMarketplace) {
        plantOwner.add(powerPlant);
        getPaymentProcessor().charge(plantOwner, powerPlant.getBasePrice());
        plantMarketplace.removeBuyablePlant(powerPlant);
    }

	public PaymentProcessor getPaymentProcessor() {
		return paymentProcessor;
	}

	public void setPaymentProcessor(PaymentProcessor paymentProcessor) {
		this.paymentProcessor = paymentProcessor;
	}
    
}
