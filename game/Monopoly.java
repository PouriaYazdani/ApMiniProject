package game;

import game.exceptions.IllegalCommand;
import game.properties.Colors;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;

public class Monopoly {
    private Board board;
    private BankManager bankManager;
    private static ArrayList <Player> players;
    private Timer timer;
    private int gameDuration;//in minutes
    private String stringCommand;
    private Commands enumCommand;

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
    }

    public void gameInit(){

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
