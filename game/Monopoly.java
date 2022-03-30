package game;

import game.exceptions.IllegalCommand;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;

public class Monopoly {
    private Board board;
    private BankManager bankManager;
    private static ArrayList <Player> players = new ArrayList<Player>();
    private Timer timer;
    private int gameDuration;//in minutes
    private String stringCommand;
    private Commands enumCommand;
    private final int MAXIMUM_PLAYERS = 4;
    private final int MINIMUM_PLAYERS = 2;
    private final double STARTING_CASH = 1500.0;

    public void waitingMenu(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            try {
                stringCommand = scanner.next();
                enumCommand = commandProcessor(stringCommand);
                if(enumCommand == Commands.CREATE_GAME){
                    break;
                }else{
                    throw new IllegalCommand("You have entered Illegal command ! Please try again.");
                }
            }catch (IllegalArgumentException e){
                System.out.println("You have entered invalid command ! Please try again.");
            }catch (IllegalCommand e){
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }

    public void gameInit(){
        board = Board.getInstance();
        bankManager = BankManager.getInstance();
        System.out.println("Enter player's usernames and then enter command 'start_game',if you want to play the game in" +
                " limited time enter command 'time' after you entered players usernames");
        Scanner scanner = new Scanner(System.in);
        int numOfPlayers = 0;
        while(true) {
//            System.out.println("player " + (numOfPlayers+1));
            stringCommand = scanner.next();
            if(stringCommand.equalsIgnoreCase("start_game") || stringCommand.equalsIgnoreCase("time")) {
                if (numOfPlayers < MINIMUM_PLAYERS) {
                    System.out.println("Minimum number of players (" + MINIMUM_PLAYERS + ")has not been reached!");
                    continue;
                } else {
                    enumCommand = commandProcessor(stringCommand);
                    switch (enumCommand) {
                        case START_GAME:
                            System.out.println("reached start_game");
                            return;
                        case TIME:
                            System.out.println("Enter duration of the game in minutes: ");
                            gameDuration = scanner.nextInt();
                            return;
                    }
                }
            }
            if(numOfPlayers < MAXIMUM_PLAYERS && numOfPlayers < MAXIMUM_PLAYERS) {
                players.add(new Player(stringCommand, STARTING_CASH));
//                if(numOfPlayers < MAXIMUM_PLAYERS - 1)
                    numOfPlayers++;
                for (int i = 0; i < players.size(); i++) {
                    System.out.println("player " + (i+1)+ ":" +players.get(i).getName());
                }
            }
             if(players.size() == MAXIMUM_PLAYERS){
                System.out.println("Maximum number of players has been reached \nIf you want to play the game in a" +
                        " limited time ,Enter 'time' \n if not , enter start_game.");
                continue;
            }
        }
    }

    public void gamerunner(){

    }

    private Commands commandProcessor(String command){
        return  Commands.valueOf(command.toUpperCase());
    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }
}
