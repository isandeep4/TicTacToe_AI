package game;

import user.Player;

public class GameInfo {
    private final boolean isOver;
    private final String winner;
    private final boolean hasFork;
    private final Player player;
    private final int numberOfSteps;
    private Cell forkCell;

    public GameInfo(boolean isOver, String winner, boolean hasFork, Player player, int numberOfSteps, Cell forkCell) {
        this.isOver = isOver;
        this.winner = winner;
        this.hasFork = hasFork;
        this.player = player;
        this.numberOfSteps = numberOfSteps;
        this.forkCell = forkCell;
    }
}
