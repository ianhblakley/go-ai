package ianhblakley.goai.bots;

import ianhblakley.goai.framework.Board;
import ianhblakley.goai.framework.Move;

/**
 * Interface to encompass a bot
 * Uses a single method getPlay to get the move of the bot
 * <p>
 * Created by ian on 10/12/16.
 */
public interface Bot {

    /**
     * Returns the move the bot deams is best
     *
     * @param board      current board
     * @param turnNumber current turnNumber
     * @return best move
     */
    Move getPlay(Board board, int turnNumber);

}
