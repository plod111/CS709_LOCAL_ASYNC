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
 