package game;

import game.exceptions.IllegalCommand;
import game.properties.*;
import org.w3c.dom.css.CSSImportRule;

import java.util.ArrayList;

public class Player implements Owner {
    private final String name;
    private double cash;
    private int position;
    private Integer lastDiceNumber;
    private ArrayList<BuyableProperties> ownedProperties = new ArrayList<BuyableProperties>();
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
        this.position = 1; //starting position of player (FreeParking)
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
        if (position+diceNum > 24){ //crossing the FreeParking
            position = (diceNum+position) % 24;
        }else {//normal
            position += diceNum;
        }
    }

    /**
     *   state method take every penalty and rent and throws exception if there is not enough cash for payment
     *   then shows the state of player (available orders)
     */
    public void state(){
        Board board = Board.getInstance();
        String defOrders = "index, rank, time, property, sell ";
        if (board.fields[position-1] instanceof FreeParking){
            FreeParking tmp = new FreeParking(1);
            tmp.enterParking();
            System.out.println("available orders are:\n"+defOrders);
        } else if (board.fields[position-1] instanceof EmptyField){
            EmptyField tmp = (EmptyField) board.fields[position-1];
            if (tmp.getOwner() == this){
                System.out.println("you are at your own property!");
                System.out.println("available orders are:\n"+defOrders
                + ",buy, build");
            }else if(tmp.getOwner() == BankManager.getInstance()){
                System.out.println("this is a bank property!");
                System.out.println("available orders are:\n"+defOrders
                + ",buy");
            }else {
                tmp.chargeRent(this);
                System.out.println("you are at someone else's property!\nwe received the rent");
                System.out.println("available orders are:\n"+defOrders);
            }

        } else if (board.fields[position-1] instanceof Airport){
            System.out.println("you are at airport NO."+position);
            System.out.println("available orders are:\n"+defOrders
            + ",fly");

        }else if (board.fields[position-1] instanceof Cinema){
            Cinema tmp = (Cinema) board.fields[position-1];
            if (tmp.getOwner() == this){
                System.out.println("you are at your own cinema!");
                System.out.println("available orders are:\n"+defOrders);
            }else if (tmp.getOwner() == BankManager.getInstance()){
                System.out.println("this cinema is a bank property, you can buy it!");
                System.out.println("available orders are:\n"+defOrders +
                ",buy");
            } else {
                tmp.chargeRent(this);
                System.out.println("you are at someone else's cinema!\nenjoy the movie!");
                System.out.println("available orders are:\n"+defOrders);
            }

        }else if (board.fields[position-1] instanceof Road){
            Road road = (Road) board.fields[position-1];
            road.payToll(this);
            System.out.println("you are on the road, the 100$ toll was received");
        }else if (board.fields[position-1] instanceof Prize){
            Prize prize = (Prize) board.fields[position-1];
            prize.wonPrize(this);
            System.out.println("you won a 200$ prize!");
            System.out.println("available orders are:\n"+defOrders);

        }else if (board.fields[position-1] instanceof Prison){
            System.out.println("don't worry, you just passed the prison");
            System.out.println("available orders are:\n"+defOrders);
        }else if (board.fields[position-1] instanceof Tax){
            Tax tax = (Tax) board.fields[position-1];
            tax.chargeTax(this);
            System.out.println("Should 10 percent appear too small, Be thankful I don't take it all");
            System.out.println("available orders are:\n"+defOrders);
        }else if (board.fields[position-1] instanceof Bank){
            System.out.println("welcome to bank!\n if you invested any money here we'll give it to you now!");
            Bank bank = (Bank) board.fields[position-1];
            bank.profit(this);
            System.out.println("you can invest your money here");
            System.out.println("available orders are:\n"+defOrders + ",invest");

        }else if (board.fields[position-1] instanceof QuestionMark){
            System.out.println("well well well! let see what's behind the cards");
            QuestionMark questionMark = (QuestionMark) board.fields[position-1];
            questionMark.randomCard(this);
        }
        updateNetWorth();
    }

    /**
     * index method shows the position of player
     */
    public void index(){
        System.out.println("you are at field " + position);
    }

    /**
     * shows the amount of cash and name of the owned properties
     */
    public void property(){
        System.out.println("cash : " + cash + "$  property worth : " + propertyWorth + "$  net worth : " + netWorth +"$");
        for (int i=0; i<ownedProperties.size();i++){
            System.out.printf("%d - ",i+1);
            if (ownedProperties.get(i) instanceof Cinema){
                System.out.println("Cinema : ");
            } else {
                System.out.println("EmptyField : ");
            }
            System.out.println("position : " + ownedProperties.get(i).getAtField() + "  color : "
                    + ownedProperties.get(i).getColor());
            if (ownedProperties.get(i) instanceof EmptyField){
                EmptyField tmp = (EmptyField) ownedProperties.get(i);
                if (tmp.isThereHotel()){
                    System.out.println(" there is a Hotel here");
                } else {
                    System.out.println(" buildings : " + tmp.getNumberOfBuildings());
                }
            }
        }
    }

    /**
     * this method receive the players orders and throws exception if they are illegal
     * otherwise it executes the command
     * @param commands player order
     * @param index for sale and fly orders
     */
    public void order(Commands commands, Integer index){
        switch (commands){
            case BUY : buy(); break;
            case BUILD: build(); break;
            case FLY: fly(index); break;
            case PROPERTY: propertyHandler(); break;
            case INVEST: invest(); break;
            case SELL: sell(index); break;
            case INDEX: indexShower(); break;
            case RANK: rankShower(); break;
        }
        updateNetWorth();
    }
    private void buy(){
        Board board = Board.getInstance();
        if (board.fields[position-1] instanceof Cinema){
            Cinema cinema = (Cinema) board.fields[position-1];
            cinema.buy(this);
            System.out.println("This Cinema is yours now!");

        } else if (board.fields[position-1] instanceof EmptyField){ // buying a EmptyField
            EmptyField emptyField = (EmptyField) board.fields[position-1];
            emptyField.buy(this);
            System.out.println("This EmptyField is yours now!");
        }else {
            Field field = board.fields[position-1];
            if (field instanceof Airport){
                throw new IllegalCommand("You can't buy a Airport in Monopoly!");
            }else if (field instanceof Bank){
                throw new IllegalCommand("You can't buy a Bank in Monopoly!");
            }else if (field instanceof FreeParking){
                throw new IllegalCommand("You can't buy a Parking in Monopoly!");
            }else if (field instanceof Prize){
                throw new IllegalCommand("You can't buy a PrizeField in Monopoly!");
            }else if (field instanceof  Prison){
                throw new IllegalCommand("You can't buy a Prison in Monopoly!");
            }else if (field instanceof Road){
                throw new IllegalCommand("You can't buy a Road in Monopoly!");
            }else if (field instanceof QuestionMark){
                throw new IllegalCommand("You can't buy a QuestionMarkField in Monopoly!");
            }else if (field instanceof Tax){
                throw new IllegalCommand("You can't buy a TaxField in Monopoly!");
            }
        }
    }
    private void build(){
        Board board = Board.getInstance();
        if (board.fields[position-1] instanceof EmptyField){
            EmptyField emptyField = (EmptyField) board.fields[position-1];
            emptyField.buy(this);
            System.out.println("Building added!");
        }else {
            Field field = board.fields[position-1];
            if (field instanceof Airport){
                throw new IllegalCommand("You can't add a building to an Airport!");
            }else if (field instanceof Bank){
                throw new IllegalCommand("You can't add a building to the Bank!");
            }else if (field instanceof Cinema){
                throw new IllegalCommand("You can't add a building to a Cinema!");
            }else if (field instanceof FreeParking){
                throw new IllegalCommand("You can't add a building to the Parking");
            }else if (field instanceof Prison){
                throw new IllegalCommand("You can't add a building to the Prison!");
            }else if (field instanceof Prize){
                throw new IllegalCommand("You can't add a building to the PrizeField!");
            }else if (field instanceof QuestionMark){
                throw new IllegalCommand("You can't add a building to the QuestionMarkField!");
            }else if (field instanceof Tax){
                throw new IllegalCommand("You can't add a building to the TaxField!");
            }else if (field instanceof Road){
                throw new IllegalCommand("You can't add a building to the Road!");
            }
        }
    }
    private void fly(Integer index){
        Board board = Board.getInstance();
        if (board.fields[position-1] instanceof Airport){
            Airport airport = (Airport) board.fields[position-1];
            airport.fly(this,index);
            System.out.println("The plane landed at the desired destination");
        }else{
            Field field = board.fields[position-1];
            if (field instanceof Bank){
                throw new IllegalCommand("You can't fly from Bank!");
            }else if(field instanceof Cinema){
                throw new IllegalCommand("You can't fly from Cinema!");
            }else if(field instanceof EmptyField){
                throw new IllegalCommand("You can't fly from EmptyField!");
            }else if (field instanceof FreeParking){
                throw new IllegalCommand("You can't fly from Parking!");
            }else if (field instanceof Prize){
                throw new IllegalCommand("You can't fly from PrizeField!");
            }else if (field instanceof Prison){
                throw new IllegalCommand("You can't fly from Prison :)");
            }else if (field instanceof QuestionMark){
                throw new IllegalCommand("You can't fly from QuestionMarkField!");
            }else if (field instanceof Road){
                throw new IllegalCommand("You can't fly from Road!");
            }else if (field instanceof Tax){
                throw new IllegalCommand("You can't fly from TaxField!");
            }
        }
    }
    private void propertyHandler(){
        this.property();
    }
    private void invest(){
        Board board = Board.getInstance();
        if (board.fields[position-1] instanceof Bank){
            Bank bank = (Bank) board.fields[position-1];
            bank.invest(this);
            System.out.println("You have invested your money at the Bank!");
        }else {
            Field field = board.fields[position-1];
            if (field instanceof Airport){
                throw new IllegalCommand("You are at Airport not the Bank");
            }else if (field instanceof Cinema){
                throw new IllegalCommand("You are at Cinema not the Bank");
            }else if (field instanceof EmptyField){
                throw new IllegalCommand("You are at EmptyField not the Bank");
            }else if (field instanceof FreeParking){
                throw new IllegalCommand("You are at FreeParking not the Bank");
            }else if (field instanceof Prison){
                throw new IllegalCommand("You are at Prison not the Bank");
            }else if (field instanceof Prize){
                throw new IllegalCommand("You are at PrizeField not the Bank");
            }else if (field instanceof QuestionMark){
                throw new IllegalCommand("You are at QuestionMarkField not the Bank");
            }else if (field instanceof Road){
                throw new IllegalCommand("You are at Road not the Bank");
            }else if (field instanceof Tax){
                throw new IllegalCommand("You are at TaxField not the Bank");
            }
        }
    }
    private void sell(Integer index){
        Board board = Board.getInstance();
        if (board.fields[index-1] instanceof EmptyField){
            EmptyField emptyField = (EmptyField) board.fields[index-1];
            emptyField.sell(this);
            System.out.println("You have sold this Field to the Bank!");
        }else if (board.fields[index-1] instanceof Cinema) {
            Cinema cinema = (Cinema) board.fields[index - 1];
            cinema.sell(this);
            System.out.println("You have sold this cinema to the Bank!");
        }else {
            Field field = board.fields[index-1];
            if (field instanceof Airport){
                throw new IllegalCommand("You can't sell an Airport");
            }else if (field instanceof Bank){
                throw new IllegalCommand("You can't sell a Bank");
            }else if (field instanceof FreeParking){
                throw new IllegalCommand("You can't sell a Parking");
            }else if (field instanceof Prison){
                throw new IllegalCommand("You can't sell a Prion");
            }else if (field instanceof Prize){
                throw new IllegalCommand("You can't sell a PrizeBank");
            }else if (field instanceof QuestionMark){
                throw new IllegalCommand("You can't sell a QuestionMarkField");
            }else if (field instanceof Road){
                throw new IllegalCommand("You can't sell a Road");
            }else if (field instanceof Tax){
                throw new IllegalCommand("You can't sell a TaxField");
            }
        }

    }
    private void indexShower(){
        this.index();
    }
    private void rankShower(){
        this.rank();
    }
    /**
     * rank method shows the rank of the player by getting a sorted list of Players
     */
    public void rank(){
        /*ArrayList<Player> sorted = BankManager.getSortedList();
        for (int i=0;i<sorted.size();i++){
            if (sorted.get(i) == this){
                rank = i+1;
            }
        }*/
        System.out.println("Rank : " + rank);
    }
    
    public void updateNetWorth(){
        netWorth = propertyWorth + cash;
    }
}
