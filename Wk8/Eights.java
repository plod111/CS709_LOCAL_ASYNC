import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

/**
 * Ch14, Exercises 3-4
 * 
 * @coeditor B.Cornish
 * @coeditor P.Chu
 * @date Nov 4, 2023
 */

/**
 * Simulates a game of Crazy Eights.
 * See https://en.wikipedia.org/wiki/Crazy_Eights.
 */
public class Eights {

    // list of players
    private static ArrayList<Player> players = new ArrayList<Player>();

    private Hand drawPile;
    private Hand discardPile;
    private Scanner in;

    /**
     * Initializes the state of the game.
     */
    public Eights() {
        Deck deck = new Deck("Deck");
        deck.shuffle();

        // list of players
        // NB: All players are Players, not Genius's - they're no fun to play against
        players.add(new Player("Allen"));
        players.add(new Player("Chris"));
        players.add(new Player("David"));
        players.add(new Player("Ethan"));
        players.add(new Player("Frank"));

        // deal cards to each player
        for (Player player : players) {
            deck.deal(player.getHand(), 5);
        }

        // turn one card face up
        discardPile = new Hand("Discards");
        deck.deal(discardPile, 1);

        // put the rest of the deck face down
        drawPile = new Hand("Draw pile");
        deck.dealAll(drawPile);

        // create the scanner we'll use to wait for the user
        // in = new Scanner(System.in);
    }

    /**
     * Returns true if either hand is empty.
     */
    public boolean isDone() {

        // loop through list of players
        // if any player's hand is empty, return done
        for (Player player : players) {
            if (player.getHand().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Moves cards from the discard pile to the draw pile and shuffles.
     */
    public void reshuffle() {
        // save the top card
        Card prev = discardPile.popCard();

        // move the rest of the cards
        discardPile.dealAll(drawPile);

        // put the top card back
        discardPile.addCard(prev);

        // shuffle the draw pile
        drawPile.shuffle();
    }

    /**
     * Returns a card from the draw pile.
     */
    public Card drawCard() {
        if (drawPile.isEmpty()) {
            reshuffle();
        }
        return drawPile.popCard();
    }

    /**
     * Switches players.
     */
    public Player nextPlayer(Player current) {

        // set index of next player to current player's index + 1
        int nextPlayerIndex = players.indexOf(current) + 1;

        if (nextPlayerIndex == players.size()) {
            nextPlayerIndex = 0;
        }
        return players.get(nextPlayerIndex);
    }

    /**
     * Displays the state of the game.
     */
    public void displayState() {

        // loop through list of players
        for (Player player : players) {
            player.display();
        }
        discardPile.display();
        System.out.print("Draw pile: ");
        System.out.println(drawPile.size() + " cards");
        in.nextLine();
    }

    /**
     * One player takes a turn.
     */
    public void takeTurn(Player player) {
        Card prev = discardPile.lastCard();
        Card next = player.play(this, prev);
        discardPile.addCard(next);

        System.out.println(player.getName() + " plays " + next);
        System.out.println();
    }

    /**
     * Plays the game.
     */
    public void playGame() {

        Random rand = new Random();
        Player player = players.get(rand.nextInt(players.size())); // randomly chosen starter

        // keep playing until there's a winner
        while (!isDone()) {
            // displayState();
            takeTurn(player);
            player = nextPlayer(player);
        }

        // display the final score
        // loop through list of players
        for (Player each_player : players) {
            each_player.displayScore();
        }
    }

    /**
     * Plays the game multiple times and keeps score.
     */
    public static void playGames(int numGames) {

        // array for player scores after multiple games
        int[] playerScores = new int[players.size()];
        // initialize all scores to 0
        for (int i = 0; i < players.size(); i++) {
            playerScores[i] = 0;
        }

        // increment the score of the winner of each game
        for (int i = 0; i < numGames; i++) {
            String winner = playOneGame();
            for (int j = 0; j < players.size(); j++) {
                if (winner.equals(players.get(j).getName())) {
                    playerScores[j]++;
                }
            }
        }

        // print out the wins for each player
        for (int i = 0; i < players.size(); i++) {
            System.out.println(players.get(i).getName() + " won " + playerScores[i] + " times.");
        }
    }

    /**
     * Simulates one game and returns the winner.
     */
    public static String playOneGame() {
        Eights game = new Eights();
        game.playGame();
        return game.getWinner();
    }

    /**
     * Returns the winner of the game.
     */
    public String getWinner() {

        // array for player scores
        int[] playerScores = new int[players.size()];
        // index to hold winners position in array
        int i = 0;
        for (Player player : players) {
            playerScores[i] = player.score();
            i++;
        }

        // find the highest score
        int maxIndex = 0;
        int max = playerScores[maxIndex];
        for (int j = 1; j < playerScores.length; j++) {
            if (playerScores[j] > max) {
                max = playerScores[j];
                maxIndex = j;
            }

        }

        // return the name of the player with the highest score
        return players.get(maxIndex).getName();

        /// no ties dealt with here...
    }

    /**
     * Creates the game and runs it.
     */
    public static void main(String[] args) {
        // Eights game = new Eights();
        playGames(100);
    }

}
