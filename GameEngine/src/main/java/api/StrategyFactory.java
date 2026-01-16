package api;

import api.RuleEngine;
import board.TicTacToe;
import game.Cell;
import game.Move;
import placements.OffensivePlacement;
import placements.Placement;
import user.Player;

import java.util.Optional;

public class StrategyFactory {

    public Strategy getStrategy(TicTacToe board, Player player){
        Strategy strategy;
        int threshold = 3;
        if(countMoves(board) < threshold){
            strategy = new BasicStrategy();
        }else if(countMoves(board) < threshold + 1){
            strategy = new SmartStrategy();
        } else if (player.getTotalTImeTaken() > 100000) {
            strategy = new BasicStrategy();
        } else{
            strategy = new OptimalStrategy();
        }
        return strategy;
    }
    private int countMoves(TicTacToe board1){
        int count = 0;
        for(int i=0; i < 3; i++){
            for(int j=0; j < 3; j++){
                if (board1.getCell(i, j) != null){
                    count++;
                }
            }
        }
        return count;
    }


}
