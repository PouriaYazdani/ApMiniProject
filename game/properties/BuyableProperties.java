package game.properties;

import game.*;
import game.exceptions.IllegalPurchase;
import game.exceptions.IllegalSell;
import game.exceptions.NotEnoughCashToBuy;

public abstract class BuyableProperties extends Field{
    protected Colors color;
    protected int purchasePrice;
    protected int baseRentPrice;
    protected Owner owner = BankManager.getInstance();

    public void buy(Player player){
        if(this.owner != BankManager.getInstance()){
            if(this.owner == player){
                throw new IllegalPurchase("You have already purchased this property!");
            }
            else{
                throw new IllegalPurchase("This property belongs to " + (Player)owner.getName()+ " you can't purchase it!");
            }
        }
        else if(purchasePrice > player.getCash()){
            throw new NotEnoughCashToBuy("You don't have enough money to buy this property! You can sell some of your properties in order to do so.");
        }
        player.setCash(player.getCash() - purchasePrice);//get the money
        this.owner = player;//give the Ownership
        player.getOwnedProperties().add(this);//add the property to the player's list of owned properties
        player.setPropertyWorth(player.getPropertyWorth() + purchasePrice/2);//update property worth
        int newNetWorth = player.getCash() + player.getPropertyWorth();
        player.setNetWorth(newNetWorth);//update netWorth
    }

    public void sell(Player player){
        if(this.owner == BankManager.getInstance()){
            throw new IllegalSell("This property belongs to the Bank you can't sell it!");
        }
        else if(this.owner != player){
            throw new IllegalSell("This property belongs to " + (Player)owner.getName() + "you can't sell it!");
        }
    }

    public abstract void chargeRent(Player player);

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public int getBaseRentPrice() {
        return baseRentPrice;
    }

    public Owner getOwner() {
        return owner;
    }

    public Colors getColor() {
        return color;
    }
}
