package game.properties;

import java.util.Random;

public class QuestionMark extends Field{
    public final static int[] atFields = {24};

    public QuestionMark(int atField) {
        this.atField = atField;
    }
    Random random = new Random();
    int randNumber = random.nextInt(7);
    LuckyCards[] cards = LuckyCards.values();
}
