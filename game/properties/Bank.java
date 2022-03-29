
package game.properties;

import game.Player;

/**
 * implements the only Bank field and the actions it's capable of via two void methods.
 */
public class Bank extends BankProperties{
    /**
     * is used in initialization process in Board constructor.
     * @see game.Board
     */
    public final static int[] atFields = {21};
    private double INVEST_RATIO = 0.5;
    private double PROFIT_RATIO = 2.0;

    public Bank(int atField){
        this.atField = atField;
    }

    /**
     * Implements the invest command and updates player's budget.
     * @param player
     */
    public void invest(Player player){
        double investedMoney = player.getCash() * INVEST_RATIO;
        player.setInvestedMoney(investedMoney);
        player.setCash(player.getCash() - investedMoney);
    }

    /**
     * is called automatically each time the player enters Bank field and returns the profit if the player's invested
     * money is not zero,and updates player's budget.
     * @param player
     */
    public void profit(Player player){
        if(player.getInvestedMoney() != 0){
            double addedCash = player.getInvestedMoney() * PROFIT_RATIO;
            player.setCash(player.getCash() + addedCash);
            player.setInvestedMoney(0);//update invested money
        }
    }


}