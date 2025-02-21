package Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cell {
    int row;
    int col;
    Player player;
    CellState cellState;

    Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.cellState = CellState.FREE;
    }

    public void updateCell(Player player){
        this.cellState = CellState.OCCUPIED;
        this.player = player;
    }
}
