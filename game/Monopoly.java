package game;

import game.exceptions.IllegalCommand;
import game.exceptions.InvalidDiceNumber;
import game.exceptions.NotEnoughPlayers;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

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
    private Instant start;

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
        board = Board.getInstance();
        bankManager = BankManager.getInstance();
        System.out.println("Enter player's usernames and then enter command 'start_game',\nif you want to play the game in" +
                " limited time enter command 'time' after you entered players usernames");
        Scanner scanner = new Scanner(System.in);
        int numOfPlayers = 0;
        while(true) {
            try {
            stringCommand = scanner.nextLine();
            if(stringCommand.equalsIgnoreCase("start_game") || stringCommand.equalsIgnoreCase("time")) {
                if (numOfPlayers < MINIMUM_PLAYERS) {
                    throw new NotEnoughPlayers("Minimum number of players (" + MINIMUM_PLAYERS + ")has not been reached!");
                } else {
                    enumCommand = commandProcessor(stringCommand);
                    switch (enumCommand) {
                        case START_GAME:
                            return;
                        case TIME:
                            System.out.println("Enter duration of the game in minutes: ");
                            gameDuration = scanner.nextInt();
                            System.out.println("The timer has been set for "+ gameDuration + " minutes.\n " +
                                    "It gets activated once START_GAME is invoked");
                            continue;
                    }
                }
            }
            if(gameDuration != 0){
                System.out.println("The list of players have been fixed,You can start the game.");
                continue;
            }
            if(numOfPlayers < MAXIMUM_PLAYERS && numOfPlayers < MAXIMUM_PLAYERS) {
                players.add(new Player(stringCommand, STARTING_CASH));
                    numOfPlayers++;
                for (int i = 0; i < players.size(); i++) {
                    System.out.println("player " + (i+1)+ ":" +players.get(i).getName());
                }
            }
             if(players.size() == MAXIMUM_PLAYERS){
                System.out.println("Maximum number of players has been reached \nIf you want to play the game in a" +
                        " limited time ,Enter 'time' \n if not , enter 'start_game'.");
                continue;
            }
            }catch (InputMismatchException e){
                System.out.println("PLease enter a valid input to set the timer \n TIME command has been terminated. ");
                scanner.nextLine();//to read he invalid input and skip it
            }catch (NotEnoughPlayers e){
                System.out.println(e.getMessage());
            }

        }
    }

    public void gamerunner(){
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter you're Dice number to arrange order of players in rolling the dice through the game.");
        int i = 0;
        while(flag){
            try{
                for (; i < players.size(); i++) {
                System.out.print(players.get(i).getName() + ": ");
                players.get(i).setLastDiceNumber(scanner.nextInt());
                if(players.get(i).getLastDiceNumber() > 6 || players.get(i).getLastDiceNumber() < 1 )
                    throw new InvalidDiceNumber("Please enter a number between 1 and 6");
                }
                flag = false;
            }catch (InputMismatchException e){
                System.out.println("Please enter a valid input");
                scanner.next();
            }catch (InvalidDiceNumber e){
                System.out.println(e.getMessage());
            }
        }
        sortPlayers();
        printPlayers();
        System.out.println("The timer has been activated");
        start = Instant.now();
        if(gameDuration != 0)
            setTimer();
        while(true){
            for (int j = 0; j < players.size(); j++) {
                System.out.println(players.get(j).getName() + "'s turn:");
                stringCommand = scanner.next();
                enumCommand = commandProcessor(stringCommand);
                switch (enumCommand){
                    case TIME:
                        System.out.println(time() + " minutes to the end of the game");
                }
            }
        }

    }

    private Long time(){
        Instant now = Instant.now();
        Duration timeElapsed = Duration.between(start,now);
        return gameDuration - timeElapsed.toMinutes();
    }

    private void printPlayers(){
        System.out.println("the order is as following:");
        for (int i = 0; i < players.size(); i++) {
            System.out.println((i + 1) + "." + players.get(i).getName());
            players.get(i).setLastDiceNumber(0);//resetting dice number to prevent going to jail at the first move
        }
    }

    private void setTimer(){
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
//                endGame();
                timer.cancel();
            }
        }, gameDuration * 1000 * 60);
    }

    /**
     * sorts the {@link #players} with the help of swap method.
     */
    private void sortPlayers(){
        for (int i = 0; i < players.size() - 1; i++) {
            for (int j = 0; j < players.size() - i - 1; j++) {
                if(players.get(j).getLastDiceNumber() < players.get(j + 1).getLastDiceNumber())
                    swap(j,j + 1);
            }
        }
    }

    /**
     * simple swap method for arraylist to simplifies the process sorting the players
     * @param a
     * @param b
     */
    private void swap(int a ,int b){
        Player temp = players.get(a);
        players.set(a,players.get(b));
        players.set(b,temp);
    }

    private Commands commandProcessor(String command){
        return  Commands.valueOf(command.toUpperCase());
    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }
}
