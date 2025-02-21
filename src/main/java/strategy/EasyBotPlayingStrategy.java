package strategy;

import Models.*;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {
    @Override
    public Cell suggestMove(Player player, Board board) {
        int N = board.getN();
        for(int i=0; i<N; i++) {
            for (int j = 0; j < N; j++) {
                Cell cell = board.getCells().get(i).get(j);
                if (cell.getCellState() == CellState.FREE) {
                    return cell;
                }
            }
        }
        throw new IllegalStateException("All cells are full");
    }
}
