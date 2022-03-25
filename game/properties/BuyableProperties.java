package game.properties;

import game.*;

public abstract class BuyableProperties extends Field{
    protected Colors color;
    protected int purchasePrice;
    protected int currentRentPrice;
    protected Owner owner = BankManager.getInstance();

    public abstract void buy(Player player);

    public abstract void sell(Player player);

    public abstract void chargeRent(Player player);

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public int getCurrentRentPrice() {
        return currentRentPrice;
    }

    public Owner getOwner() {
        return owner;
    }

    public Colors getColor() {
        return color;
    }
}
