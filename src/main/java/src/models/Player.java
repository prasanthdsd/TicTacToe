package src.models;

import src.enums.PlayerType;

import java.util.Scanner;

public class Player {

    private String name;
    private Symbol symbol;
    private PlayerType playerType;

    private Scanner scanner;

    public Player(String name, Symbol symbol, PlayerType playerType) {
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
        this.scanner = new Scanner(System.in);
    }

    public Cell makeMove(Board board) {
        System.out.println("Please enter row number (starting from 0): ");
        int row = scanner.nextInt();
        System.out.println("Please enter column number (starting from 0): ");
        int column = scanner.nextInt();

        return new Cell(row, column);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}
