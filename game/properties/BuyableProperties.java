package game.properties;

import game.*;

public abstract class BuyableProperties extends Field{
    protected Colors color;
    protected int purchasePrice;
    protected int baseRentPrice;
    protected Owner owner = BankManager.getInstance();

    public abstract void buy(Player player);

    public abstract void sell(Player player);

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
