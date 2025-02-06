package src;

import src.controllers.GameController;
import src.enums.BotDifficultyLevel;
import src.enums.GameStatus;
import src.enums.PlayerType;
import src.models.Bot;
import src.models.Game;
import src.models.Player;
import src.models.Symbol;
import src.strategies.winningstrategies.OrderOneColumnWinningStrategy;
import src.strategies.winningstrategies.OrderOneDiagonalWinningStrategy;
import src.strategies.winningstrategies.OrderOneRowWinningStrategy;

import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //create the game
        GameController gameController = new GameController();
        Scanner scanner = new Scanner(System.in);

        Game game;

        try {
            game = gameController.createGame(3, List.of(
                    new Player("Prasanth", new Symbol('X'), PlayerType.HUMAN),
                    new Player("Manu", new Symbol('O'), PlayerType.HUMAN),
                    new Bot("siri", new Symbol('#'), BotDifficultyLevel.EASY)
                    ),
                    List.of(
                            new OrderOneRowWinningStrategy(),
                            new OrderOneColumnWinningStrategy(),
                            new OrderOneDiagonalWinningStrategy()
                    )
                  );
        } catch (Exception e) {
            System.out.println("Something Went Wrong!!!");
            return;
        }

//        //while status in progress  -
//        while (gameController.getGameStatus().equals(GameStatus.IN_PROGRESS)) {
//            //print board
//            gameController.printBoard();
//
//            //print if undo
//            System.out.println("Do you want to undo? (y/n)");
//            //if yes call undo
//            String input = scanner.next();
//            if (input.equalsIgnoreCase("y")) {
//                //gameController.undoProcess();
//            }
//            else {
//                //move to next player
//                //gameController.makeMove();
//            }
//        }
//
//        //print result of game
//        gameController.printResult(game);
       }

}