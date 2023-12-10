

public class creditPayments implements payments{
    

    currencyBox creditCurrencyBox = new currencyBox();
        
    
    @Override
    public void addFunds(int amount) {
        creditCurrencyBox.setCreditAmountInt(amount);
        
    }

    @Override
    public String returnFunds() {
        return Integer.toString(creditCurrencyBox.getCreditAmountInt());
    }


}
