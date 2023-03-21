import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Deck implements Iterable<Card> {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        for (int i = 2; i <= 10; i++) {
            cards.add(new Card(i, "Hearts"));
            cards.add(new Card(i, "Clubs"));
            cards.add(new Card(i, "Diamonds"));
            cards.add(new Card(i, "Spades"));
        }
        cards.add(new Card(11, "Hearts"));
        cards.add(new Card(11, "Clubs"));
        cards.add(new Card(11, "Diamonds"));
        cards.add(new Card(11, "Spades"));
        cards.add(new Card(12, "Hearts"));
        cards.add(new Card(12, "Clubs"));
        cards.add(new Card(12, "Diamonds"));
        cards.add(new Card(12, "Spades"));
        cards.add(new Card(13, "Hearts"));
        cards.add(new Card(13, "Clubs"));
        cards.add(new Card(13, "Diamonds"));
        cards.add(new Card(13, "Spades"));
        cards.add(new Card(14, "Hearts"));
        cards.add(new Card(14, "Clubs"));
        cards.add(new Card(14, "Diamonds"));
        cards.add(new Card(14, "Spades"));
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Iterator<Card> iterator() {
        return cards.iterator();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public Card deal() {
        if (isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }
}
