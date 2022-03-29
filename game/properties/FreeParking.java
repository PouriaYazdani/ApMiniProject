package game.properties;


public class FreeParking extends Field{
    public final static int[] atFields = {1};

    public FreeParking(int atField) {
        this.atField = atField;
    }

    public void enterParking(){
        System.out.println("You have entered free parking! enjoy you're free stay for now.");
    }
}
