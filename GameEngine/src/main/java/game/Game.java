package game;

import api.RuleEngine;
import boards.Board;
import user.Player;

public class Game {
    private GameConfig gameConfig;
    private Board board;
    Player winner;
    private Integer lastMoveInMillis;

    private Integer totalTimePerPlayer;
    private Integer maxTimePerMove;
    private RuleEngine ruleEngine = new RuleEngine();

    public Game(GameConfig gameConfig, Board board, Player winner, Integer lastMoveInMillis, Integer totalTimePerPlayer, Integer maxTimePerMove){
        this.gameConfig = gameConfig;
        this.board = board;
        this.winner = winner;
        this.lastMoveInMillis = lastMoveInMillis;
        this.maxTimePerMove = maxTimePerMove;
        this.totalTimePerPlayer = totalTimePerPlayer;
    }

    public void move(Move move, int timestampInMillis){
        if(winner == null){
            int timeTakenSinceLastMove = timestampInMillis - lastMoveInMillis;
            move.getPlayer().setTImeTaken(timeTakenSinceLastMove);
            if(gameConfig.timed){
                moveForTimedGame(move, timeTakenSinceLastMove);
            }else{
                board = board.move(move);
            }
            if (winner == null && ruleEngine.getState(board).isOver()) {
                winner = move.getPlayer();
            }
        }
    }
    private void moveForTimedGame(Move move, int timeTakenSinceLastMove){
        if (move.getPlayer().getTotalTImeTaken() < totalTimePerPlayer
        && (gameConfig.timePerMove == null || timeTakenSinceLastMove < maxTimePerMove)) {
            board = board.move(move);
        } else {
            winner = move.getPlayer().flip();
        }
    }

    public Player getWinner() {
        return winner;
    }
}
