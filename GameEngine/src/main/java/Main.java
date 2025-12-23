import api.AIEngine;
import api.GameEngine;
import api.RuleEngine;
import boards.Board;
import game.Cell;
import game.Move;
import user.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        GameEngine gameEngine = new GameEngine();
        AIEngine aiEngine = new AIEngine();
        RuleEngine ruleEngine = new RuleEngine();
        Board board = gameEngine.start("TicTacToe");
        Scanner scanner = new Scanner(System.in);
        int row, col;

        while (!ruleEngine.getState(board).isOver()){
            System.out.println("Make your move");
            System.out.println(board);
            Player human = new Player("X");
            Player computer = new Player("O");
            row = scanner.nextInt();
            col = scanner.nextInt();
            Move oppMove = new Move(new Cell(row, col), human);
            gameEngine.move(board, oppMove);
            if(!ruleEngine.getState(board).isOver()){
                Cell computerMove = aiEngine.suggestMove(computer, board);
                gameEngine.move(board, new Move(computerMove, computer));
            }
        }
        System.out.println("Game result: " + ruleEngine.getState(board));
        System.out.println(board);
    }
}
