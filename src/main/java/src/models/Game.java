package src.models;

import src.enums.GameStatus;
import src.strategies.winningstrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;


public class Game {

    //Attributes
    //All the attributes are initialized in the constructor.

    private List<Move> moves;
    private List<Player> players;
    private Board board;
    private int currertMoveplayerIndex;
    private List<WinningStrategy> winningStartegies;
    private GameStatus gameStatus;
    private Player winner;

    public Game(int dimensions, List<Player> players, List<WinningStrategy> winningStartegies) {
        this.moves = new ArrayList<>();
        this.board = new Board(dimensions);
        this.players = players;
        this.currertMoveplayerIndex = 0;
        this.winningStartegies = winningStartegies;
        this.gameStatus = GameStatus.IN_PROGRESS;
    }

    //Getters and Setters for all the attributes
    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getCurrertMoveplayerIndex() {
        return currertMoveplayerIndex;
    }

    public void setCurrertMoveplayerIndex(int currertMoveplayerIndex) {
        this.currertMoveplayerIndex = currertMoveplayerIndex;
    }

    public List<WinningStrategy> getWinningStartegies() {
        return winningStartegies;
    }

    public void setWinningStartegies(List<WinningStrategy> winningStartegies) {
        this.winningStartegies = winningStartegies;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
}
