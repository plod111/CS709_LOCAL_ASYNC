import java.util.LinkedList;

public class purchaseQueue extends LinkedList<Song> {
    
    public purchaseQueue() {
        super();
    }
    
  
    // add a song to the queue band check if there's enough funds
    public void addSong(Song song, currencyBox creditCurrencyBox, currencyBox coinCurrencyBox) {
        if ((coinCurrencyBox.getTotalCoinsAmount() + creditCurrencyBox.getCreditAmountInt()) < song.getPrice()) {
            System.out.println("Not enough funds to purchase " + song.getTitle());

            return;
        }else if(coinCurrencyBox.getTotalCoinsAmount() < song.getPrice()) {
            int difference = song.getPrice() - coinCurrencyBox.getTotalCoinsAmount();
            creditCurrencyBox.setCreditAmountInt(-difference);
            coinCurrencyBox.resetAllCoins();
        this.add(song);
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
