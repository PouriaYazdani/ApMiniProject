package game.exceptions;

import game.Player;

/**
 * This exception is thrown if the player wants to construct a building in someone else's property or
 * {@link game.properties.EmptyField#buildPermission(Player)} doesn't give the build permission.
 * It is thrown in {@link game.properties.EmptyField}.
 */
public class IllegalConstruction extends MonopolyException{
    public IllegalConstruction(String message){
        super(message);
    }
}
