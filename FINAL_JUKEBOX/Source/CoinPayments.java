/**
 * CoinPayments.java - Fall 2023
 * 
 * This class implements the Payments interface and handles the coin payments
 * 
 * CS709 Hunter Fall 2023 - Final Project
 * 
 * @author P.Chu
 * @date Dec 2023
 * 
 */


public class CoinPayments implements Payments {

    CurrencyBox currencyBox = new CurrencyBox();

    /**
     * This method adds the amount of coins to the currency box
     */
    @Override
    public void addFunds(int amount) {
        
        currencyBox.setCoinAmountInt(amount);


    }

    /**
     * This method returns the amount of coins in the currency box
     * @return the amount of coins in the currency box
     */
    @Override
    public String returnFunds() {
        
        //builds a string specifying how much and how funds were returned
        return  
        Integer.toString(currencyBox.getRefundNickels()) + " nickels, " + 
        Integer.toString(currencyBox.getRefundDimes()) + " dimes, " + 
        Integer.toString(currencyBox.getRefundQuarters()) + " quarters, ";
    }

    /**
     * This method returns the total amount of coins in the currency box
     * @return
     */
    public int getTotalCoinsAmount() {
        return currencyBox.getTotalCoinsAmount();
    }

}
