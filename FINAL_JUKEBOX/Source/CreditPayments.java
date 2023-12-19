/**
 * CreditPayments.java - Fall 2023
 * 
 * This class implements the Payments interface and handles the credit payments
 * 
 * CS709 Hunter Fall 2023 - Final Project
 * 
 * @author P.Chu
 * @date Dec 2023
 * 
 */
public class CreditPayments implements Payments{
    

    CurrencyBox creditCurrencyBox = new CurrencyBox();
        
    /**
     * This method adds the amount of credit to the currency box
     */
    @Override
    public void addFunds(int amount) {
        creditCurrencyBox.setCreditAmountInt(amount);
        
    }

    /**
     * This method returns the amount of credit in the currency box
     */
    @Override
    public String returnFunds() {
        return Integer.toString(creditCurrencyBox.getCreditAmountInt());
    }


}
