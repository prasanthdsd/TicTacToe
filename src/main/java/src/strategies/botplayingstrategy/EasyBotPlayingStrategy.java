package src.strategies.botplayingstrategy;

import src.enums.CellState;
import src.models.Board;
import src.models.Cell;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {

    @Override
    public Cell makeMove(Board board) {
        for(List<Cell> row : board.getBoard()) {
            for(Cell cell : row) {
                if (cell.getCellState().equals(CellState.EMPTY)){
                    return cell;
                }
            }
        }
        return null; // never touch this point
    }
}
