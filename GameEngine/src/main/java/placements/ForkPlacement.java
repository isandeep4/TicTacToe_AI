package placements;

import board.TicTacToe;
import game.Cell;
import game.GameInfo;
import user.Player;
import utils.Utils;

import java.util.Optional;

public class ForkPlacement implements Placement{

    private static ForkPlacement forkPlacement;

    public synchronized static Placement get(){
        forkPlacement = (ForkPlacement) Utils.getIfNull(forkPlacement, DefensivePlacement::new);
        return forkPlacement;
    }
    @Override
    public Optional<Cell> place(TicTacToe board, Player player) {
        Cell best = null;
        GameInfo gameInfo = ruleEngine.getInfo(board);
        if(gameInfo.hasFork){
            best = gameInfo.getForkCell();
        }
        return Optional.ofNullable(best);
    }

    @Override
    public Placement next() {
        return CenterPlacement.get();
    }
}
