# Card Arrangement Application

This Java application simulates a card game where players arrange a group of cards in their hands. The goal is to represent the sequence of cards using a single positional list, keeping cards of the same suit together. The game involves four players, and each player receives cards during iterations. After the final deal, the players' hands are sorted by suit and value.

## Functionalities

### 1. `addACard(r, s)`
- Adds a new card with rank `r` and suit `s` to the player's hand[^5^][5].
- Time complexity: O(1)

### 2. `playACard(s)`
- Removes and returns a card of suit `s` from the player's hand[^6^][6].
- If no card of suit `s` exists, removes and returns an arbitrary card.
- Time complexity: O(1)

### 3. `iterator()`
- Returns an iterator for all cards currently in the hand.
- Allows traversal through the entire hand.

### 4. `suitIterator(s)`
- Returns an iterator for all cards of suit `s` currently in the hand.
- Allows traversal through cards of a specific suit.
