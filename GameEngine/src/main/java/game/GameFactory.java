package game;

import board.TicTacToe;

public class GameFactory {

    public Game createGame(Integer maxTimePerMove, Integer maxTotalTimePerPlayer){
        return new Game(
                new GameConfig(maxTimePerMove, maxTotalTimePerPlayer != null),
                new TicTacToe(),
                null,
                0,
                maxTotalTimePerPlayer,
                maxTimePerMove
        );
    }
    public Game createGame(Integer maxTotalTimePerPlayer){
        return new Game(
                new GameConfig(null, true),
                new TicTacToe(),
                null,
                0,
                maxTotalTimePerPlayer,
                null
        );
    }
    public Game createGame(){
        return new Game(
                new GameConfig(null, false),
                new TicTacToe(),
                null,
                0,
                null,
                null
        );
    }
}
