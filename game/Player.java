package game;

import game.properties.BuyableProperties;

import java.util.ArrayList;

public class Player implements Owner {
    private final String name;
    private double cash;
    private int position;
    private Integer lastDiceNumber;
    private ArrayList<BuyableProperties> ownedProperties;
    private double propertyWorth;
    private double netWorth;
    private int noTax;
    private int noJail;
    private int ownedCinemas;
    private double investedMoney = 0;
    private int rank;
    private boolean inJail;
    private final int BUILDINGS_LIMIT = 5;
    private int builtBuildings;
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
    public Player(String name, double cash) {
        this.name = name;
        this.cash = cash;
    }

    public void setPropertyWorth(double propertyWorth) {
        this.propertyWorth = propertyWorth;
    }

    public void setNetWorth(double netWorth) {
        this.netWorth = netWorth;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
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

    public double getPropertyWorth() {
        return propertyWorth;
    }

    public double getNetWorth() {
        return netWorth;
    }

    public int getOwnedCinemas() {
        return ownedCinemas;
    }

    public double getInvestedMoney() {
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
    
    public int getBuiltBuildings() {
        return builtBuildings;
    }

    public void setBuiltBuildings(int builtBuildings) {
        this.builtBuildings = builtBuildings;
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

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    public void setLastDiceNumber(Integer lastDiceNumber) {
        this.lastDiceNumber = lastDiceNumber;
    }

    public void setInvestedMoney(double investedMoney) {
        this.investedMoney = investedMoney;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Integer getLastDiceNumber() {
        return lastDiceNumber;
    }

    public int getRank() {
        return rank;
    }

    public int getBUILDINGS_LIMIT() {
        return BUILDINGS_LIMIT;
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
