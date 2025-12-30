package placements;

import api.RuleEngine;
import board.TicTacToe;
import game.Cell;
import game.Move;
import user.Player;
import utils.Utils;

import java.util.Optional;

public class DefensivePlacement implements Placement{
    private static DefensivePlacement defensivePlacement;

    public synchronized static DefensivePlacement get(){
        defensivePlacement = (DefensivePlacement) Utils.getIfNull(defensivePlacement, DefensivePlacement::new);
        return defensivePlacement;
    }

    @Override
    public Optional<Cell> place(TicTacToe board, Player player) {
        return Optional.ofNullable(getDefensiveMove(board, player));
    }

    @Override
    public Placement next() {
        return ForkPlacement.get();
    }
    private static Cell getDefensiveMove(TicTacToe board, Player player) {
        for (int i = 0; i < 3; i++) {
            for(int j=0; j < 3; j++){
                if(board.getCell(i, j) == null){
                    Move move = new Move(new Cell(i, j), player.flip());
                    TicTacToe boardCopy = board.move(move);
                    if(ruleEngine.getState(boardCopy).isOver()){
                        return new Cell(i, j);
                    }
                }
            }
        }
        return null;
    }
}
