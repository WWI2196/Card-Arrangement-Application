import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Hand implements Iterable<Card> {
    private List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void addACard(int rank, String suit) {
        Card card = new Card(rank, suit);
        cards.add(card);
    }

    public Card playACard(String suit) {
        Iterator<Card> iter = suitIterator(suit);
        if (iter.hasNext()) {
            Card card = iter.next();
            iter.remove();
            return card;
        } else {
            Card card = iterator().next();
            iterator().remove();
            return card;
        }
    }

    public Iterator<Card> iterator() {
        return cards.iterator();
    }

    public Iterator<Card> suitIterator(String suit) {
        List<Card> suitCards = new ArrayList<>();
        for (Card card : cards) {
            if (card.suit().equals(suit)) {
                suitCards.add(card);
            }
        }
        return suitCards.iterator();
    }
}
