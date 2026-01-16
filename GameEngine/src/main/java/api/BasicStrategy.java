package api;

import board.TicTacToe;
import game.Cell;
import user.Player;

public class BasicStrategy extends Strategy{
    @Override
    Cell getOptimalMove(TicTacToe board, Player player) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getCell(i, j) == null) {
                    return Cell.getCell(i, j);
                }
            }
        }
        return null;
    }
}
