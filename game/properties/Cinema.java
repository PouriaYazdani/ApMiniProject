package game.properties;

import game.*;
import game.exceptions.*;

/**
 * this class implements all the commands and actions related to cinema field in the game.it has several void functions
 * that do the necessary operations.{@link #atFields} static array is used in initialization process in Board constructor.
 * @see game.Board
 */
public class Cinema extends BuyableProperties{
    public final static int[] atFields = {4,8,15,22};
    {
        purchasePrice = 200.0;
        baseRentPrice = 25.0;
    }

    /**
     * is the only constructor of this class which will decide the color of the field by its parameter and will also
     * assign the field's number in board.
     * @param atField
     */
    public Cinema(int atField){
        switch (atField){
            case 4:
                color = Colors.RED;
                break;
            case 8:
                color = Colors.BLUE;
                break;
            case 15:
                color = Colors.GREEN;
                break;
            case 22:
                color = Colors.YELLOW;
        }
        this.atField = atField;
    }

    @Override
    /**
     * uses superclass implementation but with the added operation of updating the player's number of owned cinemas.
     */
    public void buy(Player player){
        super.buy(player);
        player.setOwnedCinemas(player.getOwnedCinemas() + 1);//update number of owned cinemas
    }

    @Override
    /**
     * uses superclass implementation but the details are implemented here.
     */
    public void sell(Player player){
        super.sell(player);
        player.setCash(player.getCash() + purchasePrice/2.0);//give the money
        this.owner = BankManager.getInstance();//take the Ownership
        player.getOwnedProperties().remove(this);//remove the sold properties from the player's list of owned properties
        player.setPropertyWorth(player.getPropertyWorth() - purchasePrice/2.0);//update property worth
        player.setOwnedCinemas(player.getOwnedCinemas() - 1);//update number of owned cinemas
    }

    @Override
    /**
     * calculates the correct rent cost under different circumstances and can throw an exception which will get caught
     * in gamerunner.
     */
    public void chargeRent(Player player){
        double rent = 0;
        if(this.owner != BankManager.getInstance()){
            Player fieldOwner = (Player)owner;
            switch (fieldOwner.getOwnedCinemas()){
                case 1:
                      rent = baseRentPrice * 1.0;
                    break;
                case 2:
                      rent = baseRentPrice * 2.0;
                    break;
                case 3:
                      rent = baseRentPrice * 4.0;
                    break;
                case 4:
                      rent = baseRentPrice * 8.0;
            }
            boolean boost = false;
            switch (this.getColor()){//code reuse should be considered later
                case RED:
                    boost = fieldOwner.redMonopoly;
                    break;
                case BLUE:
                    boost = fieldOwner.blueMonopoly;
                    break;
                case GREEN:
                    boost = fieldOwner.greenMonopoly;
                    break;
                case YELLOW:
                    boost = fieldOwner.yellowMonopoly;
            }
            if(boost)
                rent *= 2.0;
            if(rent > player.getCash()){
                throw new SeriousDebt("You don't have enough money to watch a movie! Try selling you're properties");
            }
            player.setCash(player.getCash() - rent);
            ((Player) owner).setCash(((Player) owner).getCash() + rent);//transfer the rent to owner's pocket
        }

    }
}
