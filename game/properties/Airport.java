package game.properties;

import game.Player;
import game.exceptions.IllegalDestination;
import game.exceptions.NotEnoughCashToFly;

/**
 * this class implements airport fields in the board and contains the only command specifically related to it  {@link
 * #fly(Player, int)}.
 */
public class Airport extends BankProperties{
    /**
     * is used in initialization process in Board constructor.
     */
    public final static int[] atFields = {3,11,20};
    private final int FLIGHT_COST = 50;

    public Airport(int atField) {
        this.atField = atField;
    }

    /**
     * throws exceptions which will get caught in gamerunner if the player sends invalid arguments and also charges the
     * flight cost from the player.
     * @param player
     * @param destination
     */
    public void fly(Player player,int destination){
        if(destination == atField){
            throw new IllegalDestination("You are already in airport NO." + atField + "!");
        }
        else if(!isIn(destination)){
            throw new IllegalDestination("There is no airport in you're requested destination!");
        }
        else if(player.getCash() < FLIGHT_COST){
            throw new NotEnoughCashToFly("You don't have enough cash to pay for the flight! You can sell you're properties in order to do so.");
        }
        player.setCash(player.getCash() - FLIGHT_COST);
        player.setPosition(destination);
    }

    /**
     * simple method to check if argument x is in {@link #atFields}.
     * @param x
     * @return whether if there is an airport in destination or not.
     */
    private boolean isIn(int x){
        x++;
        for (int i = 0; i < atFields.length; i++) {
            if(atFields[i] == x)
                return true;
        }
        return false;
    }
}
