
public class Genius extends Player {

    /**
     * Constructs a Better player with an empty hand.
     */
    public Genius(String name) {
        super(name);
    }

    /**
     * Removes and returns a legal card from the player's hand.
     */
    public Card play(Eights eights, Card prev) {
        Card card = betterSearchForMatch(prev);
        if (card == null) {
            card = drawForMatch(eights, prev);
        }
        return card;
    }

    /**
     * Searches the player's hand for a matching card.
     */
    public Card betterSearchForMatch(Card prev) {

        // find the first match
        int bestCard = 0;
        for (int j = 0; j < hand.size(); j++) {
            Card card = hand.getCard(j);
            if (card.getRank() == 8) {
                return hand.popCard(j);
            }
            if (cardMatches(card, prev)) {
                bestCard = j;
            }
        }

        // find best match in rest of hand
        for (int i = bestCard+1; i < hand.size(); i++) {
            Card card = hand.getCard(i);
            if (cardMatches(card, prev)) {
                if (card.getRank() == 8) {
                    return hand.popCard(i);
                } else if (card.getRank() > hand.getCard(bestCard).getRank() || card.getSuit() > hand.getCard(bestCard).getSuit()) {
                    bestCard = i;
                }
            }
        }
        return hand.popCard(bestCard);
    }
}
