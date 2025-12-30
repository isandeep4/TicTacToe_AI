package board;

import boards.Board;
import boards.CellBoard;
import game.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TicTacToe implements CellBoard {
    String[][] cells = new String[3][3];
    History history = new History();

    public static RuleSet getRules() {
        RuleSet rules = new RuleSet();
        rules.add(new Rule(board -> outerTraversal((i, j) -> board.getSymbol(i, j))));
        rules.add(new Rule(board -> outerTraversal((i, j) -> board.getSymbol(j, i))));
        rules.add(new Rule(board -> traverse(i -> board.getSymbol(i, i))));
        rules.add(new Rule(board -> traverse(i -> board.getSymbol(i, 2 - i))));
        rules.add(new Rule(board -> {
            int countOfFilledCells = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.getSymbol(j, i) != null) {
                        countOfFilledCells++;
                    }
                }
            }
            if (countOfFilledCells == 9) {
                return new GameState(true, "-");
            }
            return new GameState(false, "-");
        }));
        return rules;
    }
    private static GameState outerTraversal(BiFunction<Integer, Integer, String> next) {
        GameState result = new GameState(false, "-");
        for (int i = 0; i < 3; i++) {
            final int ii = i;
            GameState traversal = traverse(j -> next.apply(ii, j));
            if(traversal.isOver()){
                result = traversal;
                break;
            }
        }
        return result;
    }
    private static GameState traverse(Function<Integer, String> traversal){
        GameState result = new GameState(false, "-");
        boolean possibleStreak = true;
        for (int j = 0; j < 3; j++) {
            if (traversal.apply(j) == null || !traversal.apply(0).equals(traversal.apply(j))) {
                possibleStreak = false;
                break;
            }
        }
        if(possibleStreak){
            result = new GameState(true, traversal.apply(0));
        }
        return result;
    }

    public String getCell(int row, int col){
        return cells[row][col];
    }
    public void setCell(String symbol, Cell cell){
        if(cells[cell.getRow()][cell.getCol()] == null){
            cells[cell.getRow()][cell.getCol()] = symbol;
        }else{
            System.out.println(this);
            throw new IllegalArgumentException(cell.getRow() + " " + cell.getCol());
        }

    }

    @Override
    public String toString(){
        String result = "";
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(cells[i][j] == null){
                    result += "-";
                }else{
                    result += cells[i][j];
                }
            }
            result+="\n";
        }
        return result;
    }

    @Override
    public TicTacToe move(Move move) {
        history.add(new Representation(this));
        TicTacToe board = copy();
        board.setCell(move.getPlayer().symbol(), move.getCell());
        return board;
    }

    @Override
    public TicTacToe copy() {
        TicTacToe ticTacToe = new TicTacToe();
        for (int i=0; i<3; i++){
            System.arraycopy(cells[i], 0, ticTacToe.cells[i], 0, 3);
        }
        ticTacToe.history = history;
        return ticTacToe;
    }

    @Override
    public String getSymbol(int row, int col) {
        return cells[row][col];
    }

    public enum Symbol {
        X("X"), O("O");
        final String marker;
        Symbol(String marker) {
            this.marker = marker;
        }

        public String marker() {
            return marker;
        }
    }
}
class History {
    List<Representation> boards = new ArrayList<>();

    public Representation getBoardAtMove(int moveIndex){
        for (int i=0; i<boards.size() - (moveIndex + 1); i++){
            boards.remove(boards.size()-1);
        }
        return boards.get(moveIndex);
    }
    public Representation undo(){
        boards.remove(boards.size()-1);
        return boards.get(boards.size()-1);
    }
    public void add(Representation representation){
        boards.add(representation);
    }
}
class Representation {
    String representation;

    public Representation(Board board){
        representation = board.toString();
    }
}
