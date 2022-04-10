package game;

import game.exceptions.IllegalCommand;
import game.properties.*;

import java.util.*;

/**
 * This singelton class represents bank manager of the game .It has access to all the players via arraylist {@link #sortedList}.
 * It handles the process of ending the game via {@link #endGame()} and sorts the players via {@link Collections} which
 * is used for {@link Commands#RANK}.
 */
public class BankManager implements Owner{
    private static BankManager bankManager;
    private ArrayList<Player> sortedList;
    /**
     * Stores all the player's names.
     */
    private String[] allPlayers;
    private BankManager(){
        sortedList =  Monopoly.getPlayers();
        allPlayers = Monopoly.getPlayersName();
    }

    public static BankManager getInstance() {
        if(bankManager == null)
            bankManager = new BankManager();
        return bankManager;
    }

    /**
     * Sorts the Players based on their {@link Player#netWorth}.
     */
    private void sort(){
        Collections.sort(sortedList,Collections.reverseOrder(new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return Double.compare(o1.getNetWorth(),o2.getNetWorth());
            }
        }));
    }
    public ArrayList getSortedList() {
        sort();
        return sortedList;
    }

    /**
     * Are used in swapping wealth process and are the core of {@link #swapProp()}.
     */
    Integer firstPlayerIndex, secondPlayerIndex;

    /**
     * Is called in {@link Monopoly#gamerunner()} when {@link Commands#SWAP_WEALTH} is invoked.
     * and executes it with the help of {@link #swapWealth(String, String)} and {@link #swapProp()}.
     * @param firstPlayerName
     * @param secondPlayerName
     */
    public void swapWealth(String firstPlayerName,String secondPlayerName){
        validatePlayers(firstPlayerName,secondPlayerName);
        swapProp();
    }

    /**
     * This method swaps the necessary properties and fields between arguments of {@link Commands#SWAP_WEALTH} command.
     */
    private void swapProp(){
        double money = 0;
        money = sortedList.get(firstPlayerIndex).getCash();
        sortedList.get(firstPlayerIndex).setCash(sortedList.get(secondPlayerIndex).getCash());
        sortedList.get(secondPlayerIndex).setCash(money);
        money = sortedList.get(firstPlayerIndex).getPropertyWorth();
        sortedList.get(firstPlayerIndex).setPropertyWorth(sortedList.get(secondPlayerIndex).getPropertyWorth());
        sortedList.get(secondPlayerIndex).setPropertyWorth(money);
        money = sortedList.get(firstPlayerIndex).getNetWorth();
        sortedList.get(firstPlayerIndex).setNetWorth(sortedList.get(secondPlayerIndex).getNetWorth());
        sortedList.get(secondPlayerIndex).setNetWorth(money);
        money = sortedList.get(firstPlayerIndex).getInvestedMoney();
        sortedList.get(firstPlayerIndex).setInvestedMoney(sortedList.get(secondPlayerIndex).getInvestedMoney());
        sortedList.get(secondPlayerIndex).setInvestedMoney(money);
        int count = 0;
        count = sortedList.get(firstPlayerIndex).blueProperties;
        sortedList.get(firstPlayerIndex).blueProperties = sortedList.get(secondPlayerIndex).blueProperties;
        sortedList.get(secondPlayerIndex).blueProperties = count;
        count = sortedList.get(firstPlayerIndex).redProperties;
        sortedList.get(firstPlayerIndex).redProperties = sortedList.get(secondPlayerIndex).redProperties;
        sortedList.get(secondPlayerIndex).redProperties = count;
        count = sortedList.get(firstPlayerIndex).greenProperties;
        sortedList.get(firstPlayerIndex).greenProperties = sortedList.get(secondPlayerIndex).greenProperties;
        sortedList.get(secondPlayerIndex).greenProperties = count;
        count = sortedList.get(firstPlayerIndex).yellowProperties;
        sortedList.get(firstPlayerIndex).yellowProperties = sortedList.get(secondPlayerIndex).yellowProperties;
        sortedList.get(secondPlayerIndex).yellowProperties = count;
        count = sortedList.get(firstPlayerIndex).getBuiltBuildings();
        sortedList.get(firstPlayerIndex).setBuiltBuildings(sortedList.get(secondPlayerIndex).getBuiltBuildings());
        sortedList.get(secondPlayerIndex).setBuiltBuildings(count);
        count = sortedList.get(firstPlayerIndex).getOwnedCinemas();
        sortedList.get(firstPlayerIndex).setOwnedCinemas(sortedList.get(secondPlayerIndex).getOwnedCinemas());
        sortedList.get(secondPlayerIndex).setOwnedCinemas(count);
        boolean monopoly = false;
        monopoly = sortedList.get(firstPlayerIndex).blueMonopoly;
        sortedList.get(firstPlayerIndex).blueMonopoly = sortedList.get(secondPlayerIndex).blueMonopoly;
        sortedList.get(secondPlayerIndex).blueMonopoly = monopoly;
        monopoly = sortedList.get(firstPlayerIndex).redMonopoly;
        sortedList.get(firstPlayerIndex).redMonopoly = sortedList.get(secondPlayerIndex).redMonopoly;
        sortedList.get(secondPlayerIndex).redMonopoly = monopoly;
        monopoly = sortedList.get(firstPlayerIndex).greenMonopoly;
        sortedList.get(firstPlayerIndex).greenMonopoly = sortedList.get(secondPlayerIndex).greenMonopoly;
        sortedList.get(secondPlayerIndex).greenMonopoly = monopoly;
        monopoly = sortedList.get(firstPlayerIndex).yellowMonopoly;
        sortedList.get(firstPlayerIndex).yellowMonopoly = sortedList.get(secondPlayerIndex).yellowMonopoly;
        sortedList.get(secondPlayerIndex).yellowMonopoly = monopoly;
        ArrayList<BuyableProperties> owned = sortedList.get(firstPlayerIndex).getOwnedProperties();
        sortedList.get(firstPlayerIndex).setOwnedProperties(sortedList.get(secondPlayerIndex).getOwnedProperties());
        sortedList.get(secondPlayerIndex).setOwnedProperties(owned);
    }

    /**
     * This method checks if the players mentioned in {@link Commands#SWAP_WEALTH} command are not broke
     * and also whether they were in the game or not.
     * @param firstPlayerName
     * @param secondPlayerName
     */
    private void validatePlayers(String firstPlayerName, String secondPlayerName){
        boolean foundFirst = false,foundSecond = false;
        for (int i=0;i<allPlayers.length;i++){
            if (allPlayers[i].equals(firstPlayerName)){
                foundFirst = true;
                firstPlayerIndex = i;
            }
            if (allPlayers[i].equals(secondPlayerName)){
                foundSecond = true;
                secondPlayerIndex = i;
            }
        }
        boolean isFirstBroke = true,isSecondBroke = true;
        if (foundFirst && foundSecond){
            for (int i=0;i<sortedList.size();i++){
                if (sortedList.get(i).getName().equals(firstPlayerName)){
                    isFirstBroke = false;
                }
                if (sortedList.get(i).getName().equals(secondPlayerName)){
                    isSecondBroke = false;
                }
            }
        }
        if (!foundFirst && !foundSecond){
            throw new IllegalCommand("We didn't find "+firstPlayerName+" and "+secondPlayerName);
        }else if (!foundFirst){
            throw new IllegalCommand("We didn't find "+firstPlayerName);
        }else if (!foundSecond){
            throw new IllegalCommand("We didn't find "+secondPlayerName);
        }else if (isFirstBroke &&isSecondBroke){
            throw new IllegalCommand(firstPlayerName+" and "+secondPlayerName+" are broke!");
        }else if (isFirstBroke){
            throw new IllegalCommand(firstPlayerName+ " is broke!");
        }else if (isSecondBroke){
            throw new IllegalCommand(secondPlayerName+ " is broke!");
        }

    }

    /**
     * This method is invoked when conditions of ending the game are met.It shows a leaderboard informing the users
     * the required info.
     * <p>
     *     1: time limit is over.
     * </p>
     * <p>
     *     2: only one player has remained in the game.
     * </p>
     */
    public void endGame(){
        sort();
        System.out.println(sortedList.get(0).getName()+" won the game!");
        System.out.println("Leaderboard : ");
        for (int i=0;i<allPlayers.length;){
            for (int j=0;i<sortedList.size();j++,i++){
                System.out.println(i+1+" - "+sortedList.get(j).getName()+"  NetWorth : "+sortedList.get(j).getNetWorth());
            }
            for (int k=0;k<allPlayers.length;k++,i++){
                boolean foundPlayer = false;
                for (int p=0;p<sortedList.size();p++){
                    if (allPlayers[k].equals(sortedList.get(p).getName())){
                        foundPlayer = true;
                    }
                }
                if (!foundPlayer){
                    System.out.println(i+1+"- "+allPlayers[k]+"  Financial situation : Bankruptcy");
                }
            }
        }
    }

}
