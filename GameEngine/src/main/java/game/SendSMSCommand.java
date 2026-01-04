package game;

import user.User;

public class SendSMSCommand{
    NotificationDetails details;
    String link;
    String templateId;
    String template;

    public SendSMSCommand(User user, String message) {
        this.details = new NotificationDetails(user, message);
    }
    public String getMessage(){
        return details.getMessage();
    }
    public User getReceiver(){
        return details.getReceiver();
    }
}
