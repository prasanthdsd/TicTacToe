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

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //create the game
        GameController gameController = new GameController();
        Scanner scanner = new Scanner(System.in);

        System.out.printf("%100s\n", "TicTacToe Game");
        System.out.println("Enter the number of players: ");
        int noOfPlayers = scanner.nextInt();

        List<Player> players = new ArrayList<>(noOfPlayers);

        for (int i = 0; i < noOfPlayers; i++) {

            System.out.println("Player " +  (i + 1) + " details " );
            System.out.println("Enter name of player " + (i + 1) + " : ");
            String playerName = scanner.next();
            System.out.println("Enter Symbol of player " + (i + 1) + " : ");
            char aChar = scanner.next().charAt(0);
            Symbol symbol = new Symbol(aChar);
            Player player = new Player(playerName, symbol, PlayerType.HUMAN);
            players.add(player);
        }
        Game game;

        int dimensions = players.size()+1;

        try {
            game = gameController.createGame(dimensions, players,
                    List.of(
                            new OrderOneRowWinningStrategy(dimensions, players),
                            new OrderOneColumnWinningStrategy(dimensions, players),
                            new OrderOneDiagonalWinningStrategy(players)
                    )
                  );
        } catch (Exception e) {
            System.out.println("Something Went Wrong!!!");
            return;
        }

        System.out.println("Welcome to : ");
        for (Player player : game.getPlayers()) {
            System.out.println(player.getName());
        }
        System.out.println("Game Starts !!!");
        //while status in progress  -
        while (gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)) {
            System.out.println("This is how the board looks like ");
            //print board
            gameController.printBoard(game);

            //print if undo
            System.out.println("Do you want to undo? (y/n)");
            //if yes call undo
            String input = scanner.next();
            if (input.equalsIgnoreCase("y")) {
                //undo process
                gameController.undoProcess(game);
            } else {
                //move to next player
                gameController.makeMove(game);
            }
        }

        //print result of game
        gameController.printResult(game);
       }

}