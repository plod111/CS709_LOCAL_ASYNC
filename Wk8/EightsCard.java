
/**
 * Ch14, Exercises 3-4
 * 
 * @editor B.Cornish
 * @coeditor P.Chu
 * @date Nov 4, 2023
 */

public class EightsCard extends Card{


public EightsCard(int rank,int suit) {
    super(rank,suit);
}


public boolean matches(Card thatCard) {
        return this.getSuit() == thatCard.getSuit()
            || this.getRank() == thatCard.getRank()
            || this.getRank() == 8;
    }

}
 