package game.properties;

import game.*;
import game.exceptions.*;
import java.util.ArrayList;
/**
 * this class implements all the commands and actions related to empty Fields field in the game.it has several void
 * functions that do the necessary operations.Buy method in superclass is not overridden here because the necessaries
 * were already implemented in super.buy().{@link #atFields} static array is used in initialization process in Board
 * constructor .
 * @see game.Board
 */
public class EmptyField extends BuyableProperties{
    public final static int[] atFields = {2,7,9,12,14,18,19,23};
    private int numberOfBuildings;
    private boolean isThereHotel = false;
    private final int MAXIMUM_BUILDINGS = 4;
    private final double BUILDING_COST = 150.0;
    private final double HOTEL_LICENSE_COST = 100.0;
    private final double MAXIMUM_EMPTYFIELD_WORTH = 800.0;
    {
        purchasePrice = 100.0;
        baseRentPrice = 50.0;
    }

    /**
     * is the only constructor of this class which will assign the number of field in the board and its color through
     * its parameter.
     * @param atField
     */
    public EmptyField(int atField){
        switch(atField){
            case 2:
            case 12:
                this.color = Colors.GREEN;
                break;
            case 7:
            case 19:
                this.color = Colors.YELLOW;
                break;
            case 9:
            case 18:
                this.color = Colors.RED;
                break;
            case 14:
            case 23:
                this.color = Colors.BLUE;
        }
        this.atField = atField;
    }

    @Override
    /**
     * uses superclass implementation but the details are implemented here.
     */
    public void sell(Player player){
        super.sell(player);
        double emptyFieldWorth = 0;
        if(isThereHotel){
            emptyFieldWorth = MAXIMUM_EMPTYFIELD_WORTH;
        }
        else {
            emptyFieldWorth = purchasePrice + (numberOfBuildings * BUILDING_COST);
        }
        player.setCash(player.getCash() + emptyFieldWorth/2.0);//give the money
        this.owner = BankManager.getInstance();//take the Ownership
        player.getOwnedProperties().remove(this);//remove the sold properties from the player's list of owned properties
        player.setPropertyWorth(player.getPropertyWorth() - emptyFieldWorth/2.0);//update property worth
        player.setBuiltBuildings(player.getBuiltBuildings() - numberOfBuildings);//update player's number of built buildings
        numberOfBuildings = 0;
        isThereHotel = false;
    }

    @Override
    /*
     * calculates the correct rent cost under different circumstances,sets number of buildings to zero and can throw
     * an exception which will get caught in gamerunner.
     */
    public void chargeRent(Player player){
        double rent = 0;
        if(isThereHotel){
            rent = 600.0;
        }
        else {
            switch (numberOfBuildings){
                case 0:
                    rent = baseRentPrice;
                    break;
                case 1:
                    rent = baseRentPrice + 100.0;
                    break;
                case 2:
                    rent = baseRentPrice + 200.0;
                    break;
                case 3:
                    rent = baseRentPrice + 300.0;
                    break;
                case 4:
                    rent = baseRentPrice + 400.0;
            }
        }
        boolean boost = false;
        switch (this.getColor()){//code reuse should be considered later
            case RED:
                boost = player.redMonopoly;
            case BLUE:
                boost = player.blueMonopoly;
            case GREEN:
                boost = player.greenMonopoly;
            case YELLOW:
                boost = player.yellowMonopoly;
        }
        if(boost)
            rent *= 2.0;
        if(rent > player.getCash()){
            throw new NotEnoughCashToRent("You don't have enough money to pay the rent of this field! Try selling you're properties");
        }
        player.setCash(player.getCash() - rent);
    }

    /**
     * it handles the build command and decides to construct a building or a hotel considering number of building in this
     * field and then will charge the cost of construction.If the player wanted to build a hotel {@link #buildHotel(Player)}
     * is called through this method.Handles several error which may be made by the player the thrown exception are
     * caught in gamerunner.
     * @param player
     */
    public void addBuilding(Player player){
        if(player.getCash() < BUILDING_COST){//does player have enough cash?
            throw new NotEnoughCashToBuild("You do not have enough cash to construct building/hotel! Try selling you're properties");
        }
        else if(!buildPermission(player)){//did the player distribute the building correctly?
            throw new IllegalConstruction("You have not distributed you're buildings correctly!");
        }
        else if(player.getBuiltBuildings() == 5){//has the player reached construction limit?
            throw new IllegalConstruction("You have reached you're building construction limit!");
        }
        else if(isThereHotel){//already built a hotel?
            throw new IllegalConstruction("You have already constructed a hotel in this field! there's nothing else you can do.");
        }
        else if(this.owner != player){//who's the owner?
            Player fieldOwner = (Player)owner;
            throw new IllegalConstruction("this field belongs to " + fieldOwner.getName() + "! you can't construct a building/hotel here.");
        }
        else if(numberOfBuildings == MAXIMUM_BUILDINGS){//does the player want to build a hotel?
            buildHotel(player);
            return;
        }
        player.setCash(player.getCash() - BUILDING_COST);//take the money
        player.setBuiltBuildings(player.getBuiltBuildings() + 1);//update number of built buildings by the player
        numberOfBuildings++;
    }

    /**
     * will build a hotel and update number of buildings constructed by the player.
     * @param player
     */
    private void buildHotel(Player player){
        if(player.getCash() < HOTEL_LICENSE_COST){
            throw new IllegalConstruction("You do not have enough cash to construct a hotel! Try selling you're properties.");
        }
        isThereHotel = true;
        player.setCash(player.getCash() - HOTEL_LICENSE_COST);//take the money
        player.setBuiltBuildings(player.getBuiltBuildings() - 4 + 1);
        numberOfBuildings = 1;
    }

    /**
     * is called from {@link #addBuilding(Player)} checks the necessary qualifications to construct a new building,also
     * ignores the distribution if a hotel is built in a field.
     * @param player
     * @return whether the players have distributed their building correctly
     */
    private boolean buildPermission(Player player) {//only checks distribution
        ArrayList ownedProperties = player.getOwnedProperties();
        for (int i = 0; i < ownedProperties.size(); i++) {
            if (ownedProperties.get(i) instanceof EmptyField) {
                if (((EmptyField) ownedProperties.get(i)).isThereHotel || ownedProperties.get(i) == this) {
                    continue;
                }
                if(((EmptyField) ownedProperties.get(i)).numberOfBuildings < this.numberOfBuildings) {//***possible bug for not handling all scenarios***
                    return false;
                }
            }
        }
        return true;
    }
}