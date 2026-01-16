package api;

import board.TicTacToe;
import boards.Board;
import boards.CellBoard;
import game.*;
import placements.DefensivePlacement;
import placements.OffensivePlacement;
import user.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RuleEngine {
    Map<String, RuleSet> ruleMap = new HashMap<>();

    public RuleEngine(){
        ruleMap.put(TicTacToe.class.getName(), TicTacToe.getRules());
    }
    public GameInfo getInfo(CellBoard board){
        if(board instanceof TicTacToe ticTacToe){
            GameState gameState = getState(ticTacToe);
            for(TicTacToe.Symbol symbol : TicTacToe.Symbol.values()){
                Player player = new Player(symbol.marker());
                boolean canStillWin = false;
                for(int i=0; i<3; i++){
                    for(int j=0; j<3; j++){
                        if (ticTacToe.getSymbol(i, j) == null){
                            TicTacToe b = ticTacToe.move(new Move(Cell.getCell(i, j), player));
                            DefensivePlacement defense = DefensivePlacement.get();
                            Optional<Cell> defenseCell = defense.place(b, player.flip());
                            if(defenseCell.isPresent()){
                                b = b.move(new Move(defenseCell.get(), player.flip()));
                                OffensivePlacement offense = OffensivePlacement.get();
                                Optional<Cell> offenseCell = offense.place(b, player);
                                if(offenseCell.isPresent()){
                                    return new GameInfoBuilder().isOver(gameState.isOver())
                                            .winner(gameState.getWinner())
                                            .hasFork(true)
                                            .forkCell(Cell.getCell(i, j))
                                            .player(player.flip())
                                            .build();
                                }
                                }
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

