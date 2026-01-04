package game;

import user.User;

public class SendEmailCommand {
    NotificationDetails details;
    String link;
    String templateId;
    String template;

    public SendEmailCommand(User user, String message) {
       details = new NotificationDetails(user, message);
    }
    public String getMessage(){
        return details.getMessage();
    }
    public User getReceiver(){
        return details.getReceiver();
    }
}
