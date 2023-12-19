/**
 * CurrencyBox.java - Fall 2023
 * 
 * This class implements the Payments interface and handles the credit payments
 * 
 * CS709 Hunter Fall 2023 - Final Project
 * 
 * @author P.Chu
 * @date Dec 2023
 * 
 */

public class CurrencyBox {

    // valid coins are nickels, dimes, and quarters
    private int nickels = 0;
    private int dimes = 0;
    private int quarters = 0;

    // total amount of coins in the currency box
    private int totalCoinsAmount = 0;
    // total amount of credit in the currency box
    private int creditAmountInt = 0;

    // amount of coins to be refunded
    private int refundNickels = 0;
    private int refundDimes = 0;
    private int refundQuarters = 0;

    /**
     * This method resets all the coins in the currency box
     */
    public void resetAllCoins() {
        this.nickels = 0;
        this.dimes = 0;
        this.quarters = 0;
        this.totalCoinsAmount = 0;
        this.creditAmountInt = 0;
    }

    /**
     * This method resets the credit amount in the currency box
     */
    public void resetCreditAmountInt() {
        this.creditAmountInt = 0;
    }

    /**
     * This method increments credit in the currency box
     * @param creditAmountInt
     */
    public void setCreditAmountInt(int creditAmountInt) {
        this.creditAmountInt += creditAmountInt;
    }

    /**
     * This method increments coins amount in the currency box
     * @param coinAmountInt
     */
    public void setCoinAmountInt(int coinAmountInt) {
        this.totalCoinsAmount += coinAmountInt;
    }

    /**
     * This method returns the amount of credit in the currency box
     * @return the amount of credit in the currency box
     */
    public int getCreditAmountInt() {
        return this.creditAmountInt;
    }

    /**
     * This method updates the amount of coins in the currency box
     */
    private void updateTotalCoinsAmount() {
        this.totalCoinsAmount = nickels * 5 + dimes * 10 + quarters * 25;
    }

    /**
     * This method increments the number of nickels in the currency box
     */
    public void setNickels() {
        this.nickels += 1;
        updateTotalCoinsAmount();
    }

    /**
     * This method increments the number of dimes in the currency box
     */
    public void setDimes() {
        this.dimes += 1;
        updateTotalCoinsAmount();
    }

    /**
     * This method increments the number of quarters in the currency box
     */
    public void setQuarters() {
        this.quarters += 1;
        updateTotalCoinsAmount();
    }

    /**
     * This method returns the total amount of coins in the currency box
     * @return the total amount of coins in the currency box
     */
    public int getTotalCoinsAmount() {
        return this.totalCoinsAmount;
    }

    /**
     * This method returns the number of nickels in the currency box
     * @return the number of nickels in the currency box
     */
    public int getNickels() {
        return this.nickels;
    }

    /**
     * This method returns the number of dimes in the currency box
     * @return the number of dimes in the currency box
     */
    public int getDimes() {
        return this.dimes;
    }

    /**
     * This method returns the number of quarters in the currency box
     * @return the number of quarters in the currency box
     */
    public int getQuarters() {
        return this.quarters;
    }

    /**
     * This method sets the amount of coins to be refunded
     */
    public void setRefundAmount() {
        if (totalCoinsAmount / 25 > 0) {
            refundQuarters = totalCoinsAmount / 25;
            totalCoinsAmount = totalCoinsAmount % 25;
        }
        if (totalCoinsAmount / 10 > 0) {
            refundDimes = totalCoinsAmount / 10;
            totalCoinsAmount = totalCoinsAmount % 10;
        }
        if (totalCoinsAmount / 5 > 0) {
            refundNickels = totalCoinsAmount / 5;
        }
    }

    /**
     * This method returns the number of nickels to be refunded
     * @return the number of nickels to be refunded
     */
    public int getRefundNickels() {
        return this.refundNickels;
    }

    /**
     * This method returns the number of dimes to be refunded
     * @return the number of dimes to be refunded
     */
    public int getRefundDimes() {
        return this.refundDimes;
    }

    /**
     * This method returns the number of quarters to be refunded
     * @return the number of quarters to be refunded
     */
    public int getRefundQuarters() {
        return this.refundQuarters;
    }

}