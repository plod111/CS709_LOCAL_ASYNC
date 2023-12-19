/**
 * Payments.java - Fall 2023
 * 
 * This interface class defines two minimum methods for payments
 * 
 * CS709 Hunter Fall 2023 - Final Project
 * 
 * @author P.Chu
 * @date Dec 2023
 * 
 */

public interface Payments {

    // add funds to the currency box
    void addFunds(int amount);

    // return funds from the currency box
    String returnFunds();
}
