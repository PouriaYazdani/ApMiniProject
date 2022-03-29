package game.properties;

import game.Player;

/**
 * implements the only tax field in the game.
 */
public class Tax extends BankProperties{
    /**
     * used in initialization process in Board constructor.
     * @see game.Board
     */
    public final static int[] atFields = {17};
    private final double TAX_RATIO = 0.1;

    public Tax(int atField) {
        this.atField = atField;
    }

    /**
     * is called automatically each time a player enters tax field and will make the player pay the tax with specific
     * {@link #TAX_RATIO}.
     * @param player
     */
    public void chargeTax(Player player){
        if(player.getNoTax() > 0){
            System.out.println("You had a NO_TAX coupon! Bee careful next time.");
            player.setNoTax(player.getNoTax() - 1);
        }
        double chargedTax = player.getCash() * TAX_RATIO;
        player.setCash(player.getCash() - chargedTax);
    }
}
