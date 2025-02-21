package strategy;

import Models.Board;
import Models.Cell;
import Models.CellState;
import Models.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

public class MediumBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Cell suggestMove(Player player, Board board) {
        int N = board.getN();
        List<Cell> availableCells = new ArrayList<>();
        for(int i=0; i<N; i++) {
            for (int j = 0; j < N; j++) {
                Cell cell = board.getCells().get(i).get(j);
                if (cell.getCellState() == CellState.FREE) {
                    availableCells.add(cell);
                }
            }
        }

        return availableCells.get(Math.abs(RandomGenerator.getDefault().nextInt()) % availableCells.size());
    }
}
