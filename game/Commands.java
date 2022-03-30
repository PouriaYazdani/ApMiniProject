package game;

/**
 * <p>
 *    there are some orders that player can use:
 * </p>
 * <p>
 *     {@link #BUY} : to buy a EmptyField or Cinema
 * </p>
 * <p>
 *     {@link #BUILD} : to build a building or hotel on a emptyField
 * </p>
 * <p>
 *     {@link #SELL} : to sell a emptyField with the buildings on it
 * </p>
 * <p>
 *     {@link #FLY} : fly from an airport to another one
 * </p>
 * <p>
 *     {@link #FLY} : getting out of jail by paying a 50$ cost
 * </p>
 * <p>
 *     {@link #INVEST} : invest half of your cash at bank
 * </p>
 * <p>
 *     {@link #INDEX} : shows the index of field you are at
 * </p>
 * <p>
 *     {@link #TIME} : shows the remained time
 * </p>
 * <p>
 *     {@link #RANK} : shows the rank of player
 * </p>
 * <p>
 *     {@link #CREATE_GAME} : initializes the board and the players
 * </p>
 * <p>
 *     {@link  #START_GAME} : starts the game sets the timer on
 * </p>
 */
public enum Commands {
    BUY,BUILD,SELL,FLY,FREE,INVEST,INDEX,PROPERTY,TIME,RANK,CREATE_GAME,START_GAME
}
