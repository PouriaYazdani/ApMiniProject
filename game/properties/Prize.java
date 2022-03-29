package game.properties;

import game.Player;

/**
 * implements the only prize field in the game, and it's only behaviour {@link #wonPrize(Player)}.
 */
public class Prize extends BankProperties{
    /**
     * used in initialization process in Board constructor.
     * @see game.Board
     */
    public final static int[] atFields = {6};
    private final double PRIZE_VALUE = 200.0;

    public Prize(int atField) {
        this.atField = atField;
    }

    /**
     * just adds the prize to player's cash budget.
     * @param player
     */
    public void wonPrize(Player player){
        player.setCash(player.getCash() + PRIZE_VALUE);
    }
}
