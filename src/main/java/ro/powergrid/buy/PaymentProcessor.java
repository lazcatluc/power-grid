/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ro.powergrid.buy;

import ro.powergrid.player.PlantOwner;


/**
 *
 * @author Catalin
 */
public interface PaymentProcessor {
    void charge(PlantOwner owner, int sum);
}
