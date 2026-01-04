package api;

import game.SendEmailCommand;
import game.SendSMSCommand;
import user.User;

public class SMSService {
    private void sendSMS(User user, String message){
        //send email
    }
    public void send(SendSMSCommand sendSMSCommand){
        sendSMS(sendSMSCommand.getReceiver(), sendSMSCommand.getMessage());
    }
}
