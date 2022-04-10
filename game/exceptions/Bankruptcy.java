package game.exceptions;

/**
 * This exception is thrown when the net worth of the player is not enough to pay the bills discussed in {@link SeriousDebt}
 * throwing this exception will end the game for corresponded player.
 *  * It is thrown in {@link game.properties.Cinema},{@link game.properties.Prison},{@link game.properties.EmptyField},
 *  * {@link game.properties.QuestionMark},{@link game.properties.Road}.
 */
public class Bankruptcy extends MonopolyException{
    public Bankruptcy(String message) {
        super(message);
    }
}
