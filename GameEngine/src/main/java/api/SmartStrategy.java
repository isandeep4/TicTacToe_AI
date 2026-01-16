package api;

import board.TicTacToe;
import game.Cell;
import game.Move;
import user.Player;

public class SmartStrategy extends Strategy{
    RuleEngine ruleEngine = new RuleEngine();
    @Override
    Cell getOptimalMove(TicTacToe board, Player player) {
        //victorious moves
        Cell winningMove = offense(board, player);
        if (winningMove != null) return winningMove;
        //defensive moves
        Cell defensiveMove = defence(board, player);
        if (defensiveMove != null) return defensiveMove;
        return new BasicStrategy().getOptimalMove(board, player);
    }

    private Cell defence(TicTacToe board, Player player) {
        for (int i = 0; i < 3; i++) {
            for(int j=0; j < 3; j++){
                if(board.getCell(i, j) == null){
                    Move move = new Move(Cell.getCell(i, j), player.flip());
                    TicTacToe boardCopy = board.move(move);
                    if(ruleEngine.getState(boardCopy).isOver()){
                        return move.getCell();
                    }
                }
            }
        }
        return null;
    }

    private Cell offense(TicTacToe board, Player player) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) {
                    Move move = new Move(Cell.getCell(i, j), player);
                    TicTacToe boardCopy = board.move(move);
                    if (ruleEngine.getState(boardCopy).isOver()) {
                        return move.getCell();
                    }
                }
            }
        }
        return null;
    }
}
