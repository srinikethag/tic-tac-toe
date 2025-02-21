package strategy;

import Models.Game;

public abstract class WinningStrategy {

    public abstract boolean isWinning(Game game);
}
