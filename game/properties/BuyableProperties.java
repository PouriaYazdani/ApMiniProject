package game.properties;

import game.*;
import game.exceptions.*;

/**
 * this abstract class is superclass of all the fields that can get bargained and their owner will change as the game
 * continues.it has two baseclass -Cinema and EmptyField- which override and reuse the methods defined in this class.
 * by composition, we set each field's owner BankManager which is a singelton class.
 */

public abstract class BuyableProperties extends Field{
    protected Colors color;
    protected double purchasePrice;
    protected double baseRentPrice;
    protected Owner owner = BankManager.getInstance();

    /**
     * will do all the necessities of a transaction including changing the owner and updating cash and propertyWorth of
     * the player through getters and setter defined in Player class.This method can throw exception which will be caught
     * in gamerunner.updateMonopoly is used int this method.
     * @param player
     */
    public void buy(Player player){
        if(this.owner != BankManager.getInstance()){
            if(this.owner == player){
                throw new IllegalPurchase("You have already purchased this property!");
            }
            else{
                Player x = (Player) owner;
                throw new IllegalPurchase("This property belongs to " + x.getName()+ " you can't purchase it!");
            }
        }
        else if(purchasePrice > player.getCash()){
            throw new NotEnoughCash("You don't have enough money to buy this property! You can sell some of your properties in order to do so.");
        }
        player.setCash(player.getCash() - purchasePrice);//get the money
        this.owner = player;//give the Ownership
        player.getOwnedProperties().add(this);//add the property to the player's list of owned properties
        player.setPropertyWorth(player.getPropertyWorth() + purchasePrice/2.0);//update property worth
        updateMonopoly(player,this.getColor(),'+');
    }

    /**
     * this method used an enum based switch case to update the player's monopoly rights.
     * @param player
     * @param color
     * @param operator
     */
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
                break;
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

    /**
     * just like buy method,but it holds less similarity between Cinema and EmptyField so the details are implemented in
     * baseclasses.
     * @param player
     */
    public void sell(Player player){
        if(this.owner == BankManager.getInstance()){
            throw new IllegalSell("This property belongs to the Bank you can't sell it!");
        }
        else if(this.owner != player){
            Player x = (Player) owner;
            throw new IllegalSell("This property belongs to " + x.getName() + "you can't sell it!");
        }
        updateMonopoly(player,this.getColor(),'-');
    }

    /**
     * with this method which is implemented in baseclasses the rent cost in each field will be charged from the player's
     * cash ,and also it calculates the correct rent cost considering other belongings of the player.
     * @param player
     */
    public abstract void chargeRent(Player player);

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getBaseRentPrice() {
        return baseRentPrice;
    }

    public Owner getOwner() {
        return owner;
    }

    public Colors getColor() {
        return color;
    }
}
