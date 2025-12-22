import api.AIEngine;
import api.GameEngine;
import api.RuleEngine;
import game.Board;
import game.Cell;
import game.Move;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import user.Player;

public class GameplayTest {
    GameEngine gameEngine;
    RuleEngine ruleEngine;

    @Before
    public void setup(){
        gameEngine = new GameEngine();
        ruleEngine = new RuleEngine();
    }

    @Test
    public void checkForRowWin(){
        Board board = gameEngine.start("TicTacToe");
        int moves[][] = new int[][]{{1,0}, {1,1}, {1,2}};
        int secondPlayerMoves[][] = new int[][]{{0, 0}, {0, 1}, {0, 2}};
        playGames(board, moves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForColWin(){
        Board board = gameEngine.start("TicTacToe");
        int moves[][] = new int[][]{{0,0}, {1,0}, {2, 0}};
        int secondPlayerMoves[][] = new int[][]{{0, 1}, {0, 2}, {1, 1}};
        playGames(board, moves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }
    @Test
    public void checkForDiagWin(){
        Board board = gameEngine.start("TicTacToe");
        int moves[][] = new int[][]{{0,0}, {1,1}, {2, 2}};
        int secondPlayerMoves[][] = new int[][]{{0, 1}, {0, 2}, {1, 0}};
        playGames(board, moves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }
    @Test
    public void checkForRevDiagWin(){
        Board board = gameEngine.start("TicTacToe");
        int moves[][] = new int[][]{{0,2}, {1,1}, {2, 0}};
        int secondPlayerMoves[][] = new int[][]{{0, 0}, {0, 1}, {1, 0}};
        playGames(board, moves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }
    @Test
    public void checkForSecondPlayerWin(){
        Board board = gameEngine.start("TicTacToe");
        int moves[][] = new int[][]{{1,0}, {1,1}, {2, 0}};
        int secondPlayerMoves[][] = new int[][]{{0, 0}, {0, 1}, {0, 2}};
        playGames(board, moves, secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "O");
    }
    public void playGames(Board board, int[][] firstPlayerMoves, int[][] secondPlayerMoves){
        int row, col;
        int next = 0;
        while (!ruleEngine.getState(board).isOver()){
            Player firstPlayer = new Player("X");
            Player secondPlayer = new Player("O");
            row = firstPlayerMoves[next][0];
            col = firstPlayerMoves[next][1];
            Move firstPlayerMove = new Move(new Cell(row, col), firstPlayer);
            gameEngine.move(board, firstPlayerMove);
            if(!ruleEngine.getState(board).isOver()){
                int sRow = secondPlayerMoves[next][0];
                int sCol = secondPlayerMoves[next][1];
                Move secondPlayerMove = new Move(new Cell(sRow, sCol), secondPlayer);
                gameEngine.move(board, secondPlayerMove);
            }
            next++;
        }
    }
}