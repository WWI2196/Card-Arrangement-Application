public record Cards(int cardRank, String suit) {

    public int compareTo(Cards other) {
        if (!this.suit.equals(other.suit)) {
            return this.suit.compareTo(other.suit);
        } else {
            return Integer.compare(this.cardRank, other.cardRank);
        }
    }

    public String toString() {
        String rankStr;

        switch (cardRank) {
            case 1:
                rankStr = "Ace";
                break;
            case 11:
                rankStr = "Jack";
                break;
            case 12:
                rankStr = "Queen";
                break;
            case 13:
                rankStr = "King";
                break;
            default:
                rankStr = Integer.toString(cardRank);
                break;
        }
        return rankStr + " of " + suit;
    }
}
