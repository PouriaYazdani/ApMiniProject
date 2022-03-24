package game;

import game.properties.*;

public class Board {
    private static Board board;
    Field[] fields;
    private final int NUM_OF_FIELDS = 24;

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

    public static Board getBoard() {
        if(board == null)
            board = new Board();
        return board;
    }

    private boolean isIn(int x,int[] arr){
        x++;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == x)
                return true;
        }
        return false;
    }
}