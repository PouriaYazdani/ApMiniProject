package game.properties;

import java.util.Random;
import game.Player;
import game.exceptions.NotEnoughCashToGift;

/**
 * In this class we have a static method named {@link QuestionMark#randomCard(Player)} that randomly
 * selects a lucky card and acts on it
 * also for the GIFT situation we have a method named {@link QuestionMark#giveGift(Player)} and this method will gave
 * each player 10$
 */
public class QuestionMark extends Field{
    public final static int[] atFields = {24};

    public QuestionMark(int atField) {
        this.atField = atField;
    }
        public void randomCard(Player  player){
            Random random = new Random();
            int randNumber = random.nextInt(7);
            LuckyCards[] cards = LuckyCards.values();
            switch (cards[randNumber]){
                case CASH -> player.setCash(player.getCash()+200);break;
                case GOJAIL -> Prison.imprisonment(player);break;//this method will be at Prison class
                case TAX -> player.setCash(0.9* player.getCash());break;
                case MOVE -> player.setPosition(player.getPosition()+3);break;
                case OUTJAIL -> player.setNoJail(player.getNoJail()+1);break;
                case NOTAX -> player.setNoTax(player.getNoTax()+1);break;
                case GIFT -> giveGift(player);break;//this method will be here in the class as a public static
            }
        }
    public  void giveGift(Player p){
        for (int i=0;i<players.size();i++){
            if(players.get(i) != p){
                players.get(i).setCash(players.get(i).getCash()+10);
            }else {
                continue;
            }
        }
        if ((players.size()-1)*10 > p.getCash()){
            throw new NotEnoughCashToGift("You don't have enough cash to give the gifts, you should sell your properties to give the gifts!");
        }
        p.setCash(p.getCash() - ((players.size()-1)*10));
    }
}
