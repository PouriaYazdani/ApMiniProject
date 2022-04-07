package game;

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
        swapPrimitive();
        swapProp();
    }
    private void swapPrimitive(){

    }
    private void swapProp(){

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
