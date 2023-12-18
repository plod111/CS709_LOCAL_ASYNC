
public class CoinPayments implements Payments {

    CurrencyBox currencyBox = new CurrencyBox();

    @Override
    public void addFunds(int amount) {
        
        switch (amount) {
        
            
            case 5: 
                currencyBox.setNickels();
                break;
            
            case 10:
                currencyBox.setDimes();;
                break;

            case 25:
                currencyBox.setQuarters();
                break;

    }


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
