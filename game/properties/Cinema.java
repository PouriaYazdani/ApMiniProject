package game.properties;

public class Cinema extends BuyableProperties{
    public final static int[] atFields = {4,8,15};
    private Colors color;

    public Cinema(Colors color){
        this.color = color;
    }

    public Colors getColor() {
        return color;
    }
}
