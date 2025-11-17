package board;

import game.Board;
import game.Cell;
import game.Move;

import java.util.Arrays;

public class TicTacToe implements Board {
    String[][] cells = new String[3][3];

    public String getCell(int row, int col){
        return cells[row][col];
    }
    public void setCell(String symbol, Cell cell){
        if(cells[cell.getRow()][cell.getCol()] != null){
            cells[cell.getRow()][cell.getCol()] = symbol;
        }else{
            throw new IllegalArgumentException();
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
    public void move(Move move) {
        setCell(move.getPlayer().symbol(), move.getCell());
    }

    @Override
    public TicTacToe copy() {
        TicTacToe ticTacToe = new TicTacToe();
        for (int i=0; i<3; i++){
            System.arraycopy(cells[i], 0, ticTacToe.cells[i], 0, 3);
        }
        return ticTacToe;
    }
}
