package src.strategies.winningstrategies;

import src.models.Board;
import src.models.Move;
import src.models.Player;

public interface WinningStrategy {
    boolean checkWinner(Board board, Move move);
    void HandleUndo(Move move);
}
