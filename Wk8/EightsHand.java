
/**
 * Ch14, Exercises 3-4
 * 
 * @coeditor B.Cornish
 * @coeditor P.Chu
 * @date Nov 5, 2023
 */

public class EightsHand extends Hand {

    public EightsHand(String label) {

        super(label);
    }

    public int scoreHand() {

        int score = 0;

        for (int i = 0; i < size(); i++) {

            EightsCard card = (EightsCard) getCard(i);

            score += card.scoreCard();
        }

        return score;

    }

}