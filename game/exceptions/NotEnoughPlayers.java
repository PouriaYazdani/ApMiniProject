package game.exceptions;

public class NotEnoughPlayers extends MonopolyException{
    public NotEnoughPlayers(String message) {
        super(message);
    }
}
