import java.util.Iterator;
import java.util.NoSuchElementException;

public class Hand {
    private class Node {
        Card card;
        Node next;

        Node(Card card) {
            this.card = card;
            this.next = null;
        }
    }

    private Node[] suits; // Array of 4 nodes to represent the suits
    private int[] sizes;  // Array of 4 integers to keep track of the number of cards in each suit
    private int size;     // Total number of cards in the hand

    public Hand() {
        this.suits = new Node[4];
        this.sizes = new int[4];
        this.size = 0;
    }

    public void addACard(int cardRank, String suit) {
        Card card = new Card(cardRank, suit);
        int suitIndex = getSuitIndex(suit);
        Node newNode = new Node(card);
        newNode.next = suits[suitIndex];
        suits[suitIndex] = newNode;
        sizes[suitIndex]++;
        size++;
    }

    public Card playACard(String suit) {
        int suitIndex = getSuitIndex(suit);
        if (sizes[suitIndex] == 0) { // No cards of that suit in hand
            return removeRandomCard();
        }
        Node previous = null;
        Node current = suits[suitIndex];
        while (!current.card.getSuit().equals(suit)) {
            previous = current;
            current = current.next;
        }

        if (previous == null) { // Card is at the beginning of the list
            suits[suitIndex] = current.next;
        } else { // Card is in the middle or end of the list
            previous.next = current.next;
        }
        sizes[suitIndex]--;
        size--;
        return current.card;
    }

    private Card removeRandomCard() {
        Node prev = null;
        Node curr = null;
        for (int i = 0; i < 4; i++) {
            if (suits[i] != null) {
                prev = curr;
                curr = suits[i];
                break;
            }
        }
        while (curr.next != null) {
            prev = curr;
            curr = curr.next;
        }
        if (prev == null) { // Only one card in hand
            for (int i = 0; i < 4; i++) {
                if (suits[i] != null) {
                    curr = suits[i];
                    suits[i] = null;
                    sizes[i] = 0;
                    break;
                }
            }
        } else { // Remove last card in hand
            prev.next = null;
        }
        size--;
        return curr.card;
    }

    public Iterator<Card> iterator() {
        return (Iterator<Card>) new HandIterator();
    }

    public Iterator<Card> suitIterator(String suit) {
        int suitIndex = getSuitIndex(suit);
        return new SuitIterator(suitIndex, suits[suitIndex]);
    }

    private int getSuitIndex(String suit) {
        switch (suit) {
            case "Hearts":
                return 0;
            case "Clubs":
                return 1;
            case "Spades":
                return 2;
            case "Diamonds":
                return 3;
            default:
                return -1;
        }
    }

    private String getSuit(int suitIndex) {
        switch (suitIndex) {
            case 0:
                return "Hearts";
            case 1:
                return "Clubs";
            case 2:
                return "Spades";
            case 3:
                return "Diamonds";
            default:
                return null;
        }
    }

    public class HandIterator {

        private int currentIndex = 0;
        private Node currentSuit = null;
        private int suitIndex = 0;

        public boolean hasNext() {
            return currentIndex < size;
        }

        public Card next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            while (currentSuit == null || currentIndex % sizes[suitIndex] == 0) {
                suitIndex++;
                currentSuit = suits[suitIndex];
            }
            Card card = currentSuit.card;
            currentSuit = currentSuit.next;
            currentIndex++;
            return card;
        }
    }

    private class SuitIterator implements Iterator<Card> {

        private int suitIndex;
        private Node current;

        public SuitIterator(int suitIndex, Node head) {
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
        }
    }
}