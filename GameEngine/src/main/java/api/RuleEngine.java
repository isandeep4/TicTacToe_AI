package api;

import board.TicTacToe;
import game.Board;
import game.GameResult;

public class RuleEngine {
    public GameResult gameState (Board board) {
        if(board instanceof TicTacToe) {
            TicTacToe board1 = (TicTacToe) board;
            String firstCharacter = "-";
            boolean isRowCrossed = true;
            for (int i = 0; i < 3; i++) {
                firstCharacter = board1.getCell(i, 0);
                isRowCrossed = firstCharacter != null;
                if(firstCharacter != null){
                    for (int j = 1; j < 3; j++) {
                        if (!firstCharacter.equals(board1.getCell(i,j))) {
                            isRowCrossed = false;
                            break;
                        }
                        ;
                    }
                }

                if(isRowCrossed){
                    break;
                }
            }
            if(isRowCrossed){
                return new GameResult(true, firstCharacter);
            }

            boolean isColCrossed = true;
            for (int i = 0; i < 3; i++) {
                firstCharacter = board1.getCell(0, i);
                isColCrossed = firstCharacter != null;
                if(firstCharacter != null){
                    for (int j = 1; j < 3; j++) {
                        if (!firstCharacter.equals(board1.getCell(j, i))) {
                            isColCrossed = false;
                            break;
                        }
                        ;
                    }
                }
                if(isColCrossed){
                    break;
                }

            }
            if(isColCrossed){
                return new GameResult(true, firstCharacter);
            }
            firstCharacter = board1.getCell(0, 0);
            boolean isDiagCrossed = firstCharacter != null;
            for (int i = 0; i < 3; i++) {
                if(firstCharacter != null) {
                    if (!firstCharacter.equals(board1.getCell(i, i))) {
                        isDiagCrossed = false;
                        break;
                    }
                }
            }
            if(isDiagCrossed){
                return new GameResult(true, firstCharacter);
            }
            firstCharacter = board1.getCell(0, 2);
            boolean isRevDiagCrossed = firstCharacter != null;
            for (int i = 0; i < 3; i++) {
                if(firstCharacter != null) {
                    if (!firstCharacter.equals(board1.getCell(i, 2 - i))) {
                        isRevDiagCrossed = false;
                        break;
                    }
                }
            }
            if(isRevDiagCrossed){
                return new GameResult(true, firstCharacter);
            }

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
}
