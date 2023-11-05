
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

    public boolean matches(Card thatCard) {
        return this.getSuit() == thatCard.getSuit()
                || this.getRank() == thatCard.getRank()
                || this.getRank() == 8;
    }


    /**
     * Returns the score value of the card using CrazyEights rules.
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
