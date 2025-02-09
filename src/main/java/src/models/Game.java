package src.models;

import src.enums.CellState;
import src.enums.GameStatus;
import src.enums.PlayerType;
import src.strategies.winningstrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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

    public static Builder getBuilder() {
        return new Builder();
    }

    private Game(int dimensions, List<Player> players, List<WinningStrategy> winningStartegies) {
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

    public void printBoard() {
        this.board.print();

    }

    private boolean validateMove(Cell cell){
        int row = cell.getRow();
        int col = cell.getCol();

        if(row < 0 ||col < 0 || row >= this.board.getSize() || col >= this.board.getSize() ){
            return false;
        }

        return board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY);
    }

    public void makeMove()
    {
        Player currentPlayer = players.get(currertMoveplayerIndex);
        System.out.println("Current player is " + currentPlayer.getName());

        Cell proposedCell = currentPlayer.makeMove(board);

        System.out.println("move made at row " + proposedCell.getRow() + " col " + proposedCell.getCol());
        if(!validateMove(proposedCell)){
            System.out.println("Invalid move.. Please Try again");
            return;
        }

        Cell cellInBoard = board.getBoard().get(proposedCell.getRow()).get(proposedCell.getCol());
        cellInBoard.setCellState(CellState.FILLED);
        cellInBoard.setPlayer(currentPlayer);

        Move move = new Move(currentPlayer, cellInBoard);
        moves.add(move);

        for(WinningStrategy winningStrategy : winningStartegies){
            if(winningStrategy.checkWinner(board, move)){
                gameStatus = GameStatus.FINISHED;
                winner = currentPlayer;
                return;
            }
        }

        if(moves.size() == board.getSize() * board.getSize())
        {
            gameStatus = GameStatus.DRAW;
            return;
        }

        currertMoveplayerIndex++;
        currertMoveplayerIndex %= players.size();

    }

    public void printResult(){
        if(this.gameStatus == GameStatus.FINISHED){
            System.out.println("Game is over!!!");
            System.out.println("Winner is :" + this.winner.getName());
        }
        else {
            System.out.println("Game is Draw!!!");
        }
    }

    public void undo()
    {
        if(moves.size() == 0){
            System.out.println("No Moves to undo!!!");
            return;
        }

        Move lastMove = moves.get(moves.size()-1);

        for(WinningStrategy winningStrategy : winningStartegies){
            winningStrategy.HandleUndo(lastMove);
        }

        Cell cellInBoard = lastMove.getCell();
        cellInBoard.setCellState(CellState.EMPTY);
        cellInBoard.setPlayer(null);

        moves.remove(lastMove);

        currertMoveplayerIndex--;
        currertMoveplayerIndex += players.size();
        currertMoveplayerIndex %= players.size();
    }

    public static class Builder {

        private int dimensions;
        private List<Player> players;
        private List<WinningStrategy> winningStartegies;

        private Builder()
        {}

        public Builder setDimensions(int dimensions) {
            this.dimensions = dimensions;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStartegies(List<WinningStrategy> winningStartegies) {
            this.winningStartegies = winningStartegies;
            return this;
        }

        private boolean validate() {
            if(this.players.size() < 2)
            {
                return false;
            }
            if(this.players.size() != this.dimensions-1)
            {
                return false;
            }
            int botCount = 0;
            for(Player player : this.players) {
                if(player.getPlayerType().equals(PlayerType.BOT))
                {
                    botCount++;
                }
            }
            if(botCount >=2)
            {
                return false;
            }
            Set<Character> existingSymbols = new HashSet<>();
            for(Player player : this.players) {
                if(existingSymbols.contains(player.getSymbol().getaChar())) {
                    return false;
                }
                existingSymbols.add(player.getSymbol().getaChar());
            }
            return true;
        }

        public Game build() {
            if(!validate()) {
                throw new RuntimeException("Invalid params for game!!!");
            }
            return new Game(dimensions, players, winningStartegies);
        }
    }
}
