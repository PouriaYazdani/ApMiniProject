package game;

public class BankManager implements Owner{
    private static BankManager bankManager;

    private BankManager(){

    }

    public static BankManager getInstance() {
        if(bankManager == null)
            bankManager = new BankManager();
        return bankManager;
    }
}
