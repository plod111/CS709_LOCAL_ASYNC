
public class CreditPayments implements Payments{
    

    CurrencyBox creditCurrencyBox = new CurrencyBox();
        
    
    @Override
    public void addFunds(int amount) {
        creditCurrencyBox.setCreditAmountInt(amount);
        
    }

    @Override
    public String returnFunds() {
        return Integer.toString(creditCurrencyBox.getCreditAmountInt());
    }


}
