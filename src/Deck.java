import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        this.cards = new ArrayList<Card>();
        for (int i = 2; i <= 14; i++) {
            this.cards.add(new Card(i, "Hearts"));
            this.cards.add(new Card(i, "Clubs"));
            this.cards.add(new Card(i, "Spades"));
            this.cards.add(new Card(i, "Diamonds"));
        }
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public int size() {
        return cards.size();
    }

    public Card deal() {
        if (cards.size() == 0) {
            return null;
        }
        return cards.remove(cards.size() - 1);
    }
}
