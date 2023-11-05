import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

/**
 * Simulates a game of Crazy Eights.
 * See https://en.wikipedia.org/wiki/Crazy_Eights.
 */
public class Eights {

    // private Player one;
    // private Player two;
    ArrayList<Player> players = new ArrayList<Player>();

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
        players.add(new Player("Allen"));
        players.add(new Player("Chris"));
        players.add(new Player("David"));
        players.add(new Player("Ethan"));
        players.add(new Player("Frank"));

        // deal cards to each player
        for (Player player : players) {
            deck.deal(player.getHand(), 5);
        }

        // deal cards to each player
        // one = new Player("Allen");
        // deck.deal(one.getHand(), 5);
        // two = new Player("Chris");
        // deck.deal(two.getHand(), 5);

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
        
        for (Player player: players) {
            if(player.getHand().isEmpty()) {
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
        int nextPlayerIndex = players.indexOf(current)+1;
        int bounds = players.size();

        if (nextPlayerIndex == bounds) {
            nextPlayerIndex = 0;
        } 
            return players.get(nextPlayerIndex);
        }


    /**
     * Displays the state of the game.
     */
    public void displayState() {
        
        for(Player player:players) {
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
        Player player = players.get(rand.nextInt(players.size()));  // randomly chosen strater

        // keep playing until there's a winner
        while (!isDone()) {
            //displayState();
            takeTurn(player);
            player = nextPlayer(player);
        }

        // display the final score
        // loop through list of players
        for (Player each_player : players) {
            each_player.displayScore();
        }
    }

    public static void playGame(int numGames) {
        int oneWins = 0;
        int twoWins = 0;
        int ties = 0;
        for (int i = 0; i < numGames; i++) {
            String winner = playOneGame();
            if (winner.equals("Allen")) {
                oneWins++;
            } else if (winner.equals("Chris")) {
                twoWins++;
            } else {
                ties++;
            }
        }
        System.out.println("Allen won " + oneWins
                + " times. Chris won " + twoWins
                + " times. There were " + ties + " ties.");
    }

    public static String playOneGame() {
        Eights game = new Eights();
        game.playGame();
        return game.getWinner();
    }

    public String getWinner() {
        int i = 0;
        int[] playerScores = new int[players.size()];
        for (Player player : players) {
            playerScores[i] =  player.score();
            i++;
        }

        int maxIndex = 0;
        int max = playerScores[maxIndex];
        for (int j = 1; j < playerScores.length; j++) {
            if(playerScores[j] > max) {
                max = playerScores[j];
                maxIndex = j;
            }
            
        }
        return players.get(maxIndex).getName();

        /// no ties dealt with here...
    }

    /**
     * Creates the game and runs it.
     */
    public static void main(String[] args) {
        // Eights game = new Eights();
        playGame(100);
    }

}
