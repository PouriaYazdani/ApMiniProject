package game.properties;

import game.Player;

public class Tax extends BankProperties{
    public final static int[] atFields = {17};
    private final double TAX_RATIO = 0.1;

    public Tax(int atField) {
        this.atField = atField;
    }

    public void chargeTax(Player player){
        if(player.getNoTax() > 0){
            System.out.println("You had a NO_TAX coupon! Bee careful next time.");
            player.setNoTax(player.getNoTax() - 1);
        }
        double chargedTax = player.getCash() * TAX_RATIO;
        player.setCash(player.getCash() - chargedTax);
    }
}
