
public class CoinPayments implements Payments {

    CurrencyBox currencyBox = new CurrencyBox();

    @Override
    public void addFunds(int amount) {
        
        currencyBox.setCoinAmountInt(amount);


    }

    @Override
    public String returnFunds() {
        
        //builds a string specifying how much and how funds were returned
        return  
        Integer.toString(currencyBox.getRefundNickels()) + " nickels, " + 
        Integer.toString(currencyBox.getRefundDimes()) + " dimes, " + 
        Integer.toString(currencyBox.getRefundQuarters()) + " quarters, ";
    }

    public int getTotalCoinsAmount() {
        return currencyBox.getTotalCoinsAmount();
    }

}
