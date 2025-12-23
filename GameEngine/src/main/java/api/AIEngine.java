package api;

import board.TicTacToe;
import boards.Board;
import game.*;
import org.junit.Rule;
import placements.OffensivePlacement;
import placements.Placement;
import user.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

public class AIEngine {
    public Cell suggestMove(Player computer, Board board) {
        if(board instanceof TicTacToe) {
            TicTacToe board1 = (TicTacToe) board;
            Cell suggestions;
            if(isStarting(board1)){
                suggestions = getBasicMove(board1);
            }else{
                suggestions = getOptimalMove(board1, computer);
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
    private Cell getBasicMove(TicTacToe board1){
        for (int i = 0; i < 3; i++) {
            for(int j=0; j < 3; j++){
                if(board1.getCell(i, j) == null){
                    return new Cell(i, j);
                }
            }
        }
        return null;
    }
    private Cell getSmartMove(TicTacToe board, Player player){
        RuleEngine ruleEngine = new RuleEngine();
        //victorious moves
//        Cell winningMove = getOffensiveMove(board, player, ruleEngine);
//        if (winningMove != null) return winningMove;
//        //defensive moves
//        Cell defensiveMove = getDefensiveMove(board, player, ruleEngine);
//        if (defensiveMove != null) return defensiveMove;
        return getBasicMove(board);
    }

    private Cell getOptimalMove(TicTacToe board, Player player){
        Placement placement = OffensivePlacement.get();
        while (placement.next() != null){
            Optional<Cell> place = placement.place(board, player);
            if (place.isPresent()){
                return place.get();
            }
            placement.next();
        }
        return null;
    }



    
}
