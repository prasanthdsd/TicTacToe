package src.strategies.botplayingstrategy;

import src.enums.BotDifficultyLevel;

public class BotPlayingStrategyFactory {

    public  static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel difficultyLevel)
    {
        return new EasyBotPlayingStrategy();
//        return switch (difficultyLevel){
//            case EASY -> new EasyBotPlayingStrategy();
//            case MEDIUM -> new MediumBotPlayingStrategy();
//            case HARD -> new HardBotPlayingStrategy();
//        };
    }
}
