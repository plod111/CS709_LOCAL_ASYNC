
/**
 * Ch14, Exercises 3-4
 * 
 * @editor B.Cornish
 * @coeditor P.Chu
 * @date Nov 4, 2023
 */

/**
 * A deck of playing cards.
 */
public class Deck extends CardCollection {

    /**
     * Constructs a standard deck of 52 cards.
     */
    public Deck(String label) {
        super(label);
        for (int suit = 0; suit <= 3; suit++) {
            for (int rank = 1; rank <= 13; rank++) {
                addCard(new Card(rank, suit));
            }
        }
    }

}
