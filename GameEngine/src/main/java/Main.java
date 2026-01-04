import api.*;
import boards.Board;
import game.*;
import user.Player;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args){
        GameEngine gameEngine = new GameEngine();
        AIEngine aiEngine = new AIEngine();
        RuleEngine ruleEngine = new RuleEngine();
        EmailService emailService = new EmailService();
        SMSService smsService = new SMSService();
        Board board = gameEngine.start("TicTacToe");
        Scanner scanner = new Scanner(System.in);
        int row, col;
        Player human = new Player("X");
        Player computer = new Player("O");
        if(human.getUser().activeAfter(10, TimeUnit.DAYS)){
            emailService.send(new SendEmailCommandBuilder().user(human.getUser()).message("Welcome back").build());
            smsService.send(new SendSMSCommandBuilder().user(human.getUser()).message("Welcome back").build());
        }
        while (!ruleEngine.getState(board).isOver()){
            System.out.println("Make your move");
            System.out.println(board);
            row = scanner.nextInt();
            col = scanner.nextInt();
            Move oppMove = new Move(new Cell(row, col), human);
            gameEngine.move(board, oppMove);
            if(!ruleEngine.getState(board).isOver()){
                Cell computerMove = aiEngine.suggestMove(computer, board);
                gameEngine.move(board, new Move(computerMove, computer));
            }
        }
        if(ruleEngine.getState(board).getWinner().equals(human.symbol())){
            emailService.send(new SendEmailCommandBuilder().user(human.getUser()).message("Congratulation on your win!").build());
            smsService.send(new SendSMSCommandBuilder().user(human.getUser()).message("Congratulation on your win!").build());
        }
        System.out.println("Game result: " + ruleEngine.getState(board));
        System.out.println(board);
    }
}
