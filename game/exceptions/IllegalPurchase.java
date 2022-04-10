package game.exceptions;

import game.Player;

/**
 * This exception is thrown when a player wants to buy someone else's property or a property which is already his/hers.
 * It is thrown in {@link game.properties.BuyableProperties} and it's subclasses.
 * @see game.properties.Cinema#buy(Player)
 * @see game.properties.EmptyField#buy(Player)
 */
public class IllegalPurchase extends MonopolyException{
    public IllegalPurchase(String message){
        super(message);
    }
}
