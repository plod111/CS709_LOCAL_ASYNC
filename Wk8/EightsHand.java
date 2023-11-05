import java.util.ArrayList;

/**
 * Ch14, Exercises 3-4
 * 
 * @coeditor B.Cornish
 * @coeditor P.Chu
 * @date Nov 5, 2023
 */

public class EightsHand extends Hand {

    private String label;
    private ArrayList<EightsCard> cards;

    public EightsHand(String label) {
        super(label);
        //this.label = label;
        this.cards = new ArrayList<EightsCard>();

    }

    /**
     * Scores the hand.
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