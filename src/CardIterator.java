public interface CardIterator<Card> {
    boolean hasNext();
    Card next();
}

//reference: https://www.baeldung.com/java-iterator, https://stackoverflow.com/questions/24588148/how-to-create-a-custom-iterator-for-a-deck-of-cards-java

/*public class HandIterator implements CardIterator<Card> {

        private int currentIndex = 0;
        private CardHolder currentSuit = null;
        private int suitIndex = 0;

        public boolean hasNext() {
            return currentIndex < cardsInHand;
        }

        public Card next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            while (currentSuit == null || currentIndex % cardsInSuit[suitIndex] == 0) {
                suitIndex++;
                currentSuit = suits[suitIndex];
            }
            Card card = currentSuit.card;
            currentSuit = currentSuit.next;
            currentIndex++;
            return card;
        }
    }

    private class SuitIterator implements CardIterator<Card>  {

        private final int suitIndex;
        private CardHolder current;

        public SuitIterator(int suitIndex, CardHolder head) {
            this.suitIndex = suitIndex;
            this.current = head;
            while (current != null && !current.card.getSuit().equals(getSuit(suitIndex))) {
                current = current.next;
            }
        }

        public boolean hasNext() {
            return current != null;
        }

        public Card next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Card card = current.card;
            current = current.next;
            while (current != null && !current.card.getSuit().equals(getSuit(suitIndex))) {
                current = current.next;
            }
            return card;
        }*/