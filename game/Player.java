package game;

import game.properties.BuyableProperties;

import java.util.ArrayList;

public class Player implements Owner {
    private final String name;
    private int cash;
    private int position;
    private Integer lastDiceNumber;
    private ArrayList<BuyableProperties> ownedProperties;
    private int propertyWorth;
    private int netWorth;
    private int noTax;
    private int noJail;
    private int ownedCinemas;
    private int investedMoney = 0;
    private int rank;
    private boolean inJail;
    private final int BUILDINGS_LIMIT = 5;
    private int builtBuilding;
    // these counters are going to use in emptyField Class, if they equal 3 it means it's Monopoly!
    // (the specific boolean will be True)
    // if Monopoly happens the rent price will be double!
    public int blueProperties;
    public int redProperties;
    public int greenProperties;
    public int yellowProperties;
    public boolean blueMonopoly;
    public boolean redMonopoly;
    public boolean greenMonopoly;
    public boolean yellowMonopoly;
    public Player(String name, int cash) {
        this.name = name;
        this.cash = cash;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getNoTax() {
        return noTax;
    }

    public void setNoTax(int noTax) {
        this.noTax = noTax;
    }

    public int getNoJail() {
        return noJail;
    }

    public void setNoJail(int noJail) {
        this.noJail = noJail;
    }

    public String getName() {
        return name;
    }

    public int getPropertyWorth() {
        return propertyWorth;
    }

    public int getNetWorth() {
        return netWorth;
    }

    public int getOwnedCinemas() {
        return ownedCinemas;
    }

    public int getInvestedMoney() {
        return investedMoney;
    }

    public boolean isInJail() {
        return inJail;
    }

    public ArrayList<BuyableProperties> getOwnedProperties() {
        return ownedProperties;
    }

    public void setOwnedProperties(ArrayList<BuyableProperties> ownedProperties) {
        this.ownedProperties = ownedProperties;
    }
    
    public int getBuiltBuilding() {
        return builtBuilding;
    }

    public void setBuiltBuilding(int builtBuilding) {
        this.builtBuilding = builtBuilding;
    }

    public void setPropertyWorth(int propertyWorth) {
        this.propertyWorth = propertyWorth;
    }

    public void setNetWorth(int netWorth) {
        this.netWorth = netWorth;
    }

    public void setOwnedCinemas(int ownedCinemas) {
        this.ownedCinemas = ownedCinemas;
    }
    
    //move method revive a die number and set the new position
    public void move(int diceNum){
    }
    // state method shows the state of player (position and available orders)
    // than take every penalty and rent and throws exception if there is not enough cash for payment
    public void state(){
    }
    //index method shows the position of player
    public void index(){
    }
    //shows the amount of cash and name of the owned properties
    public void property(){
    }
    // rank method shows the rank of the player by getting a sorted list of Players
    public void rank(){
    }
    // The buildLicence method checks whether each empty field of the player has the least amount of building,
    // then returns true and the player can add another building to the desired empty field.
    public boolean buildLicence(){
        return false;
    }
}
