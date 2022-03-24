package game.properties;

import game.Owner;

public abstract class BuyableProperties extends Field{
    protected Colors color;
    protected int purchasePrice;
    protected int currentRentPrice;
    protected Owner owner;

    public abstract void buy();

    public abstract void sell();

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
