
/**
 * Ch14, Exercises 3-4
 * 
 * @editor B.Cornish
 * @coeditor P.Chu
 * @date Nov 4, 2023
 */

/**
 * A player in a game of crazy eights.
 */
public class Player {

    private String name;
    protected EightsHand hand;

    /**
     * Constructs a player with an empty hand.
     */
    public Player(String name) {
        this.name = name;
        this.hand = new EightsHand(name);
    }

    /**
     * Gets the player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the player's hand.
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * Removes and returns a legal card from the player's hand.
     */
    public EightsCard play(Eights eights, EightsCard prev) {
        EightsCard card = searchForMatch(prev);
        if (card == null) {
            card = drawForMatch(eights, prev);
        }
        return card;
    }

    /**
     * Searches the player's hand for a matching card.
     */
    public EightsCard searchForMatch(EightsCard prev) {
        for (int i = 0; i < hand.size(); i++) {
            EightsCard card = hand.getCard(i);
            if (card.matches(prev)) {
                return hand.popCard(i);
            }
        }
        return null;
    }

    /**
     * Draws cards until a match is found.
     */
    public EightsCard drawForMatch(Eights eights, Card prev) {
        while (true) {
            EightsCard card = eights.drawCard();
            System.out.println(name + " draws " + card);
            if (card.matches(prev)) {
                return card;
            }
            hand.addCard(card);
        }
    }

    /**
     * Checks whether two cards match.
     */
    // public static boolean cardMatches(Card card1, Card card2) {
    //     return card1.getSuit() == card2.getSuit()
    //         || card1.getRank() == card2.getRank()
    //         || card1.getRank() == 8;
    // }

    /**
     * Calculates the player's score (penalty points).
     */
    public int score() {
        int sum = 0;
        for (int i = 0; i < hand.size(); i++) {
            Card card = hand.getCard(i);
            int rank = card.getRank();
            if (rank == 8) {
                sum -= 20;
            } else if (rank > 10) {
                sum -= 10;
            } else {
                sum -= rank;
            }
        }
        return sum;
    }

    /**
     * Displays the player's hand.
     */
    public void display() {
        hand.display();
    }

    /**
     * Displays the player's name and score.
     */
    public void displayScore() {
        System.out.println(name + " has " + score() + " points");
    }

}
