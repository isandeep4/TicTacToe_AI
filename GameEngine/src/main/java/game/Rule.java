package game;

import boards.CellBoard;

import java.util.function.Function;

public class Rule {
    public Function<CellBoard, GameState> condition;
    public Rule(Function<CellBoard, GameState> condition){
        this.condition = condition;
    }
}
