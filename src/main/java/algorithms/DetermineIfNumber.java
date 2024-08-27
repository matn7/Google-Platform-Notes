package algorithms;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class DetermineIfNumber {

    static Map<DigitState, List<DigitState>> PATHS = new HashMap<>();

    static Map<DigitState, Predicate<Character>> STATE_VALIDATOR = new HashMap<>();

    static {
        PATHS.put(DigitState.BEGIN, Arrays.asList(DigitState.NEGATIVE1, DigitState.DIGIT1));
        PATHS.put(DigitState.NEGATIVE1, Arrays.asList(DigitState.DIGIT1, DigitState.DOT));
        PATHS.put(DigitState.DIGIT1, Arrays.asList(DigitState.DIGIT1, DigitState.DOT, DigitState.E));
        PATHS.put(DigitState.DOT, Arrays.asList(DigitState.DIGIT2));
        PATHS.put(DigitState.DIGIT2, Arrays.asList(DigitState.DIGIT2, DigitState.E));
        PATHS.put(DigitState.NEGATIVE2, Arrays.asList(DigitState.DIGIT3));
        PATHS.put(DigitState.DIGIT3, Arrays.asList(DigitState.DIGIT3));
        PATHS.put(DigitState.E, Arrays.asList(DigitState.DIGIT3, DigitState.NEGATIVE2));

        STATE_VALIDATOR.put(DigitState.BEGIN, a -> true);
        STATE_VALIDATOR.put(DigitState.DIGIT1, Character::isDigit);
        STATE_VALIDATOR.put(DigitState.NEGATIVE1, a -> a == '-');
        STATE_VALIDATOR.put(DigitState.DIGIT2, Character::isDigit);
        STATE_VALIDATOR.put(DigitState.DOT, a -> a == '.');
        STATE_VALIDATOR.put(DigitState.E, a -> a == 'e');
        STATE_VALIDATOR.put(DigitState.NEGATIVE2, a -> a == '-');
        STATE_VALIDATOR.put(DigitState.DIGIT3, Character::isDigit);

    }

    public static void main(String[] args) {

        // finite state machine
        DetermineIfNumber determineIfNumber = new DetermineIfNumber();
        boolean result = determineIfNumber.parse_number("123e-5");
        System.out.println(result);
        System.out.println(determineIfNumber.parse_number("123a"));

    }

    // O(n) time | O(k) space (build finite state machine or O(1))
    public boolean parse_number(String str) {
        DigitState state = DigitState.BEGIN;

        for (char c : str.toCharArray()) {
            boolean found = false;
            for (DigitState next_state : PATHS.get(state)) {
                Boolean apply = STATE_VALIDATOR.get(next_state).test(c);
                if (apply) {
                    state = next_state;
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        Set<DigitState> endStates = new HashSet<>();
        endStates.add(DigitState.DIGIT1);
        endStates.add(DigitState.DIGIT2);
        endStates.add(DigitState.DIGIT3);
        return endStates.contains(state);
    }

    enum DigitState {
        BEGIN,
        NEGATIVE1,
        DIGIT1,
        DOT,
        DIGIT2,
        E,
        NEGATIVE2,
        DIGIT3
    }

}