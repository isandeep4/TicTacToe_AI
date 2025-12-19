package api;

import board.TicTacToe;
import game.Board;
import game.GameResult;

import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {
    public GameResult gameState (Board board) {
        if(board instanceof TicTacToe) {
            TicTacToe board1 = (TicTacToe) board;
            String firstCharacter = "-";

            GameResult rowWin = findStreak((i, j) -> board1.getCell(i, j));
            if (rowWin != null) return rowWin;

            GameResult colWin = findStreak((i, j) -> board1.getCell(j, i));
            if (colWin != null) return colWin;

            GameResult diagWin = findDiagStreak((i) -> board1.getCell(i, i));
            if(diagWin != null) return diagWin;

            GameResult revDiagWin = findDiagStreak((i) -> board1.getCell(i, 2-i));
            if(revDiagWin != null) return revDiagWin;

            int countOfFilledCells = 0;
            for (int i = 0; i < 3; i++) {
                for(int j=0; j < 3; j++){
                    if(board1.getCell(j, i) != null){
                        countOfFilledCells++;
                    }
                }
            }
            if(countOfFilledCells == 9){
                return new GameResult(true, "-");
            }else{
                return new GameResult(false, "-");
            }
        } else {
            return new GameResult(false, "-");
        }


    }

    private GameResult findStreak(BiFunction<Integer, Integer, String> next) {
        for (int i = 0; i < 3; i++) {
            boolean possibleStreak = true;
            for (int j = 1; j < 3; j++) {
                if (next.apply(i, j) == null || !next.apply(i,0).equals(next.apply(i,j))) {
                    possibleStreak = false;
                    break;
                }
                ;
            }
            if(possibleStreak){
                return new GameResult(true, next.apply(i,0));
            }
        }
        return null;
    }
    private GameResult findDiagStreak(Function<Integer, String> diag){
        boolean isStreak = true;
        for (int i = 0; i < 3; i++) {
            if (diag.apply(i) != null && diag.apply(0).equals(diag.apply(i))) {
                isStreak = false;
                break;
            }
        }
        if(isStreak){
            return new GameResult(true, diag.apply(0));
        }
        return null;
    }
}

