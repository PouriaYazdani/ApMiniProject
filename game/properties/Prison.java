package game.properties;

import game.Player;

public class Prison extends BankProperties{
    public final static int[] atFields = {13};

    public Prison(int atField){
        this.atField = atField;
    }
    public static void imprisonment(Player player){
        if (player.getNoJail() > 0){
            System.out.println("You had a NO_JAIL coupon! Be careful next time.");
            player.setNoJail(player.getNoJail() - 1);
        } else {
            player.setInJail(true);
            player.setPosition(atFields[0]); // change the position of player to it's
        }
    }

}
