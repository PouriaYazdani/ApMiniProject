package game.exceptions;

public class NotEnoughCashToBuy extends MonopolyException{
    public NotEnoughCashToBuy(String message){
        super(message);
    }
}
