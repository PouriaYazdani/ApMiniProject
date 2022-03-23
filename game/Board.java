package game;

import game.properties.Field;

public class Board {
    private static Board board;

    private Board(){}

    public static Board getBoard() {
        if(board == null)
            board = new Board();
        return board;
    }

    private Field[] fields;
    private final int NUM_OF_FIELDS = 24;

}
