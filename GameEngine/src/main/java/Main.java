import api.*;
import boards.Board;
import event.*;
import game.*;
import user.Player;

import java.util.ArrayList;
import java.util.List;
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
        EventBus eventBus = new EventBus();
        eventBus.subscribe(new Subscriber((event -> emailService.send(new SendEmailCommand(event)))));
        eventBus.subscribe(new Subscriber(event -> smsService.send(new SendSMSCommand(event))));
        if(human.getUser().activeAfter(10, TimeUnit.DAYS)){
            eventBus.publish(new Event(human.getUser(), "Welcome back!", "sandymyspace.com", "ACTIVITY"));
        }
        while (!ruleEngine.getState(board).isOver()){
            System.out.println("Make your move");
            System.out.println(board);
            row = scanner.nextInt();
            col = scanner.nextInt();
            Move oppMove = new Move(Cell.getCell(row, col), human);
            gameEngine.move(board, oppMove);
            if(!ruleEngine.getState(board).isOver()){
                Cell computerMove = aiEngine.suggestMove(computer, board);
                gameEngine.move(board, new Move(computerMove, computer));
            }
        }
        if(ruleEngine.getState(board).getWinner().equals(human.symbol())){
            eventBus.publish(new Event(human.getUser(), "congratulation!", "sandymyspace.com", "WIN"));
        }
        System.out.println("Game result: " + ruleEngine.getState(board));
        System.out.println(board);
    }
}
