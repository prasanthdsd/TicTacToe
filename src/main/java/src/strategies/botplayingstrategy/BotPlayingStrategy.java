package src.strategies.botplayingstrategy;

import src.models.Board;
import src.models.Cell;

public interface BotPlayingStrategy {

    Cell makeMove(Board board);
}
