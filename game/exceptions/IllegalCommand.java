package game.exceptions;

/**
 * This exception is thrown whenever a command is not allowed in a particular situation ,or it's argument is not valid
 * and finally if the entered command is not defined in {@link game.Commands}.
 * It is thrown in {@link game.Monopoly},{@link game.BankManager} and {@link game.Player}.
 */
public class IllegalCommand extends MonopolyException{
    public IllegalCommand(String message) {
        super(message);
    }
}
