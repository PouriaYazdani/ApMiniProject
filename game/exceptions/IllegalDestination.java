package game.exceptions;

/**
 * This exception is thrown whenever the player wants to fly to a location which is not an airport or it's his/her
 * current location.
 * It is thrown in {@link game.properties.Airport}.
 */
public class IllegalDestination extends MonopolyException{
    public IllegalDestination(String message) {
        super(message);
    }
}
