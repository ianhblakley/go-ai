package ianhblakley.goai.bots;

import ianhblakley.goai.Constants;
import ianhblakley.goai.framework.*;
import ianhblakley.goai.neuralnetworkconnection.NeuralMoveClient;
import ianhblakley.goai.neuralnetworkconnection.ValueClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Bot that uses {@link ValueClient} to find the best move given the current game state
 *
 * Created by ian on 11/13/16.
 */
class NeuralNetBot extends AbstractBot {

    private final NeuralMoveClient client;

    NeuralNetBot(PositionState color) {
        super(color);
        client = NeuralMoveClient.getInstance();
    }

    @Override
    public Move getPlay(Board board, int turnNumber) {
        if (checkCannotPlay()) return new Move(color);
        Set<Position> positionSet = board.getAvailableSpaces();

        if (positionSet.size() == 0) return new Move(color);
        List<Position> legalMoves = new ArrayList<>();
        for (Position p : positionSet) {
            Move m = new Move(p, color);
            if (StateChecker.isLegalMove(m, board)) {
                legalMoves.add(p);
            }
        }
        if (legalMoves.size() < Constants.ALLOW_PASS_COUNT) {
            legalMoves.add(null);
        }
        Position bestMove = client.getBestPosition(color, board, legalMoves);
        if (bestMove == null) {
            return new Move(color);
        }
        if (resign(board, bestMove, turnNumber)) {
            return new Move(color);
        }
        playStone();
        return new Move(bestMove, color);
    }

    @Override
    public String toString() {
        return "Neural Net Bot";
    }

}
