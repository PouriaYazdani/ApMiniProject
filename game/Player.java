package game;

import game.properties.BuyableProperties;

public class Player implements Owner {
    private final String name;
    private int cash;
    private int position;
    private Integer lastDiceNumber;
    BuyableProperties[] properties;
    private int propertyWorth;
    private int netWorth;
    private int noTax;
    private int noJail;
    private int ownedCinemas;
    private int investedMoney = 0;
    private int rank;
    private boolean inJail;
}
