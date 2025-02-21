package strategy;

import Models.Cell;
import Models.CellState;
import Models.Game;
import Models.Player;

public class DiagonalWinningStrategy extends WinningStrategy {
    @Override
    public boolean isWinning(Game game) {
        return this.leftTopToRightBottomDiagonalCheck(game) || this.rightTopToLeftBottomDiagonalCheck(game);
    }

    public boolean leftTopToRightBottomDiagonalCheck(Game game){
        // O(N)

        // get the current player
        Player currPlayer = game.playerList.get(game.currentPlayerIndex);

        for(int i=0; i<game.board.getN(); i++){
            Cell curr = game.board.getCells().get(i).get(i);
            if(curr.getCellState().equals(CellState.FREE)
                    || !curr.getPlayer().equals((currPlayer))){
                return false;
            }
        }
        return true;
    }


    public boolean rightTopToLeftBottomDiagonalCheck(Game game){
        // O(N)

        // get the current player
        Player currPlayer = game.playerList.get(game.currentPlayerIndex);

        // get last move
        Cell cell = game.moves.getLast();

        int N = game.board.getN();

        for(int i=0; i<N; i++){
            Cell curr = game.board.getCells().get(i).get(N - i - 1);
            if(curr.getCellState().equals(CellState.FREE)
                    || !curr.getPlayer().equals((currPlayer))){
                return false;
            }
        }
        return true;
    }
}
