package Models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Board {

    int N;
    List<List<Cell>> cells = new ArrayList<>();

    public Board(int n){
        this.N = n;

        for(int i=0; i<N; i++){
            List<Cell> row = new ArrayList<>();
            for(int j=0; j< N; j++){
                row.add(new Cell(i, j));
            }

            assert false;
            this.cells.add(row);
        }
    }




}
