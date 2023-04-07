import java.util.Scanner;

public class Game {
    int numberOfPlayers;
    Hand[] hands;
    Deck deck;

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
        int numberOfCards = deck.size / numberOfPlayers;
        String[][] suitsReceived = new String[numberOfPlayers][];

        for (int i = 0; i < numberOfCards; i++) {
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
            System.out.println("""
                    Enter your choice:
                    1. play a card
                    2. suit iterator
                    3. exit""");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 3) {
                break;
            } else if (choice == 2) {
                System.out.print("Enter the suit to iterate: ");
                String suit = scanner.nextLine();
                suitIterator(suit, hands);
            }else if (choice == 1) {
                do {
                    System.out.print("Enter the suit to play: ");
                    String input = scanner.nextLine();
                    if (input.equals("Hearts") || input.equals("Clubs") || input.equals("Spades") || input.equals("Diamonds")) {
                        playACard(input, hands);
                        System.out.println();
                    } else {
                        break;
                    }
                }while (true);

            }
        }while (true);
    }

    private void printSortedHands(String[][] suitsReceived, Hand[] hands) {
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.print("Player " + (i + 1) + ": ");
            Hand hand = hands[i];
            Card[] sortedCards = new Card[hand.getSize()];
            int index = 0;
            String[] order = suitsReceived[i];
            if (order == null) {
                order = new String[0];
            }
            for (String suit : order) {
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
                System.out.print(card.RankToString() + "-" + card.getSuit() + ", ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void playACard(String suit, Hand[] hands) {
        for (int i = 0; i < numberOfPlayers; i++) {
            Hand hand = hands[i];
            boolean suitExist = false;
            for (int j = 0; j < hand.getSize(); j++) {
                Card card = hand.getCard(j);
                if (card.getSuit().equals(suit)) {
                    suitExist = true;
                    hand.removeCard(j);
                    System.out.println("Player " + (i + 1) + " plays " + card.RankToString() + "-" + card.getSuit() + ".");
                    break;
                }
            }
            if (!suitExist) {
                int randomCardIndex = (int) (Math.random() * hand.getSize());
                Card randomCard = hand.removeCard(randomCardIndex);
                System.out.println("Player does not have a card in " + suit + ". Player " + (i + 1) + " plays " + randomCard.RankToString() + "-" + randomCard.getSuit() + ".");
            }
        }
    }

    public void iterator(Hand[] hands) {
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.print("Player " + (i + 1) + ": ");
            Hand hand = hands[i];
            for (int j = 0; j < hand.getSize(); j++) {
                Card card = hand.getCard(j);
                System.out.print(card.RankToString() + "-" + card.getSuit() + ", ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void suitIterator(String suit,Hand[] hands) {
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.print("Player " + (i + 1) + ": ");
            Hand hand = hands[i];
            for (int j = hand.getSize() - 1; j >= 0; j--) {
                Card card = hand.getCard(j);
                if (card.getSuit().equals(suit)) {
                    System.out.print(card.RankToString() + "-" + card.getSuit() + ", ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}