package ro.powergrid.buy;

import java.io.Serializable;

import javax.ejb.Local;
import javax.ejb.Stateless;

import ro.powergrid.player.PlantOwner;

@Local
@Stateless
public class TransactionalPaymentProcessor implements PaymentProcessor, Serializable {

	private static final long serialVersionUID = 5440871799037098935L;

	@Override
	public void charge(PlantOwner owner, int sum) {

	}

}
