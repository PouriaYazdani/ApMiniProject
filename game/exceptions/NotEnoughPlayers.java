package game.exceptions;

/**
 * This exception is thrown when limitation in number of players is not met.
 * It is  thrown in {@link game.Monopoly}.
 */
public class NotEnoughPlayers extends MonopolyException{
    public NotEnoughPlayers(String message) {
        super(message);
    }
}
