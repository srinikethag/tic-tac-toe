package strategy;

import Models.BotDifficultyLevel;

public class BotPlayingStrategyFactory {

    public static  BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel botDifficultyLevel){
        return switch (botDifficultyLevel){
            case EASY -> new EasyBotPlayingStrategy();
            case MEDIUM -> new MediumBotPlayingStrategy();
            default -> new MediumBotPlayingStrategy();
        };
    }
}
