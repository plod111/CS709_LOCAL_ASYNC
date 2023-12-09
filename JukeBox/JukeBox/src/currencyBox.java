public class currencyBox {
    private int pennies = 0;
    private int nickels = 0;
    private int dimes = 0;
    private int quarters = 0;
    private int halfDollars = 0;
    private int goldenDollars = 0;
    private int totalCoinsAmount = 0;
    private int creditAmountInt = 0;


    public void resetAllCoins() {
        this.pennies = 0;
        this.nickels = 0;
        this.dimes = 0;
        this.quarters = 0;
        this.halfDollars = 0;
        this.goldenDollars = 0;
        this.totalCoinsAmount = 0;
        this.creditAmountInt = 0;
    }

    public void resetCreditAmountInt() {
        this.creditAmountInt = 0;
    }

    public void setCreditAmountInt(int creditAmountInt) {
        this.creditAmountInt += creditAmountInt;
    }

    public int getCreditAmountInt() {
        return this.creditAmountInt;
    }

    private void updateTotalCoinsAmount() {
        this.totalCoinsAmount = pennies*1 + nickels*5 + dimes*10 + quarters*25 + halfDollars*50 + goldenDollars*100;
    }   

    public void setPennies() {
        this.pennies += 1;
        updateTotalCoinsAmount();
    }

    public void setNickels() {
        this.nickels += 1;
        updateTotalCoinsAmount();
    }

    public void setDimes() {
        this.dimes += 1;
        updateTotalCoinsAmount();
    }

    public void setQuarters() {
        this.quarters += 1;
        updateTotalCoinsAmount();
    }

    public void setHalfDollars() {
        this.halfDollars += 1;
        updateTotalCoinsAmount();
    }
    
    public void setGoldenDollars() {
        this.goldenDollars += 1;    
        updateTotalCoinsAmount();
    }


    public int getTotalCoinsAmount() {
        return this.totalCoinsAmount;
    }

    public int getPennies() {
        return this.pennies;
    }

    public int getNickels() {
        return this.nickels;
    }

    public int GetDimes() {
        return this.dimes;
    }   

    public int getQuarters() {
        return this.quarters;
    }

    public int getHalfDollars() {
        return this.halfDollars;
    }

    public int getGoldenDollars() {
        return this.goldenDollars;
    }

}
