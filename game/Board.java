package game;

public class Board {
    private static Board board;

    private Board(){}

    public static Board getBoard() {
        if(board == null)
            board = new Board();
        return board;
    }
}
