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
            displayHands();
        }
    }

    public void displayHands() {
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
            int[] suitOrderForPlayer = suitOrder[i];
            for (int j = 0; j < 4; j++) {
                int suitIndex = suitOrderForPlayer[j] - 1;
                if (suitIndex >= 0) {
                    Card card = hand.getCard(suitIndex);
                    System.out.print(card.getRank() + "-" + card.getSuit() + ", ");
                }
                int endIndex = ((j == 3) ? hand.getSize() : suitOrderForPlayer[j + 1] - 1);
                Hand suitHand = new Hand();
                for (int k = suitIndex + 1; k < endIndex; k++) {
                    Card card = hand.getCard(k);
                    if (card.getSuit().equals(hand.getSuit(j))) {
                        suitHand.addACard(card.getRank(), card.getSuit());
                    }
                }
                suitHand.sortByRank();
                System.out.print(suitHand+" ");
            }
            System.out.println();
        }
        System.out.println();

    }
}
