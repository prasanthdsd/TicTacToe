package src.strategies.winningstrategies;

import src.models.Board;
import src.models.Move;
import src.models.Player;
import src.models.Symbol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderOneDiagonalWinningStrategy implements WinningStrategy {

    private Map<Symbol,Integer> leftDiagonalMap;
    private Map<Symbol,Integer> rightDiagonalMap;

    public OrderOneDiagonalWinningStrategy(List<Player> players) {
        leftDiagonalMap = new HashMap<>();
        rightDiagonalMap = new HashMap<>();
        for (Player player : players) {
            leftDiagonalMap.put(player.getSymbol(), 0);
            rightDiagonalMap.put(player.getSymbol(), 0);
        }


    }

    @Override
    public boolean checkWinner(Board board, Move move) {

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        if(row == col)
        {
            leftDiagonalMap.put(symbol, leftDiagonalMap.get(symbol) + 1);
        }

        if(row + col== board.getSize()-1)
        {
            rightDiagonalMap.put(symbol, rightDiagonalMap.get(symbol) + 1);
        }

        if(row == col)
        {
            if(leftDiagonalMap.get(symbol) == board.getSize())
            {
                return true;
            }
        }
        if(row + col == board.getSize()-1)
        {
            return rightDiagonalMap.get(symbol) == board.getSize();
        }

        return false;
    }

    @Override
    public void HandleUndo(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        if(row == col){
            leftDiagonalMap.put(symbol, leftDiagonalMap.get(symbol) - 1);
        }

        if(row + col == leftDiagonalMap.size()-1)
        {
            rightDiagonalMap.put(symbol, rightDiagonalMap.get(symbol) - 1);
        }
    }
}
