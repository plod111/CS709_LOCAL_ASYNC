

public class CoinPayments implements Payments {

    CurrencyBox currencyBox = new CurrencyBox();

    @Override
    public void addFunds(int amount) {
        
        switch (amount) {
        
            case 1:
                currencyBox.setPennies();;
                break;
            
            case 5: 
                currencyBox.setNickels();
                break;
            
            case 10:
                currencyBox.setDimes();;
                break;

            case 25:
                currencyBox.setQuarters();
                break;

            case 50:
                currencyBox.setHalfDollars();
                break;

            case 100:
                currencyBox.setGoldenDollars();
                break;
    }


    }

    @Override
    public String returnFunds() {
        
        //builds a string specifying how much and how funds were returned
        return Integer.toString(currencyBox.getPennies()) + " pennies, " + 
        Integer.toString(currencyBox.getNickels()) + " nickels, " + 
        Integer.toString(currencyBox.GetDimes()) + " dimes, " + 
        Integer.toString(currencyBox.getQuarters()) + " quarters, " + 
        Integer.toString(currencyBox.getHalfDollars()) + " half dollars, " + 
        Integer.toString(currencyBox.getGoldenDollars()) + " golden dollars.";
    }

    public int getTotalCoinsAmount() {
        return currencyBox.getTotalCoinsAmount();
    }

}
