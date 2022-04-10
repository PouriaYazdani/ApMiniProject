package game.exceptions;

/**
 * Every exception thrown in this program is a subclass of this class.
 */
public class MonopolyException extends RuntimeException{
     MonopolyException(String message){
        super(message);
    }
}
