
public class EightsHand extends Hand {

    public EightsHand(String label) {

        super(label);
    }


    public int scoreHand() {

        int score = 0;

        for (int i = 0; i < size(); i++) {

            EightsCard card = (EightsCard) getCard(i);

            int rank = card.getRank();

            if (rank == 8) {

                score -= 20;

            } else if (rank > 10) {

                score -= 10;

            } else {

                score -= rank;

            }

        }

        return score;

    }

    
}