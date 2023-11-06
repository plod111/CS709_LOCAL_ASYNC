
/**
 * Ch14, Exercises 3-4
 * 
 * @editor B.Cornish
 * @coeditor P.Chu
 * @date Nov 4, 2023
 */

public class EightsCard extends Card {

    public EightsCard(int rank, int suit) {
        super(rank, suit);
    }

    /**
     * Returns true if suits or rank matches, or is an 8.
     */
    public boolean matches(EightsCard thatCard) {   // Card or EightsCard?
        return this.getSuit() == thatCard.getSuit()
                || this.getRank() == thatCard.getRank()
                || this.getRank() == 8;
    }


    /**
     * Returns the score value of the card using CrazyEights rules.
     * 
     * Similarly, I beleive this should be part of the game state controller, and not here.
     * Being here complicates things.
     */
    public int scoreCard() {

        int rank = getRank();

        if (rank == 8) {

            return -20;

        } else if (rank > 10) {

            return -10;

        } else {

            return -rank;

        }

    }

}
