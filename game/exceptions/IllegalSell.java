package game.exceptions;

/**
 * this exception is thrown when the player wants to sell a property which is not his/her.
 * It is thrown in {@link game.properties.BuyableProperties} and it's subclasses.
 * @see game.properties.Cinema
 * @see game.properties.EmptyField
 */
public class IllegalSell extends MonopolyException{
    public IllegalSell(String message){
        super(message);
    }
}
