import java.util.Iterator;

public class Player {
    private String name;
    private Hand hand;

    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public String getName() {
        return name;
    }

    public void addCardToHand(int cardRank, String suit) {
        hand.addACard(cardRank, suit);
    }

    public Card playCard(String suit) {
        return hand.playACard(suit);
    }

    public Iterator<Card> getHandIterator() {
        return hand.iterator();
    }

    public Iterator<Card> getSuitIterator(String suit) {
        return hand.suitIterator(suit);
    }
}