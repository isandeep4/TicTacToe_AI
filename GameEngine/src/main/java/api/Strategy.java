package api;

import board.TicTacToe;
import game.Cell;
import user.Player;

public abstract class Strategy {
    abstract Cell getOptimalMove(TicTacToe board, Player player);
}
