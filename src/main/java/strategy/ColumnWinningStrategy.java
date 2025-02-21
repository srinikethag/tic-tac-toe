package strategy;

import Models.Cell;
import Models.CellState;
import Models.Game;
import Models.Player;

public class ColumnWinningStrategy extends WinningStrategy{

    @Override
    public boolean isWinning(Game game) {
        // O(N)

        // get the current player
        Player currPlayer = game.playerList.get(game.currentPlayerIndex);

        // get last move
        Cell cell = game.moves.getLast();

        int col = cell.getCol();

        for(int i=0; i<game.board.getN(); i++){
            Cell curr = game.board.getCells().get(i).get(col);
            if(curr.getCellState().equals(CellState.FREE)
                    || !curr.getPlayer().equals((currPlayer))){
                return false;
            }
        }
        return true;
    }
}
