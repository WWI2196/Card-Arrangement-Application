import java.util.NoSuchElementException;

public class Hand {
    public static class CardHolder {
        Card card;
        CardHolder next;

        CardHolder(Card card) {
            this.card = card;
            this.next = null;
        }
    }

    private final CardHolder[] suits;
    private final int[] cardsInSuit;
    private int cardsInHand;

    public Hand() {
        this.suits = new CardHolder[4];
        this.cardsInSuit = new int[4];
        this.cardsInHand = 0;
    }

    public void addACard(int rank, String suit) {
        Card card = new Card(rank, suit);
        int suitIndex = getSuitIndex(suit);
        CardHolder newHolder = new CardHolder(card);
        newHolder.next = suits[suitIndex];
        suits[suitIndex] = newHolder;
        cardsInSuit[suitIndex]++;
        cardsInHand++;
    }

    public int getSize() {
        return cardsInHand;
    }

    public Card getCard(int index) {
        if (index < 0 || index >= cardsInHand) {
            throw new NoSuchElementException();
        }
        int suitIndex = 0;
        while (index >= cardsInSuit[suitIndex]) {
            index -= cardsInSuit[suitIndex];
            suitIndex++;
        }
        CardHolder current = suits[suitIndex];
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.card;
    }

    public Card removeCard(int index) {
        if (index < 0 || index >= cardsInHand) {
            throw new NoSuchElementException();
        }
        int suitIndex = 0;
        while (index >= cardsInSuit[suitIndex]) {
            index -= cardsInSuit[suitIndex];
            suitIndex++;
        }
        CardHolder previous = null;
        CardHolder current = suits[suitIndex];
        for (int i = 0; i < index; i++) {
            previous = current;
            current = current.next;
        }
        if (previous == null) {
            suits[suitIndex] = current.next;
        } else {
            previous.next = current.next;
        }
        cardsInSuit[suitIndex]--;
        cardsInHand--;
        return current.card;
    }

    public int getSuitIndex(String suit) {
        return switch (suit) {
            case "Hearts" -> 0;
            case "Clubs" -> 1;
            case "Spades" -> 2;
            case "Diamonds" -> 3;
            default -> -1;
        };
    }
}