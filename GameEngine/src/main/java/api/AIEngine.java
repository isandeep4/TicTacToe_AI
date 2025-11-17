package api;

import board.TicTacToe;
import game.Board;
import game.Cell;
import game.Move;
import org.junit.Rule;
import user.Player;

public class AIEngine {
    public Move suggestMove(Player computer, Board board) {
        if(board instanceof TicTacToe) {
            TicTacToe board1 = (TicTacToe) board;
            Move suggestions;
            if(isStarting(board1)){
                suggestions = getBasicMove(board1, computer);
            }else{
                suggestions = getSmartMove(board1, computer);
            }
            if(suggestions != null) return suggestions;
            throw new IllegalStateException();
        }else{
            throw new IllegalArgumentException();
        }
    }
    private boolean isStarting(TicTacToe board1){
        int count = 0;
        for(int i=0; i < 3; i++){
           for(int j=0; j < 3; j++){
               if (board1.getCell(i, j) != null){
                   count++;
               }
           }
        }
        return count < 3;
    }
    private Move getBasicMove(TicTacToe board1, Player computer){
        for (int i = 0; i < 3; i++) {
            for(int j=0; j < 3; j++){
                if(board1.getCell(i, j) == null){
                    return new Move(new Cell(i, j), computer);
                }
            }
        }
        return null;
    }
    private Move getSmartMove(TicTacToe board, Player player){
        RuleEngine ruleEngine = new RuleEngine();
        //victorious moves
        for (int i = 0; i < 3; i++) {
            for(int j=0; j < 3; j++){
                if(board.getCell(i, j) == null){
                    Move move = new Move(new Cell(i, j), player);
                    TicTacToe boardCopy = board.copy();
                    boardCopy.move(move);
                    if(ruleEngine.gameState(boardCopy).isOver()){
                        return move;
                    }
                }
            }
        }
        //defensive moves
        for (int i = 0; i < 3; i++) {
            for(int j=0; j < 3; j++){
                if(board.getCell(i, j) == null){
                    Move move = new Move(new Cell(i, j), player.flip());
                    TicTacToe boardCopy = board.copy();
                    boardCopy.move(move);
                    if(ruleEngine.gameState(boardCopy).isOver()){
                        return move;
                    }
                }
            }
        }
        return getBasicMove(board, player);
    }
}
