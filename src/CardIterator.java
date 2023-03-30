public interface CardIterator<Card> {
    boolean hasNext();
    Card next();
}

//reference: https://www.baeldung.com/java-iterator, https://stackoverflow.com/questions/24588148/how-to-create-a-custom-iterator-for-a-deck-of-cards-java