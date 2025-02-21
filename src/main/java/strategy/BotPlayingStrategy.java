package strategy;

import Models.Board;
import Models.Cell;
import Models.Player;

public interface BotPlayingStrategy {

    Cell suggestMove(Player player, Board board);
}
