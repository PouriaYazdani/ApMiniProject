package game.properties;

import java.util.ArrayList;
import java.util.Random;
import game.Player;
import game.Monopoly;
import game.exceptions.Bankruptcy;
import game.exceptions.SeriousDebt;

/**
 * In this class we have a static method named {@link QuestionMark#randomCard(Player)} that randomly
 * selects a lucky card and acts on it
 * also for the GIFT situation we have a method named {@link QuestionMark#giveGift(Player)} and this method will gave
 * each player 10$
 */
public class QuestionMark extends Field{
    public final static int[] atFields = {24};
    private final double GIFT_PRICE = 10.0;
    public QuestionMark(int atField) {
        this.atField = atField;
    }
        public void randomCard(Player  player){
            Random random = new Random();
            int randNumber = random.nextInt(7);
            LuckyCards[] cards = LuckyCards.values();
            switch (cards[randNumber]){
                case CASH : player.setCash(player.getCash()+200);
                    System.out.println("You won a 200$ prize!");
                break;
                case GOJAIL : Prison.imprisonment(player);
                    System.out.println("You weren't lucky! We will imprison you");
                break;//this method will be at Prison class
                case TAX : player.setCash(0.9* player.getCash());
                    System.out.println("10% tax received!");
                break;
                case MOVE : player.move(3);
                    System.out.println("You moved three fields forward!");
                break;
                case OUTJAIL : player.setNoJail(player.getNoJail()+1);
                    System.out.println("You won a freedom coupon!");
                break;
                case NOTAX : player.setNoTax(player.getNoTax()+1);
                    System.out.println("You don't have to pay tax next time!");
                break;
                case GIFT : giveGift(player);
                    System.out.println("Be generous and give a 10$ gift to each player!");
                break;//this method will be here in the class as a public method
            }
        }
    public  void giveGift(Player p){
        ArrayList<Player> players = Monopoly.getPlayers();
        if ((players.size()-1)* GIFT_PRICE > p.getCash()){
            if((players.size()-1)* GIFT_PRICE > p.getNetWorth()){
                throw new Bankruptcy("You do not have enough net worth to give gift to other players" + p.getName() + " the game " +
                        "is OVER for you");
            }
            p.setDebt((players.size()-1)* GIFT_PRICE);
            throw new SeriousDebt("You don't have enough cash to give the gifts, you should sell your properties to give the gifts!");
        }
        p.setCash(p.getCash() - ((players.size()-1)* GIFT_PRICE));
        for (int i=0;i<players.size();i++){
            if(players.get(i) != p){
                players.get(i).setCash(players.get(i).getCash()+ GIFT_PRICE);
            }else {
                continue;
            }
        }
    }
}
