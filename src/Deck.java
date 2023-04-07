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
        int shuffles = size * (random.nextInt(9)+random.nextInt(9));
        for (int i = 0; i < shuffles; i++) {
            int j = random.nextInt(size);
            int k = random.nextInt(size);
            Card card = cards[j];
            cards[j] = cards[k];
            cards[k] = card;
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
