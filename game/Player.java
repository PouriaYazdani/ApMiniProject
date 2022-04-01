package game;

import game.properties.*;

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

    /**
     * move method receive a die number and set the new position
     * @param diceNum diceNum entered dice
     */
    public void move(int diceNum){
        lastDiceNumber = diceNum; //save the dice number
        position += diceNum; // move the player
    }

    /**
     *   state method take every penalty and rent and throws exception if there is not enough cash for payment
     *   then shows the state of player (available orders)
     */
    public void state(){
        Board board = Board.getInstance();
        String defOrders = "index, rank, time, property ";
        if (board.fields[position-1] instanceof FreeParking){
            FreeParking tmp = new FreeParking(1);
            tmp.enterParking();
            System.out.println("available orders are:\n"+defOrders);
        } else if (board.fields[position-1] instanceof EmptyField){

        } else if (board.fields[position-1] instanceof Airport){

        }else if (board.fields[position-1] instanceof Cinema){

        }else if (board.fields[position-1] instanceof Road){

        }else if (board.fields[position-1] instanceof Prize){

        }else if (board.fields[position-1] instanceof Prison){

        }else if (board.fields[position-1] instanceof Tax){

        }else if (board.fields[position-1] instanceof Bank){

        }else if (board.fields[position-1] instanceof QuestionMark){

        }
    }

    /**
     * index method shows the position of player
     */
    public void index(){
        System.out.println("you are at field " + position);
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
    public void updateNetWorth(){
        netWorth = propertyWorth + cash;
    }
}
