public class CurrencyBox {
    private int nickels = 0;
    private int dimes = 0;
    private int quarters = 0;
    private int totalCoinsAmount = 0;
    private int creditAmountInt = 0;
    private int refundNickels = 0;
    private int refundDimes = 0;
    private int refundQuarters = 0;


    
    public void resetAllCoins() {
        this.nickels = 0;
        this.dimes = 0;
        this.quarters = 0;
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
        this.totalCoinsAmount =  nickels*5 + dimes*10 + quarters*25;
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



    public int getTotalCoinsAmount() {
        return this.totalCoinsAmount;
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

    
    public void setRefundAmount() {
        if (totalCoinsAmount/25 > 0) {
            refundQuarters = totalCoinsAmount / 25;
            totalCoinsAmount = totalCoinsAmount % 25;
        }
        if (totalCoinsAmount/10 > 0) {
            refundDimes = totalCoinsAmount/10;
            totalCoinsAmount = totalCoinsAmount % 10;  
        }
        if (totalCoinsAmount/5 > 0) {
            refundNickels = totalCoinsAmount/5;    
        }
    }

    public int getRefundNickels() {
        return this.refundNickels;
    }

    public int getRefundDimes() {
        return this.refundDimes;
    }

    public int getRefundQuarters() {
        return this.refundQuarters;
    }
    


}