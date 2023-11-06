import java.util.ArrayList;

/**
 * Ch14, Exercises 3-4
 * 
 * @coeditor B.Cornish
 * @coeditor P.Chu
 * @date Nov 5, 2023
 */

public class EightsHand extends Hand {

    ///////////////////////
    // Not sure what to do with the constructor here //

    // private String label;
    // private ArrayList<EightsCard> cards;

    public EightsHand(String label) {
        super(label);
        // super.label = label;
        super.cards = new ArrayList<EightsCard>();  // we want the Hand to contain EightsCards
    }

    /**
     * Scores the hand.  
     * ************->>> This is the method we are adding to the EightsHand class, 
     * and screws everything up.  
     * It's a game-specific scoring method and should be in the game state class, not the hand class.
     */
    public int scoreHand() {

        int score = 0;

        for (int i = 0; i < size(); i++) {

            EightsCard card = (EightsCard) getCard(i);

            score += card.scoreCard();
        }

        return score;

    }

    ///////////////////////////////////////////////////////////////////////////
    // Overriding methods from Hand/CardCollection to handle EightsCards
    // These are going to be required in order to accept and return EightsCards
    // instead of Cards, at which point, what is the point..>!??!
    /**
     * Adds the given card to the collection.
     */
    public void addCard(EightsCard card) {
        cards.add(card);
    }

    /**
     * Removes and returns the card with the given index.
     */
    public EightsCard popCard(int i) {
        return cards.remove(i);
    }

    /**
     * Removes and returns the last card.
     */
    public EightsCard popCard() {
        int i = cards.size() - 1; // from the end of the list
        return popCard(i);
    }

    /**
     * Returns the card with the given index.
     */
    public EightsCard getCard(int i) {
        return cards.get(i);
    }

    /**
     * Returns the last card.
     */
    public EightsCard lastCard() {
        int i = cards.size() - 1;
        return cards.get(i);
    }

}