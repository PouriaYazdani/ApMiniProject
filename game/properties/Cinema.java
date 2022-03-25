package game.properties;

import game.Player;

public class Cinema extends BuyableProperties{
    public final static int[] atFields = {4,8,15,22};
    {
        purchasePrice = 200;
        currentRentPrice = 0;
    }

    public Cinema(int atField){
        switch (atField){
            case 4:
                color = Colors.RED;
                break;
            case 8:
                color = Colors.BLUE;
                break;
            case 15:
                color = Colors.GREEN;
                break;
            case 22:
                color = Colors.YELLOW;
        }
        this.atField = atField;
    }

    @Override
    public void buy(Player player){

    }

    @Override
    public void sell(Player player){

    }

}
