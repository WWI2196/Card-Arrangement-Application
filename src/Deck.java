import java.util.Random;

public class Deck {
    Card[] cards;
    int size;

    public Deck() {
        this.cards = new Card[52];
        this.size = 0;
        for (String suit : new String[]{"Hearts", "Clubs", "Spades", "Diamonds"}) {
            for (int rank : new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14}) {
                cards[size] = new Card(rank, suit);
                size++;
            }
        }
    }

    public void shuffle() {
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int j = random.nextInt(size);
            Card temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
    }

    public Card dealCard() {
        if (size == 0) {
            return null;
        }
        Card card = cards[size - 1];
        cards[size - 1] = null;
        size--;
        return card;
    }
}
