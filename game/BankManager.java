package game;

import java.util.*;

public class BankManager implements Owner,Comparator<Player>{
    private static BankManager bankManager;
    private ArrayList sortedList = Monopoly.getPlayers();
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

    public void swapWealth(){

    }
    private void validatePlayers(){

    }

    @Override
    public int compare(Player o1, Player o2) {
        return Double.compare(o1.getNetWorth(),o2.getNetWorth());
    }
}
