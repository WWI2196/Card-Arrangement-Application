import java.util.ArrayList;
import java.util.List;

public class Game {
    private final int numberOfPlayers;
    private final Hand[] hands;
    private final Deck deck;

    public Game(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        hands = new Hand[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            hands[i] = new Hand();
        }
        deck = new Deck();
    }

    public void play() {
        deck.shuffle();
        int numCards = deck.size / numberOfPlayers;

        for (int i = 0; i < numCards; i++) {
            for (int j = 0; j < numberOfPlayers; j++) {
                Card card = deck.dealCard();
                if (card != null) {
                    hands[j].addACard(card.getRank(), card.getSuit());

                }
            }
            System.out.println("Hands after deal " + (i + 1) + ":");
            iterator();
        }
    }

    public void iterator() {
        int[][] suitOrder = new int[numberOfPlayers][4];
        for (int i = 0; i < numberOfPlayers; i++) {
            Hand hand = hands[i];
            int[] suitOrderForPlayer = suitOrder[i];
            for (int j = 0; j < hand.getSize(); j++) {
                Card card = hand.getCard(j);
                String suit = card.getSuit();
                int suitIndex = hand.getSuitIndex(suit);
                if (suitOrderForPlayer[suitIndex] == 0) {
                    suitOrderForPlayer[suitIndex] = j + 1;
                }
            }
        }

        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.print("Player " + (i + 1) + ": " );
            Hand hand = hands[i];
            List<Card>[] sortedSuits = new List[4];
            for (int j = 0; j < 4; j++) {
                sortedSuits[j] = new ArrayList<>();
            }
            for (int j = 0; j < hand.getSize(); j++) {
                Card card = hand.getCard(j);
                String suit = card.getSuit();
                int suitIndex = hand.getSuitIndex(suit);
                sortedSuits[suitIndex].add(card);
            }
            for (int j = 0; j < 4; j++) {
                List<Card> suitCards = sortedSuits[j];
                suitCards.sort((c1, c2) -> Integer.compare(c1.getRank(), c2.getRank()));
                for (Card card : suitCards) {
                    System.out.print(card.getRank() + "-" + card.getSuit() + ", ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
class Runner {
    public static void main(String[] args) {
        Game game = new Game(4);
        game.play();
    }
}