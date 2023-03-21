import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game {
    private List<Player> players;
    private Deck deck;
    private int currentPlayerIndex;

    public Game() {
        players = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            players.add(new Player(i));
        }
        deck = new Deck();
        deck.shuffle();
        currentPlayerIndex = 0;
    }

    public void deal() {
        if (deck.isEmpty()) {
            return;
        }
        Card card = deck.deal();
        players.get(currentPlayerIndex).addCard(card);
        currentPlayerIndex = (currentPlayerIndex + 1) % 4;
    }

    public void playCard(String suit) {
        for (Player player : players) {
            Card card = player.playCard(suit);
            if (card != null) {
                System.out.println("Player " + player.getId() + " played " + card.toString());
                return;
            }
        }
        System.out.println("No card of suit " + suit + " found. Playing arbitrary card.");
        for (Player player : players) {
            Card card = player.playCard();
            if (card != null) {
                System.out.println("Player " + player.getId() + " played " + card.toString());
                return;
            }
        }
        System.out.println("No cards found. Cannot play.");
    }

    public void printHands() {
        for (Player player : players) {
            System.out.println("Player " + player.getId() + ": " + player.getHand().toString());
        }
    }

    public void sortHands() {
        for (Player player : players) {
            player.sortHand();
        }
    }

    public Iterator<Card> iterator() {
        List<Card> allCards = new ArrayList<>();
        for (Player player : players) {
            allCards.addAll(player.getHand().getAllCards());
        }
        return allCards.iterator();
    }

    public Iterator<Card> suitIterator(String suit) {
        List<Card> suitCards = new ArrayList<>();
        for (Player player : players) {
            suitCards.addAll(player.getHand().getSuitCards(suit));
        }
        return suitCards.iterator();
    }
}
