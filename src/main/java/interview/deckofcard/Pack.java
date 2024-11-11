package interview.deckofcard;

import java.util.List;

public abstract class Pack<T extends Card> {
    private List<T> cards;

    protected abstract List<T> build();
}
