package game.properties;

/**
 * only parking field in the game.
 */
public class FreeParking extends Field{
    public final static int[] atFields = {1};

    public FreeParking(int atField) {
        this.atField = atField;
    }
    /**
     * just sends a message to inform the player that he/she can stay for free.
     */
    public void enterParking(){
        System.out.println("You have entered free parking! enjoy you're free stay for now.");
    }
}
