package game.properties;

import game.Player;
import game.exceptions.Bankruptcy;
import game.exceptions.SeriousDebt;

/**
 * This class represents road fields in the game.
 */
public class Road extends BankProperties{
    public final static int[] atFields = {5,10,16};
    private final double TOLL_PRICE = 100.0;
    public Road(int atField) {
        this.atField = atField;
    }

    /**
     * simple method to charge the player {@link #TOLL_PRICE} and checks if he/she can pay it or not.
     * @param player
     */
    public void payToll(Player player){
        if (player.getCash() < TOLL_PRICE){
            if(TOLL_PRICE > player.getNetWorth()){
                throw new Bankruptcy("You do not have enough net worth to pay the toll," + player.getName() + " the game " +
                        "is OVER for you");
            }
            player.setDebt(TOLL_PRICE);
            throw new SeriousDebt("you don't have enough cash to pay your toll, try selling your property in order to do so");
        }
        player.setCash(player.getCash() - TOLL_PRICE);// getting a 100$ toll
    }
}
