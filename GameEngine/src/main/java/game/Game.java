package game;

import user.Player;

public class Game {
    private GameConfig gameConfig;
    private Board board;
    Player winner;
    private int lastMoveInMillis;

    private int totalTimePerPlayer;
    private int maxTimePerMove;

    public void move(Move move, int timestampInMillis){
        int timeTakenSinceLastMove = timestampInMillis - lastMoveInMillis;
        move.getPlayer().setTImeTaken(timeTakenSinceLastMove);
        if(gameConfig.timed){
            if(gameConfig.timePerMove != null){
                if(movedMadeInTime(timeTakenSinceLastMove)){
                    board.move(move);
                }else{
                    winner = move.getPlayer().flip();
                }
            }else {
                if (movedMadeInTime(move.getPlayer())) {
                    board.move(move);
                } else {
                    winner = move.getPlayer().flip();
                }
            }
        }else{
            board.move(move);
        }

    }
    private boolean movedMadeInTime(int timeTakenSinceLastMove){
        return timeTakenSinceLastMove < maxTimePerMove;
    }
    boolean movedMadeInTime(Player player){
        return player.getTotalTImeTaken() < totalTimePerPlayer;
    }
}
