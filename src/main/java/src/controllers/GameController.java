package src.controllers;

import src.enums.GameStatus;
import src.models.Board;
import src.models.Game;
import src.models.Move;
import src.models.Player;
import src.strategies.winningstrategies.WinningStrategy;

import java.util.List;

public class GameController {

    public Game createGame(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) {

        return Game.getBuilder()
                .setDimensions(dimension)
                .setPlayers(players)
                .setWinningStartegies(winningStrategies)
                .build();
    }
    public void printBoard(Game game){
        game.printBoard();
    }

    public Board undoProcess(Game game)
    {
        return null;
    }

    public  void makeMove(Game game)
    {

    }

    public GameStatus getGameStatus(Game game)
    {
        return game.getGameStatus();
    }

    public  void printResult(Game game)
    {
        game.printResult();
    }


}
