package game;

/**
 * <p>
 *    there are some orders that player can use:
 * </p>
 * <p>
 *     {@link game.Commands#BUY} : to buy a EmptyField or Cinema
 * </p>
 * <p>
 *     {@link game.Commands#BUILD} : to build a building or hotel on a emptyField
 * </p>
 * <p>
 *     {@link game.Commands#SELL} : to sell a emptyField with the buildings on it
 * </p>
 * <p>
 *     {@link game.Commands#FLY} : fly from an airport to another one
 * </p>
 * <p>
 *     {@link game.Commands#FLY} : getting out of jail by paying a 50$ cost
 * </p>
 * <p>
 *     {@link game.Commands#INVEST} : invest half of your cash at bank
 * </p>
 * <p>
 *     {@link game.Commands#INDEX} : shows the index of field you are at
 * </p>
 * <p>
 *     {@link game.Commands#TIME} : shows the remained time
 * </p>
 * <p>
 *     {@link game.Commands#RANK} : shows the rank of player
 * </p>
 */
public enum Commands {
    BUY,BUILD,SELL,FLY,FREE,INVEST,INDEX,PROPERTY,TIME,RANK
}
