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
    AIEngine aiEngine;
    RuleEngine ruleEngine;

    @Before
    public void setup(){
        gameEngine = new GameEngine();
        aiEngine = new AIEngine();
        ruleEngine = new RuleEngine();
    }

    @Test
    public void checkForRowWin(){
        Board board = gameEngine.start("TicTacToe");
        int moves[][] = new int[][]{{1,0}, {1,1}, {1,2}};
        playGames(board, moves);
        Assert.assertTrue(ruleEngine.gameState(board).isOver());
        Assert.assertEquals(ruleEngine.gameState(board).getWinner(), "X");
    }

    @Test
    public void checkForColWin(){
        Board board = gameEngine.start("TicTacToe");
        int moves[][] = new int[][]{{0,0}, {1,0}, {2, 0}};
        playGames(board, moves);
        Assert.assertTrue(ruleEngine.gameState(board).isOver());
        Assert.assertEquals(ruleEngine.gameState(board).getWinner(), "X");
    }
    public void playGames(Board board, int[][] moves){
        int row, col;
        int next = 0;
        while (!ruleEngine.gameState(board).isOver()){
            Player human = new Player("X");
            Player computer = new Player("O");
            row = moves[next][0];
            col = moves[next][1];
            next++;
            Move oppMove = new Move(new Cell(row, col), human);
            gameEngine.move(board, oppMove);
            if(!ruleEngine.gameState(board).isOver()){
                Move computerMove = aiEngine.suggestMove(computer, board);
                gameEngine.move(board, computerMove);
            }
        }
    }
}