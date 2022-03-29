package game.exceptions;

public class NotEnoughCashToPayToll extends MonopolyException {
    NotEnoughCashToPayToll(String message) {
        super(message);
    }
}
