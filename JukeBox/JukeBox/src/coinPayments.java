package JukeBox.JukeBox.src;

public class coinPayments implements payments {

    currencyBox box = new currencyBox();

    @Override
    public void addFunds(int amount) {
        // TODO Auto-generated method stub
        switch (amount) {
        
            case 1:
                box.setPennies();;
                break;
            
            case 5: 
                box.setNickels();
                break;
            
            case 10:
                box.setDimes();;
                break;

            case 25:
                box.setQuarters();
                break;

            case 50:
                box.setHalfDollars();
                break;

            case 100:
                box.setGoldenDollars();
                break;
    }


    }

    @Override
    public String returnFunds() {
        // TODO Auto-generated method stub
        return null;
    }
}
