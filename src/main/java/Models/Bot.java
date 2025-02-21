package Models;

import strategy.BotPlayingStrategy;
import strategy.BotPlayingStrategyFactory;

public class Bot extends Player{

    BotDifficultyLevel botDifficultyLevel;
    BotPlayingStrategy botPlayingStrategy;

    public Bot(String name, Character symbol, int id, BotDifficultyLevel botDifficultyLevel) {
        super(name, symbol, id);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy(botDifficultyLevel);
    }


    @Override
    public Cell makeMove(Player player, Board board) {
        return this.botPlayingStrategy.suggestMove(player, board);
    }
}
