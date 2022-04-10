package game.exceptions;

/**
 * This exception is thrown when an invalid number is entered as dice number.
 * It is thrown in {@link game.Monopoly}
 */
public class InvalidDiceNumber extends MonopolyException {
    public InvalidDiceNumber(String message) {
        super(message);
    }
}
