package api;

import game.SendEmailCommand;
import user.User;

public class EmailService {
    private void sendEmail(User user, String message){
        //send email
    }
    public void send(SendEmailCommand sendEmailCommand){
        sendEmail(sendEmailCommand.getReceiver(), sendEmailCommand.getMessage());
    }
}
