package game.properties;

import game.Player;
import game.exceptions.NotEnoughCashForFreedom;

/**
 *  with this class's methods we'll be able to put a player in jail or get them out
 */
public class Prison extends BankProperties{
    public final static int[] atFields = {13};
    private final double FREEDOM_COST = 50.0;
    private final double JAIL_COST = 10.0;
    public Prison(int atField){
        this.atField = atField;
    }

    /**
     * this method checks if the player has a freedom coupon, if they haven't any we'll imprison them
     * @param player
     */
    public static void imprisonment(Player player){
        if (player.getNoJail() > 0){
            System.out.println("You had a NO_JAIL coupon! Be careful next time.");
            player.setNoJail(player.getNoJail() - 1);
        } else {
            player.setInJail(true);
            player.setPosition(atFields[0]); // change the position of player to it's
        }
    }

    /**
     * If the players have $ 50, we will release them
     * @param player
     */
    public void free(Player player){
        if (player.getCash() < FREEDOM_COST){
            throw new NotEnoughCashForFreedom("you haven't enough cash to get outta here, Try sell your properties in order to do so");
        }
        player.setCash(player.getCash() - FREEDOM_COST);
        player.setInJail(false);
        System.out.println("You're free to go!");
    }

    /**
     * If the dice number is 1, they will be released, otherwise they have to pay $ 10 and wait for the next round
     * @param player
     */
    public void luckyDice(Player player){
        if (player.getLastDiceNumber() == 1){
            player.setInJail(false);
            System.out.println("You're free to go!");
        }else {
            player.setCash(player.getCash() - JAIL_COST);
            System.out.println("You weren't lucky, It costs you 10$");
        }
    }
}
