package placements;

import board.TicTacToe;
import game.Cell;
import user.Player;
import utils.Utils;

import java.util.Optional;

public class CenterPlacement implements Placement{
    private static CenterPlacement centerPlacement;

    public synchronized static CenterPlacement get(){
        centerPlacement = (CenterPlacement) Utils.getIfNull(centerPlacement, CenterPlacement::new);
        return centerPlacement;
    }
    @Override
    public Optional<Cell> place(TicTacToe board, Player player) {
        Cell center = null;
        if(board.getCell(1, 1) != null){
            center = Cell.getCell(1, 1);
        }
        return Optional.ofNullable(center);
    }

    @Override
    public Placement next() {
        return CornerPlacement.get();
    }
}
