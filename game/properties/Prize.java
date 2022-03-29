package game.properties;

import game.Player;

public class Prize extends BankProperties{
    public final static int[] atFields = {6};
    private final int PRIZE_VALUE = 200;

    public Prize(int atField) {
        this.atField = atField;
    }

    public void wonPrize(Player player){
        player.setCash(player.getCash() + PRIZE_VALUE);
    }
}
