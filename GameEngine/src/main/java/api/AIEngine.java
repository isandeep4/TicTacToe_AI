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
    StrategyFactory strategyFactory = new StrategyFactory();
    public Cell suggestMove(Player player, Board board) {
        if(board instanceof TicTacToe) {
            TicTacToe b = (TicTacToe) board;
            Strategy strategy = strategyFactory.getStrategy(b, player);
            Cell suggestions = strategy.getOptimalMove(b, player);

            throw new IllegalStateException();
        }else{
            throw new IllegalArgumentException();
        }
    }







    
}
