package game.properties;

import game.*;
import game.exceptions.*;

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
        updateMonopoly(player,this.getColor(),'+');
    }

    protected void updateMonopoly(Player player,Colors color,char operator){
        switch (color){
            case BLUE:
                if(operator == '+') {
                    player.blueProperties++;
                    if (player.blueProperties == 3)
                        player.blueMonopoly = true;
                }
                else {
                    player.blueProperties--;
                    if(player.blueProperties + 1 == 3){
                        player.blueMonopoly = false;
                    }
                }
                break;
            case RED:
                if(operator == '+') {
                    player.redProperties++;
                    if(player.redProperties == 3)
                        player.redMonopoly = true;
                }
                else {
                    player.redProperties--;
                    if(player.redProperties + 1 == 3)
                        player.redMonopoly = false;
                }
                break;
            case GREEN:
                if(operator == '+') {
                    player.greenProperties++;
                    if(player.greenProperties == 3)
                        player.greenMonopoly = true;
                }
                else {
                    player.greenProperties--;
                    if(player.greenProperties + 1 == 3)
                        player.greenMonopoly = false;
                }
            case YELLOW:
                if(operator == '+') {
                    player.yellowProperties++;
                    if(player.yellowProperties == 3)
                        player.yellowMonopoly = true;
                }
                else {
                    player.yellowProperties--;
                    if(player.yellowProperties + 1 == 3)
                        player.yellowMonopoly = false;
                }
        }
    }

    public void sell(Player player){
        if(this.owner == BankManager.getInstance()){
            throw new IllegalSell("This property belongs to the Bank you can't sell it!");
        }
        else if(this.owner != player){
            throw new IllegalSell("This property belongs to " + (Player)owner.getName() + "you can't sell it!");
        }
        updateMonopoly(player,this.getColor(),'-');
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
