package src.strategies.winningstrategies;

import src.models.Board;
import src.models.Move;

public class OrderOneDiagonalWinningStrategy implements WinningStrategy {

    @Override
    public boolean checkWinner(Board board, Move move) {
        return false;
    }
}