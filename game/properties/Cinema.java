package game.properties;

import game.BankManager;
import game.Player;
import game.exceptions.IllegalPurchase;

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
        if(this.owner != BankManager.getInstance()){
            throw new IllegalPurchase("This property belongs to " + (Player)owner.getName()+ " you can't purchase it!");
        }
        player.setCash(player.getCash() - purchasePrice);//get the money
        this.owner = player;//give the Ownership
        player.getOwnedProperties().add(this);//add the property to the player's list of owned properties
        int newNetWorth = player.getNetWorth() + (purchasePrice/2 - purchasePrice);
        player.setNetWorth(newNetWorth);//update netWorth
        player.setOwnedCinemas(player.getOwnedCinemas() + 1);//update number of owned cinemas
    }

    @Override
    public void sell(Player player){

    }

}
