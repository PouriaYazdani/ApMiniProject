package game.properties;

/**
 * <p>
 *     there are 7 lucky-cards that may be revealed in {@link game.properties.QuestionMark} field:
 * </p>
 * <p>
 *    {@link game.properties.LuckyCards#CASH} : player receive a 200$ prize!
 * </p>
 * <p>
 *     {@link game.properties.LuckyCards#GOJAIL} : If this card is exposed, you will be imprisoned
 * </p>
 * <p>
 *     {@link game.properties.LuckyCards#TAX} : you must pay 10% of your cash to the bank
 * </p>
 * <p>
 *     {@link game.properties.LuckyCards#MOVE} : Go 3 steps forward
 * </p>
 * <p>
 *     {@link game.properties.LuckyCards#OUTJAIL} : one chance not to be imprisoned
 * </p>
 * <p>
 *    {@link game.properties.LuckyCards#NOTAX} : If you enter the tax area, you do not have to pay money to the bank once
 * </p>
 * <p>
 *     {@link game.properties.LuckyCards#GIFT} : you must pay 10$ to each player
 * </p>
 */
public enum LuckyCards {
    CASH,GOJAIL,TAX,MOVE,OUTJAIL,NOTAX,GIFT
}
