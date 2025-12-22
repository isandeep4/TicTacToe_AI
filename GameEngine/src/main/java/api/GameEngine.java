package api;
import game.Board;
import game.Cell;
import game.Move;
import board.TicTacToe;
import user.Player;

public class GameEngine {

    public Board start(String type){
        if(type.equals("TicTacToe")){
            return new TicTacToe();
        }else {
            throw new IllegalArgumentException();
        }
    }

    public void move(Board board, Move move){
        if(board instanceof TicTacToe) {
            board.move(move);
//            TicTacToe board1 = (TicTacToe) board;
//            board1.setCell(player.symbol(), move.getCell());
        }else {
            throw new IllegalArgumentException();
        }
    }
}


