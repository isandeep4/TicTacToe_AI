package placements;

import board.TicTacToe;
import game.Cell;
import user.Player;
import utils.Utils;

import java.util.Optional;

public class CornerPlacement implements Placement{
    private static CornerPlacement cornerPlacement;

    public synchronized static Placement get(){
        cornerPlacement = (CornerPlacement) Utils.getIfNull(cornerPlacement, CornerPlacement::new);
        return cornerPlacement;
    }
    @Override
    public Optional<Cell> place(TicTacToe board, Player player) {
        Cell cornerPlace = null;
        final int[][] corners = new int[][]{{0, 0}, {0, 2}, {2, 0}, {2, 2}};
        for (int i=0; i<4; i++){
            if(board.getCell(corners[i][0], corners[i][1]) == null){
                cornerPlace = new Cell(corners[i][0], corners[i][1]);
            }
        }
        return Optional.ofNullable(cornerPlace);
    }

    @Override
    public Placement next() {
        return null;
    }
}
