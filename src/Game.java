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
            printHands(suitsReceived, hands);
        }
        playACard("Hearts",hands);

    }

    private void printHands(String[][] suitsReceived, Hand[] hands) {
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

    public void playACard(String suit,Hand[] hands){
        // Remove and return a card of suit from the player’s hand; if there is no card of suit s, then remove and return an arbitrary card from the hand.


    }


}
