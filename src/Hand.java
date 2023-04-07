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

    private CardHolder firstCard;
    private int cardsInHand;

    public Hand() {
        this.firstCard = null;
        this.cardsInHand = 0;
    }

    public void addACard(int rank, String suit) {
        Card card = new Card(rank, suit);
        CardHolder newHolder = new CardHolder(card);
        if (firstCard == null) {
            firstCard = newHolder;
        } else {
            CardHolder current = firstCard;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newHolder;
        }
        cardsInHand++;
    }

    public int getSize() {
        return cardsInHand;
    }

    public Card getCard(int index) {
        if (index < 0 || index >= cardsInHand) {
            throw new NoSuchElementException();
        }
        CardHolder current = firstCard;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.card;
    }

    public Card removeCard(int index) {
        if (index < 0 || index >= cardsInHand) {
            throw new NoSuchElementException();
        }
        CardHolder previous = null;
        CardHolder current = firstCard;
        for (int i = 0; i < index; i++) {
            previous = current;
            current = current.next;
        }
        if (previous == null) {
            firstCard = current.next;
        } else {
            previous.next = current.next;
        }
        cardsInHand--;
        return current.card;
    }
}