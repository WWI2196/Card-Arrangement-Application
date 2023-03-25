import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Game {
    private final int numPlayers;
    private final Hand[] hands;
    private final Deck deck;

    public Game(int numPlayers) {
        this.numPlayers = numPlayers;
        hands = new Hand[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            hands[i] = new Hand();
        }
        deck = new Deck();
    }

    public void play() {
        deck.shuffle();
        int numCards = deck.size() / numPlayers;

        for (int i = 0; i < numCards; i++) {
            for (int j = 0; j < numPlayers; j++) {
                Card card = deck.deal();
                if (card != null) {
                    hands[j].addACard(card);
                    System.out.println("Player " + (j + 1) + " received: " + card);
                }
            }
            System.out.println("Hands after deal " + (i + 1) + ":");
            displayHands();
        }

        sortHands();
        System.out.println("Final hands:");
        displayHands();
    }

    private void displayHands() {
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Player " + (i + 1) + ": " + hands[i]);
        }
        System.out.println();
    }

    private void sortHands() {
        for (Hand hand : hands) {
            hand.sortBySuit();
        }
        Arrays.sort(hands);
    }
}
