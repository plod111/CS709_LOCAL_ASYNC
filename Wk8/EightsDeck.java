public class EightsDeck extends Deck {
    
   /**
     * Constructs a standard deck of 52 "Eights" cards.
     */
    public EightsDeck(String label) {
        super(label);
        for (int suit = 0; suit <= 3; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
                addCard(new EightsCard(rank, suit));
            }
        }
    }
    
}
