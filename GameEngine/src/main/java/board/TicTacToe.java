package board;

import game.Board;
import game.Cell;
import game.Move;

import java.util.Arrays;

public class TicTacToe extends Board {
    String[][] cells = new String[3][3];

    public String getCell(int row, int col){
        return cells[row][col];
    }
    public void setCell(String symbol, Cell cell){
        cells[cell.getRow()][cell.getCol()] = symbol;
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
}
