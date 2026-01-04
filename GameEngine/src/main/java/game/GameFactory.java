package game;

import board.TicTacToe;

public class GameFactory {

    public Game createGame(Integer maxTimePerMove, Integer maxTotalTimeperPlayer){
        return new Game(
                new GameConfig(maxTimePerMove, maxTotalTimeperPlayer != null),
                new TicTacToe(),
                null,
                0,
                maxTimePerMove,
                maxTotalTimeperPlayer
        );
    }
}
