package game.exceptions;

/**
 * This exception is thrown when the player doesn't have enough cash to pay for a OPTIONAl operation.such as buying
 * building and to fly and other choices he/she would make throughout the game.
 * It is thrown in {@link game.properties.Airport},{@link game.properties.Cinema},{@link game.properties.EmptyField}
 * ,{@link game.properties.Prison}
 */
public class NotEnoughCash extends MonopolyException{
    public NotEnoughCash(String message) {
        super(message);
    }
}
