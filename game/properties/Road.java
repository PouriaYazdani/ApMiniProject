package game.properties;

import game.Player;

/**
 *  every player must pay a 100$ toll then they can pass
 */
public class Road extends BankProperties{
    public final static int[] atFields = {5,10,16};

    public Road(int atField) {
        this.atField = atField;
    }
    public void payToll(Player player){
        player.setCash(player.getCash() - 100);//100$ toll
    }
}
