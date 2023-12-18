import java.util.LinkedList;

public class PurchaseQueue extends LinkedList<Song> {
    
    // preimum cost for play next is 10c
    private int premiumCost = 10;

    /**
     * Constructor for objects of class PurchaseQueue
     */
    public PurchaseQueue() {
        super();
    }
    
    public int getPremiumCost() {
        return premiumCost;
    }

    public void setPremiumCost(int premiumCost) {
        this.premiumCost = premiumCost;
    }

    public LinkedList<Song> getQueue() {
        return this;
    }
    
  
    // add a song to the queue band check if there's enough funds to purchase it
    public void addSong(Song song, CurrencyBox creditCurrencyBox, CurrencyBox coinCurrencyBox) {
        if ((coinCurrencyBox.getTotalCoinsAmount() + creditCurrencyBox.getCreditAmountInt()) < song.getPrice()) {
            System.out.println("Not enough funds to purchase " + song.getTitle());
            return;
            //if there aren't enough coins, add the song to the queue and subtract the price from coins and then credit
        } else if (coinCurrencyBox.getTotalCoinsAmount() < song.getPrice()) {
            int difference = song.getPrice() - coinCurrencyBox.getTotalCoinsAmount();
            creditCurrencyBox.setCreditAmountInt(-difference);
            coinCurrencyBox.resetAllCoins();

            //if there's enough coins, add the song to the queue and subtract the price from coins
        } else {
            coinCurrencyBox.setCoinAmountInt(-song.getPrice());
        }
        this.add(song);
        System.out.println("Added " + song.getTitle() + " to the queue");
    }
    
    // add a song to the front of queue position 1 , so as to not push current playing song out
    // (check if there's enough funds to purchase it
    // Premium cost for this is 10c.
    public void addSongPlayNext(Song song, CurrencyBox creditCurrencyBox, CurrencyBox coinCurrencyBox) {
        if ((coinCurrencyBox.getTotalCoinsAmount() + creditCurrencyBox.getCreditAmountInt()) < (song.getPrice()+premiumCost)) {
            System.out.println("Not enough funds to purchase " + song.getTitle());
            return;
            // if there aren't enough coins, add the song to the queue and subtract the price from coins and then credit
        } else if (coinCurrencyBox.getTotalCoinsAmount() < (song.getPrice()+premiumCost)) {
            int difference = (song.getPrice()+premiumCost) - coinCurrencyBox.getTotalCoinsAmount();
            creditCurrencyBox.setCreditAmountInt(-difference);
            coinCurrencyBox.resetAllCoins();
           
            //if there's enough coins, add the song to the queue and subtract the price from coins
        }  else{
            coinCurrencyBox.setCoinAmountInt(-(song.getPrice()+premiumCost));
        }
        this.add(1,song); // add to position 1, behind current playing song
        System.out.println("Added," + song.getTitle() + " Playing Next");
    }

    //skip a song in the queue
    public void skipSong() {
        if (this.size() == 0) {
            System.out.println("No songs in the queue");
            
            return;
        }
        this.remove();
    }
}
