package interview.object.oriented.design.deckofcard;

public class StandardCard extends Card {

    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 13;

    public StandardCard(Enum suit, int value) {
        super(suit, value);
    }
}
