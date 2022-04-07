package game;

import game.properties.*;

import java.util.*;

public class BankManager implements Owner,Comparator<Player>{
    private static BankManager bankManager;
    private ArrayList<Player> sortedList = Monopoly.getPlayers();
    private BankManager(){

    }

    public static BankManager getInstance() {
        if(bankManager == null)
            bankManager = new BankManager();
        return bankManager;
    }

    public ArrayList getSortedList() {
        Collections.sort(sortedList);
        return sortedList;
    }
    Integer firstPlayerIndex, secondPlayerIndex;
    public void swapWealth(){
        validatePlayers();
        swapProp();
    }
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

    private void validatePlayers(){
        try {
            boolean first = true;
            while (first){
                Scanner scanner = new Scanner(System.in);
                System.out.println("Please enter first player's name : ");
                String firstPlayer = scanner.next();
                for (int i=0;i<sortedList.size();i++){
                    if (sortedList.get(i).getName().equals(firstPlayer)){
                        firstPlayerIndex = i;
                    }
                }
                if (firstPlayerIndex == null){
                    throw new RuntimeException("Player not found!");
                }else {
                    first = false;
                    scanner.close();
                }
            }
            boolean second = true;
            while (second){
                System.out.println("Please enter second player's name : ");
                Scanner scanner = new Scanner(System.in);
                String secondPlayer = scanner.next();
                for (int i=0;i<sortedList.size();i++){
                    if (sortedList.get(i).getName().equals(secondPlayer)){
                        secondPlayerIndex = i;
                    }
                }
                if (secondPlayerIndex == null){
                    throw new RuntimeException("Player not found!");
                }
            }
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public int compare(Player o1, Player o2) {
        return Double.compare(o1.getNetWorth(),o2.getNetWorth());
    }
}
