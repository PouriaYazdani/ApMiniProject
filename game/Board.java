package game;

import game.properties.*;

/**
 *  is a singelton class which we get an instance of it once when create_game is run.
 *  it is repeatedly used in {@link Monopoly#gamerunner()} method via composition relation.
 */
public class Board {
    private static Board board;
    /**
     * is defined package access because is used repeatedly in {@link Monopoly#gamerunner()} method,all the game's
     * different fields are kept in it and each command/action for different fields are called via the elements
     * of this array.
     */
    Field[] fields;
    private final int NUM_OF_FIELDS = 24;
    /**
     * {@link #fields} is initialized here with the help of each field's static array arField[].
     * <p>
     * {@link #isIn(int, int[])} method is called from here to simplifies the process.
     * </p>
     */
    private Board(){
        fields = new Field[24];
        for (int i = 0; i < NUM_OF_FIELDS; i++) {
            if(isIn(i, Airport.atFields)){
                fields[i] = new Airport(i+1);
            }
            else if(isIn(i, Bank.atFields)){
                fields[i] = new Bank(i+1);
            }
            else if(isIn(i, FreeParking.atFields)){
                fields[i] = new FreeParking(i+1);
            }
            else if(isIn(i, Prison.atFields)){
                fields[i] = new Prison(i+1);
            }
            else if(isIn(i,Prize.atFields)){
                fields[i] = new Prize(i+1);
            }
            else if(isIn(i,QuestionMark.atFields)){
                fields[i] = new QuestionMark(i+1);
            }
            else if(isIn(i,Road.atFields)){
                fields[i] = new Road(i+1);
            }
            else if(isIn(i,Tax.atFields)){
                fields[i] = new Tax(i+1);
            }
            else if(isIn(i,Cinema.atFields)){
                fields[i] = new Cinema(i+1);
            }
            else {//emptyField
                fields[i] = new EmptyField(i+1);
            }
        }
    }

    public static Board getInstance() {
        if(board == null)
            board = new Board();
        return board;
    }
    /**
     * simple method to check if argument x is in each field's atField[].
     * @param x
     * @param arr
     * @return whether x in a specified array or not.
     */
    private boolean isIn(int x,int[] arr){
        x++;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == x)
                return true;
        }
        return false;
    }
}