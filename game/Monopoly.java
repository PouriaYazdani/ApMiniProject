package game;

import game.exceptions.*;
import game.properties.*;

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
    private int roundCounter;
    private static String[] playersName;
    private boolean isGameFinished = false;

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
        System.out.println("Enter player's usernames and then enter command 'start_game',\nif you want to play the game in" +
                " limited time enter command 'time' after you entered players usernames");
        Scanner scanner = new Scanner(System.in);
        int numOfPlayers = 0;
        outer :while(true) {
            try {
            stringCommand = scanner.nextLine();
            if(stringCommand.equalsIgnoreCase("start_game") || stringCommand.equalsIgnoreCase("time")) {
                if (numOfPlayers < MINIMUM_PLAYERS) {
                    throw new NotEnoughPlayers("Minimum number of players (" + MINIMUM_PLAYERS + ")has not been reached!");
                } else {
                    enumCommand = commandProcessor(stringCommand);
                    switch (enumCommand) {
                        case START_GAME:
                            break outer;
                        case TIME:
                            System.out.println("Enter duration of the game in minutes: ");
                            gameDuration = scanner.nextInt();
                            System.out.println("The timer has been set for "+ gameDuration + " minutes.\n " +
                                    "It gets activated once 'start_game' is invoked");
                            scanner.nextLine();
                            continue;
                    }
                }
            }
            if(gameDuration != 0){
                System.out.println("The list of players have been fixed,You can start the game.");
                if(!stringCommand.equalsIgnoreCase("start_game"))
                    throw new IllegalCommand("PLease enter a valid command.");
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
            }catch (IllegalCommand e){
                System.out.println(e.getMessage());
            }
        }
        boolean flag = true;
        System.out.println("Enter you're Dice number to arrange order of players in rolling the dice through the game.");
        int i = 0;
        while(flag){
            try{
                for (; i < players.size(); i++) {
                    System.out.print(players.get(i).getName() + ": ");
                    int diceNumber = scanner.nextInt();
                    checkDiceNumber(diceNumber);
                    players.get(i).setLastDiceNumber(diceNumber);
                }
                flag = false;
            }catch (InputMismatchException e){
                System.out.println("Please enter a valid input");
                scanner.next();
            }catch (InvalidDiceNumber e){
                System.out.println(e.getMessage());
            }
        }
        assignNames();
        sortPlayers();
        printPlayers();
        bankManager = BankManager.getInstance();
        board = Board.getInstance();
    }

    public void gamerunner() {
        Scanner scanner = new Scanner(System.in);
        if (gameDuration != 0){
            System.out.println("The timer has been activated");
            start = Instant.now();
            setTimer();
        }
        outer:
        while(!isGameFinished) {
            roundCounter++;
            int diceNumber = 0;
            boolean acceptDice = true;
            inner:
            for (int i = 0; i < players.size(); ) {
                try {
                    Integer possibleIndex = null;
                    System.out.println("round " + roundCounter + '\n' + players.get(i).getName() + "'s turn:");
                    if (players.get(i).isInJail()) {
                        jailManager(players.get(i));
                        if (i == players.size()) {
                            break inner;
                        }
                        i++;
                        continue;}
//                    }else if(seriousDebt){//define a boolean here at gamerunner
//                        calls a new method like jailManager that forces the player to sell properties to be able to pay the rent
//                        the player will come out of this method when the debt is paid and joins normal cycle of the gamerunner,,
//                    }
                    else {
                        if (acceptDice) {
                            diceNumber = scanner.nextInt();
                            checkDiceNumber(diceNumber);
                            if (sendToJail(players.get(i), diceNumber)) {
                                if (i == players.size()) {
                                    break inner;
                                }
                                i++;
                                continue;
                            }
                            players.get(i).move(diceNumber);
                            players.get(i).state();
                            scanner.nextLine();//to consume the '\n' so we can enter two part command like sell and fly
                        }
                        acceptDice = false;
                        stringCommand = scanner.nextLine();
                        if (stringCommand.contains("fly") || stringCommand.contains("sell"))
                            possibleIndex = collapseCommandToInteger();
                        else if(stringCommand.contains("swap_wealth")){
                            throw new IllegalCommand("swap_wealth is not allowed here,The bank manager can use it at the"
                                    + " end of each round");
                        }
                        enumCommand = commandProcessor(stringCommand);//can throw exception
                        switch (enumCommand) {
                            case TIME:
                                if (gameDuration == 0) {
                                    throw new IllegalCommand("This game has no time limit,TIME command is not valid!");
                                }
                                System.out.println(time() + " minutes to the end of the game");
                                break;
                            case PASS:
                                i++;
                                if (i == players.size()) {
                                    if (diceNumber != 6) {
                                        break inner;
                                    }
                                    i--;//player has another turn for rolling 6
                                } else {
                                    if (diceNumber == 6) {
                                        i--;
                                    }
                                }
                                acceptDice = true;
                        }
                        players.get(i).order(enumCommand, possibleIndex);
                        if(isGameFinished){
                            break outer;
                        }
                    }
                } catch (InputMismatchException e) {//if anything but number entered for dice
                    System.out.println("Please enter enter a number");
                    scanner.nextLine();
                } catch (IllegalArgumentException e) {
                    System.out.println("There is no such command,please enter a valid command");
                } catch (SeriousDebt e) {
                    System.out.println(e.getMessage());
                    debtManager(i);
                    System.out.println("You paid your debt!\nNow you can continue playing!");
                    i++;
                }catch (Bankruptcy e){
                    System.out.println(e.getMessage());
                    removePlayer(i);
                    //calls the method that removes the player
                    if(players.size() == 1){//
                        break outer;
                    }
                    i++;//next players turn...
                    if(players.size() == i){
                        break inner;
                    }
                }
                catch (MonopolyException e) {
                    System.out.println(e.getMessage());
                }
            }
            boolean flag = true;
            System.out.println("The end of this round has been reached,\nThe bank manager can swap the properties of two" +
                    " players via swap_wealth command followed by the two player's names\nIf not just enter pass");
            while (flag) {
                String player1 = "";
                String player2 = "";
                stringCommand = scanner.nextLine();
                try {
                    if (stringCommand.contains("swap_wealth")) {
                        String[] arguments = collapseCommandToString();
                        player1 = arguments[0];
                        player2 = arguments[1];
                    }
                    enumCommand = commandProcessor(stringCommand);
                    switch (enumCommand) {
                        case PASS:
                            flag = false;
                            break;
                        case SWAP_WEALTH:
                          bankManager.swapWealth(player1,player2);//throws IllegalCommand
                            flag = false;
                            break;
                        default:
                            throw new IllegalCommand(stringCommand + " is not valid here");
                    }
                }catch (IllegalArgumentException e) {
                    System.out.println("There is no such command,please enter a valid command");
                }catch (IllegalCommand e){
                    System.out.println(e.getMessage());
                }catch (StringIndexOutOfBoundsException e){
                    System.out.println("Please enter the command thoroughly");
                }
            }
            System.out.println("==========================================================");
        }
        System.out.println("bankManager.endgame() is called here");
        bankManager.endGame();
    }
    private void debtManager(int index){
        try {
            while (true){
                System.out.println("Your debt is : " + players.get(index).getDebt()
                        +" but your cash is : "+players.get(index).getCash());
                System.out.println("You should sell your properties to pay your debt!");
                System.out.println("Enter the desired field number to sell: ");
                Scanner scanner = new Scanner(System.in);
                int fieldNum = scanner.nextInt();
                if (fieldNum<1 || fieldNum>24){
                    throw new IllegalCommand("Please enter a number between 1 and 24!");
                }else {
                    players.get(index).order(Commands.SELL,fieldNum);
                }
                if (players.get(index).getCash() >= players.get(index).getDebt()){
                    Board board = Board.getInstance();
                    Field field = board.fields[players.get(index).getPosition()-1];
                    if (field instanceof Cinema){
                        Cinema cinema = (Cinema) field;
                        cinema.chargeRent(players.get(index));
                        break;
                    }else if (field instanceof EmptyField){
                        EmptyField emptyField = (EmptyField) field;
                        emptyField.chargeRent(players.get(index));
                        break;
                    }else if (field instanceof Prison){
                        Prison prison = (Prison) field;
                        players.get(index).setCash(players.get(index).getCash() - prison.getJAIL_COST());
                        break;
                    }else if (field instanceof QuestionMark){
                        QuestionMark questionMark = (QuestionMark) field;
                        questionMark.giveGift(players.get(index));
                        break;
                    }else if (field instanceof Road){
                        Road road = (Road) field;
                        road.payToll(players.get(index));
                        break;
                    }
                }else if (players.get(index).getDebt()>players.get(index).getCash()){
                    System.out.println("Sell more!, You still don't have enough cash to pay off your debt!");
                    continue;
                }
            }
        }catch (InputMismatchException e){
            System.out.println("Please enter a valid number!(should be integer)");
        }catch (IllegalCommand e){
            System.out.println(e.getMessage());
        }
    }
    private void removePlayer(int index){
        System.out.println(players.get(index).getName()+" has been removed from game!");
        players.remove(players.get(index));
    }
    private  String[] collapseCommandToString(){
        String temp = stringCommand;
        int whiteSpace = stringCommand.indexOf(" ");
        stringCommand = stringCommand.substring(0,whiteSpace);//now is swap_wealth
        temp = temp.substring(whiteSpace + 1);
        whiteSpace = temp.indexOf(" ");
        String player1 = temp.substring(0,whiteSpace);
        String player2 = temp.substring(whiteSpace + 1);
        String[] names = {player1,player2};
        return names;
    }

    private Integer collapseCommandToInteger(){
        Integer at;
        String temp = stringCommand;
        int idx = 0;
        for (int i = 0; i < stringCommand.length(); i++) {
            if(stringCommand.charAt(i) == ' '){
                idx = i;
                break;
            }
        }
        at = Integer.parseInt(temp.substring(idx + 1));//throws IllegalArgumentException if there is no index
        if(at < 1 || at > 24){
            throw new IllegalCommand("You have entered Illegal command,field number is between 1 and 24 !");
        }
        stringCommand = stringCommand.substring(0,idx);
        return at;
    }

    private void jailManager(Player player){
        Prison prison = (Prison)board.fields[12];
        boolean isNumber = true;
        int diceNumber = 0;
        System.out.println("You are in jail,you can try your luck and roll a dice or pay 50$ to free yourself");
        Scanner scanner = new Scanner(System.in);
        stringCommand = scanner.nextLine();
        try{
            diceNumber = Integer.parseInt(stringCommand);
        }catch(NumberFormatException e){//the player has entered a command
            isNumber = false;
        }
        if(isNumber){
            if(diceNumber > 6 || diceNumber < 1){
                throw new InvalidDiceNumber("Please enter a number between 1 and 6");
            }
            else {
                player.setLastDiceNumber(diceNumber);
                prison.luckyDice(player);
            }
        }else{
            enumCommand = commandProcessor(stringCommand);//throws exception if the player entered giberish
            // -IllegalArgumentexception- it should say invalid command
            if(enumCommand == Commands.FREE){
                prison.free(player);
            }else{
                throw new IllegalCommand("You have entered illegal command ! ,please try again.");
            }
        }

    }

    private boolean sendToJail(Player player,int currentDiceNumber){
        if(player.getLastDiceNumber() == currentDiceNumber && currentDiceNumber == 6){
            Prison.imprisonment(player);
            return true;
        }
        return false;
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
                isGameFinished = true;
                System.out.println("The time limit has been reached\nAt the end of current player's turn we'll know " +
                        "the winner...");
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

    private void checkDiceNumber(int diceNumber){
        if(diceNumber > 6 || diceNumber < 1 )
            throw new InvalidDiceNumber("Please enter a number between 1 and 6");
    }

    private void assignNames(){
        playersName = new String[players.size()];
        for (int i = 0; i < playersName.length; i++) {
            playersName[i] = players.get(i).getName();
        }
    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public static String[] getPlayersName(){
        return playersName;
    }
}
