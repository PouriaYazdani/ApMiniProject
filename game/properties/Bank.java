package game.properties;

import game.Player;

public class Bank extends BankProperties{
    public final static int[] atFields = {21};
    private double INVEST_RATIO = 0.5;
    private int PROFIT_RATIO = 2;

    public Bank(int atField){
        this.atField = atField;
    }

    public void invest(Player player){

    }

    public void profit(Player player){

    }


}
