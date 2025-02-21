package Controllers;

import Models.Board;
import Models.Cell;
import Models.CellState;
import Models.Player;

public class BoardController {

    Board board;
    int N;

    public BoardController(Board board) {
        this.board = board;
        this.N = board.getN();
    }

    public void displayBoard(){
        for(int i=0; i<N; i++) {
            for (int j = 0; j < N; j++) {
                Cell cell = board.getCells().get(i).get(j);
                if(cell.getCellState() == CellState.OCCUPIED){
                    System.out.printf(" | %c", cell.getPlayer().getSymbol());
                } else {
                    System.out.print(" |  ");
                }
            }
            System.out.print(" | \n");

        }
    }

    public void updateBoard(Cell cell, Player player){
        int row = cell.getRow();
        int col = cell.getCol();

        if(row < N && col < N && row >=0 && col >=0 && board.getCells().get(row).get(col).getCellState() == CellState.FREE){
            board.getCells().get(row).get(col).updateCell(player);
        } else {
            throw new IllegalArgumentException("The move is invalid.");
        }
    }

    public boolean isFull() {
        for(int i=0; i<N; i++) {
            for (int j = 0; j < N; j++) {
                Cell cell = board.getCells().get(i).get(j);
                if (cell.getCellState() == CellState.FREE) {
                    return false;
                }
            }
        }

        return true;
    }
}
