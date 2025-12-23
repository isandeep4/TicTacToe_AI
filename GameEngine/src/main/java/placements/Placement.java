package placements;

import api.RuleEngine;
import board.TicTacToe;
import game.Cell;
import user.Player;

import java.util.Optional;

public interface Placement {
    RuleEngine ruleEngine = new RuleEngine();
    Optional<Cell> place(TicTacToe board, Player player);

    Placement next();
}
