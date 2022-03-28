package game.properties;

import game.Player;
import game.exceptions.IllegalDestination;
import game.exceptions.NotEnoughCashToFly;

public class Airport extends BankProperties{
    public final static int[] atFields = {3,11,20};
    private final int FLIGHT_COST = 50;

    public Airport(int atField) {
        this.atField = atField;
    }

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
}
