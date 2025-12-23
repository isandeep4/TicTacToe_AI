package api;

import board.TicTacToe;
import boards.Board;
import boards.CellBoard;
import game.*;
import user.Player;

import java.util.HashMap;
import java.util.Map;

public class RuleEngine {
    Map<String, RuleSet> ruleMap = new HashMap<>();

    public RuleEngine(){
        ruleMap.put(TicTacToe.class.getName(), TicTacToe.getRules());
    }
    public GameInfo getInfo(CellBoard cellBoard){
        if(cellBoard instanceof TicTacToe){
            GameState gameState = getState(cellBoard);
            final String[] players = new String[]{"X", "O"};
            for(int i=0; i<2; i++){
                Player player = new Player(players[i]);
                boolean canStillWin = false;
                Cell forkCell = null;
                for(int j=0; j<3; j++){
                    for(int k=0; k<3; k++){
                        CellBoard b = (CellBoard) cellBoard.copy();
                        b.move(new Move(new Cell(j, k), player));
                        for(int l=0; l<3; l++){
                            for(int m=0; m<3; m++){
                                CellBoard b1 = (CellBoard) b.copy();
                                b1.move(new Move(new Cell(l, m), player.flip()));
                                if(getState(b1).getWinner().equals(player.flip().symbol())){
                                    forkCell = new Cell(l, m);
                                    canStillWin = true;
                                    break;
                                }
                            }
                            if (canStillWin){
                                break;
                            }
                        }
                        if(canStillWin){
                            return new GameInfoBuilder().isOver(gameState.isOver())
                                    .winner(gameState.getWinner())
                                    .hasFork(true)
                                    .forkCell(forkCell)
                                    .player(player.flip())
                                    .build();
                        }
                    }
                }

            }
            return new GameInfoBuilder()
                    .isOver(gameState.isOver())
                    .winner(gameState.getWinner())
                    .build();

        }else{
            throw new IllegalArgumentException();
        }
    }
    public GameState getState (Board board) {
        if(board instanceof TicTacToe board1) {
            for (Rule r:ruleMap.get(TicTacToe.class.getName())){
                GameState gameState = r.condition.apply(board1);
                if(gameState.isOver()){
                    return gameState;
                }
            }
            return new GameState(false, "-");
        } else {
            throw new IllegalArgumentException();
        }
    }
}

