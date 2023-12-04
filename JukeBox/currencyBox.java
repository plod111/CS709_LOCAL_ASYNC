package JukeBox;


public class currencyBox {
    private int pennies = 0;
    private int nickels = 0;
    private int dimes = 0;
    private int quarters = 0;
    private int halfDollars = 0;
    private int goldenDollars = 0;
    private int credit = 0;

    switch (coinPayments.addFunds()) {
        
        case 1:
            pennies += 1;
            break;
        
        case 5: 
            nickels += 1;
            break;
        
        case 10:
            dimes += 1;
            break;

        case 25:
            quarters += 1;
            break;

        case 50:
            halfDollars += 1;
            break;
    }

    
}
