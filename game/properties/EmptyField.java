package game.properties;

public class EmptyField extends BuyableProperties{
    public final static int[] atFields = {2,7,9,12,14,18,19,23};

    public EmptyField(int atField){
        switch(atField){
            case 2:
            case 12:
                this.color = Colors.GREEN;
                break;
            case 7:
            case 19:
                this.color = Colors.YELLOW;
                break;
            case 9:
            case 18:
                this.color = Colors.RED;
                break;
            case 14:
            case 23:
                this.color = Colors.BLUE;
        }
        this.atField = atField;
    }
}
