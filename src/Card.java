public class Card{
     int rank;
     String suit;

    public Card(int rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }
    public int compareTo(Card antherCard) {
        if (this.rank == antherCard.rank) {
            return this.suit.compareTo(antherCard.suit);
        } else {
            return Integer.compare(this.rank, antherCard.rank);
        }
    }

    public String toString() {
        String rankToString = switch (rank) {
            case 11 -> "Jack";
            case 12 -> "Queen";
            case 13 -> "King";
            case 14 -> "Ace";
            default -> Integer.toString(rank);
        };

        return rankToString + " of " + suit;
    }
}
