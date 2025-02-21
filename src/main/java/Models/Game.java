package Models;

import Controllers.GameController;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import strategy.ColumnWinningStrategy;
import strategy.DiagonalWinningStrategy;
import strategy.RowWinningStrategy;
import strategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Game {

    public Board board;
    public List<Player> playerList;
    public int currentPlayerIndex;
    public List<Cell> moves;
    public GameState gameState;
    List<WinningStrategy> winningStrategies;
    Player winner;

    public Game(int n, List<Player> playerList){
        this.board = new Board(n);
        this.playerList = playerList;
        this.currentPlayerIndex = 0;
        this.moves = new ArrayList<>();
        this.gameState = GameState.INIT;
        winningStrategies = new ArrayList<>(List.of(new RowWinningStrategy(),
                new ColumnWinningStrategy(),
                new DiagonalWinningStrategy()));
    }

    public void setWinner(){
        gameState = GameState.WON;
        this.winner = playerList.get(currentPlayerIndex);
    }

    public void setDraw(){
        gameState = GameState.DRAW;
    }

    /**
     * This method makes the next player decide a move and updates the board
     */
    public void makeMoveForCurrPlayer(){
        Player currPlayer = this.playerList.get(currentPlayerIndex);
        Cell cell = currPlayer.makeMove(currPlayer, this.board);

        // Update the board with that move and corresponding validation.
        // if it fails again try to make the move
        try{
            this.getBoard().updateBoard(cell, currPlayer);
            this.moves.add(cell);
        } catch (IllegalArgumentException e){
            System.out.println("Please choose a valid cell");
            makeMoveForCurrPlayer();
        }


    }

    public void postMoveWinnerCheck(){
        boolean isWin = this.getWinningStrategies().stream()
                .anyMatch(winningStrategy -> winningStrategy.isWinning(this));

        if(isWin){
            this.setWinner();
        } else {
            this.currentPlayerIndex++;
            // limit only to number of players
            this.currentPlayerIndex %= this.playerList.size();
        }
    }

}
