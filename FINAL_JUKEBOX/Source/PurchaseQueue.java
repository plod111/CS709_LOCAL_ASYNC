import java.util.LinkedList;

public class PurchaseQueue extends LinkedList<Song> {
    
    public PurchaseQueue() {
        super();
    }
    
  
    // add a song to the queue band check if there's enough funds
    
    public void addSong(Song song, CurrencyBox creditCurrencyBox, CurrencyBox coinCurrencyBox) {
        if ((coinCurrencyBox.getTotalCoinsAmount() + creditCurrencyBox.getCreditAmountInt()) < song.getPrice()) {
            System.out.println("Not enough funds to purchase " + song.getTitle());

            return;
            //if there's enough coins, add the song to the queue and subtract the price from coins
        } else if (coinCurrencyBox.getTotalCoinsAmount() < song.getPrice()) {
            int difference = song.getPrice() - coinCurrencyBox.getTotalCoinsAmount();
            creditCurrencyBox.setCreditAmountInt(-difference);
            coinCurrencyBox.resetAllCoins();
           
            //if there aren't enough coins, add the song to the queue and subtract the price from coins and then credit
        }  else{
            int difference = song.getPrice() - coinCurrencyBox.getTotalCoinsAmount();
            coinCurrencyBox.resetAllCoins();
            coinCurrencyBox.setCreditAmountInt(-difference);
        }
        this.add(song);
        System.out.println("Added " + song.getTitle() + " to the queue");
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
