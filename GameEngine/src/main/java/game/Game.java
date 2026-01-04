package game;

import boards.Board;
import user.Player;

public class Game {
    private GameConfig gameConfig;
    private Board board;
    Player winner;
    private Integer lastMoveInMillis;

    private Integer totalTimePerPlayer;
    private Integer maxTimePerMove;

    public Game(GameConfig gameConfig, Board board, Player winner, Integer lastMoveInMillis, Integer totalTimePerPlayer, Integer maxTimePerMove){
        this.gameConfig = gameConfig;
        this.board = board;
        this.winner = winner;
        this.lastMoveInMillis = lastMoveInMillis;
        this.maxTimePerMove = maxTimePerMove;
        this.totalTimePerPlayer = totalTimePerPlayer;
    }

    public void move(Move move, int timestampInMillis){
        int timeTakenSinceLastMove = timestampInMillis - lastMoveInMillis;
        int endTime = gameConfig.timePerMove != null ? maxTimePerMove : totalTimePerPlayer;
        int timeTakenInMillis = gameConfig.timePerMove != null ? timeTakenSinceLastMove : move.getPlayer().getTotalTImeTaken();
        move.getPlayer().setTImeTaken(timeTakenSinceLastMove);
        if(gameConfig.timed){
            if(timeTakenInMillis < endTime){
                board.move(move);
            }else{
                winner = move.getPlayer().flip();
            }
        }else{
            board.move(move);
        }

    }
}
