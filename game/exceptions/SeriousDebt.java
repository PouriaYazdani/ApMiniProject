package game.exceptions;

/**
 * This exception is thrown when the player doesn't have enough cash to MANDATORY operation such as paying the rent
 * or the road tolls and ...when this exception is thrown the player is forced to sell his/her properties to pay his/her
 * debts.
 * It is thrown in {@link game.properties.Cinema},{@link game.properties.Prison},{@link game.properties.EmptyField},
 * {@link game.properties.QuestionMark},{@link game.properties.Road}.
 * @see game.Monopoly#debtManager(int)
 */

public class SeriousDebt extends MonopolyException{
    public SeriousDebt(String message) {
        super(message);
    }
}
