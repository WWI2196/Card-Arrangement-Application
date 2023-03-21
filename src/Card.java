public class Card{
     int cardRank;
     String suit;

    public Card(int cardRank, String suit) {
        this.cardRank = cardRank;
        this.suit = suit;
    }

    public int getCardRank() {
        return cardRank;
    }

    public String getSuit() {
        return suit;
    }

    public int compareTo(Card antherCard) {
        if (this.cardRank == antherCard.cardRank) {
            return this.suit.compareTo(antherCard.suit);
        } else {
            return Integer.compare(this.cardRank, antherCard.cardRank);
        }
    }

    public String toString() {
        String rankToString = switch (cardRank) {
            case 11 -> "Jack";
            case 12 -> "Queen";
            case 13 -> "King";
            case 14 -> "Ace";
            default -> Integer.toString(cardRank);
        };

        return rankToString + " of " + suit;
    }
}
