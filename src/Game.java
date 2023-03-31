import java.util.Scanner;

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
        String[][] suitsReceived = new String[numberOfPlayers][];
        Hand[] hands = new Hand[numberOfPlayers];

        for (int i = 0; i < numberOfPlayers; i++) {
            hands[i] = new Hand();
        }

        for (int i = 0; i < numCards; i++) {
            for (int j = 0; j < numberOfPlayers; j++) {
                Card card = deck.dealCard();
                if (card != null) {
                    hands[j].addACard(card.getRank(), card.getSuit());
                    String suit = card.getSuit();
                    if (suitsReceived[j] == null) {
                        suitsReceived[j] = new String[]{suit};
                    } else {
                        int index = -1;
                        for (int k = 0; k < suitsReceived[j].length; k++) {
                            if (suit.equals(suitsReceived[j][k])) {
                                index = k;
                                break;
                            }
                        }
                        if (index == -1) {
                            String[] newSuitsReceived = new String[suitsReceived[j].length + 1];
                            for (int k = 0; k < suitsReceived[j].length; k++) {
                                newSuitsReceived[k] = suitsReceived[j][k];
                            }
                            newSuitsReceived[newSuitsReceived.length - 1] = suit;
                            suitsReceived[j] = newSuitsReceived;
                        }
                    }
                }
            }
            System.out.println("Hands after deal " + (i + 1) + ":");
            iterator(hands);
        }
        System.out.println("Hands after dealing all cards:");
        printSortedHands(suitsReceived,hands);
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the suit to play: ");
            String suit = scanner.nextLine();
            playACard(suit, hands);
            System.out.println();
        }while (true);
    }

    private void printSortedHands(String[][] suitsReceived, Hand[] hands) {
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.print("Player " + (i + 1) + ": ");
            Hand hand = hands[i];
            Card[] sortedCards = new Card[hand.getSize()];
            int index = 0;
            String[] suitOrder = suitsReceived[i];
            if (suitOrder == null) {
                suitOrder = new String[0];
            }
            for (String suit : suitOrder) {
                for (int j = 2; j <= 14; j++) {
                    for (int k = 0; k < hand.getSize(); k++) {
                        Card card = hand.getCard(k);
                        if (card.getSuit().equals(suit) && card.getRank() == j) {
                            sortedCards[index++] = card;
                        }
                    }
                }
            }
            for (int j = 0; j < index; j++) {
                Card card = sortedCards[j];
                System.out.print(card.getRank() + "-" + card.getSuit() + ", ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void playACard(String suit, Hand[] hands) {
        for (int i = 0; i < numberOfPlayers; i++) {
            Hand hand = hands[i];
            boolean suitFound = false;
            for (int j = 0; j < hand.getSize(); j++) {
                Card card = hand.getCard(j);
                if (card.getSuit().equals(suit)) {
                    suitFound = true;
                    hand.removeCard(j);
                    System.out.println("Player " + (i + 1) + " plays " + card.getRank() + "-" + card.getSuit() + ".");
                    break;
                }
            }
            if (!suitFound) {
                int randomCardIndex = (int) (Math.random() * hand.getSize());
                Card randomCard = hand.removeCard(randomCardIndex);
                System.out.println("Player does not have a card in " + suit + ". Player " + (i + 1) + " plays " + randomCard.getRank() + "-" + randomCard.getSuit() + ".");
            }
        }
    }

    public void iterator(Hand[] hands) {
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.print("Player " + (i + 1) + ": ");
            Hand hand = hands[i];
            for (int j = hand.getSize() - 1; j >= 0; j--) {
                Card card = hand.getCard(j);
                System.out.print(card.getRank() + "-" + card.getSuit() + ", ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
class Execution {
    public static void main(String[] args) {
        Game game = new Game(4);
        game.play();
    }
}