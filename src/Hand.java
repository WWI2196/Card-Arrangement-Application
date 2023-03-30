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

    public void sortByRank() {
        for (CardHolder suit : suits) {
            CardHolder current = suit;
            while (current != null) {
                CardHolder next = current.next;
                while (next != null) {
                    if (current.card.getRank() > next.card.getRank()) {
                        Card temp = current.card;
                        current.card = next.card;
                        next.card = temp;
                    }
                    next = next.next;
                }
                current = current.next;
            }
        }
    }

    public Card playACard(String suit) {
        int suitIndex = getSuitIndex(suit);
        if (cardsInSuit[suitIndex] == 0) { // No cards of that suit in hand
            return removeRandomCard();

        }else {
            CardHolder previous = null;
            CardHolder current = suits[suitIndex];
            while (!current.card.getSuit().equals(suit)) {
                previous = current;
                current = current.next;
            }
            if (previous == null) { // Card is at the beginning of the list
                suits[suitIndex] = current.next;
            } else { // Card is in the middle or end of the list
                previous.next = current.next;
            }
            cardsInSuit[suitIndex]--;
            cardsInHand--;
            return current.card;
        }
    }

    private Card removeRandomCard() {
        CardHolder previous = null;
        CardHolder current;
        int i = 0;
        do {
            current = suits[i++];
        } while (current == null);

        while (current.next != null) {
            previous = current;
            current = current.next;
        }
        if (previous == null) { // Only one card in hand
            for (int y = 0; y < 4; y++) {
                if (suits[y] != null) {
                    current = suits[y];
                    suits[y] = null;
                    cardsInSuit[y] = 0;
                    break;
                }
            }
        } else { // Remove last card in hand
            previous.next = null;
        }
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

    public String getSuit(int suitIndex) {
        return switch (suitIndex) {
            case 0 -> "Hearts";
            case 1 -> "Clubs";
            case 2 -> "Spades";
            case 3 -> "Diamonds";
            default -> null;
        };
    }



}