package game.properties;

public class Cinema extends BuyableProperties{
    public final static int[] atFields = {4,8,15,22};

    public Cinema(int atField){
        switch (atField){
            case 4:
                this.atField = atField;
                color = Colors.RED;
                break;
            case 8:
                this.atField = atField;
                color = Colors.BLUE;
                break;
            case 15:
                this.atField = atField;
                color = Colors.GREEN;
                break;
            case 22:
                this.atField = atField;
                color = Colors.YELLOW;
        }
    }

    public Colors getColor() {
        return color;
    }
}
